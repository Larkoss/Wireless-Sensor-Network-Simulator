package cy.ac.ucy.cs.epl231.ID1011182.ID1039279.ID1023259.homework3;

import java.util.*;

public class Graph {

	private int height;
	private LinkedList<Node>[] hashTable;
	private int distance;
	private int numNodes;

	// Getters
	public static int findDistance(Node A, Node B) {
		return (int) Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2));
	}

	public int getHeight() {
		return this.height;
	}

	private boolean isComplete() {
		for (int i = 0; i < height; i++)
			if (hashTable[i].size() > 19)
				return true;
		return false;
	}

	public int getDd() {
		return this.distance;
	}

	// Constructor
	public Graph(int distance) {
		this.numNodes = 0;
		this.distance = distance;
		this.height = 5;
		hashTable = new LinkedList[height];
		for (int i = 0; i < height; i++) {
			hashTable[i] = new LinkedList<Node>();
		}
	}

	// Constructor using the MST
	public Graph(Graph g, ArrayList<Link> mstLinks) {
		this.numNodes = g.numNodes;
		this.distance = g.distance;
		this.height = g.height;
		hashTable = new LinkedList[height];
		for (int i = 0; i < height; i++) 
			hashTable[i] = new LinkedList<Node>();
		for (int i = 0; i < height; i++)
			for (int j = 0; j < g.hashTable[i].size(); j++) {
				hashTable[i].add(g.hashTable[i].get(j).clone());
				hashTable[i].get(j).adjacents.clear();
			}
		// System.out.println("mstLinks:\n" + mstLinks + " size = " + mstLinks.size());
		for (int i = 0; i < mstLinks.size(); i++) {
			Node A = this.find(mstLinks.get(i).getA().getId());
			Node B = this.find(mstLinks.get(i).getB().getId());
			// System.out.println("A = " + A + " B = " + B);
			A.addAdjacent(B);
			B.addAdjacent(A);
		}
	}

	// Hashfunction
	private int hashFunction(String id) {
		return (Integer.parseInt(id) % this.getHeight());
	}

	// Node Manipulation

	//Check if a nodes exists in the hashtable
	//return true/false
	public boolean exist(Node A) {
		int i = hashFunction(A.getId());
		for (int j = 0; j < hashTable[i].size(); j++)
			if (hashTable[i].get(j).getId().equals(A.getId()))
				return true;
		return false;
	}

	//Return a node using its id
	public Node find(String id) {
		int i = hashFunction(id);
		for (int j = 0; j < hashTable[i].size(); j++)
			if (hashTable[i].get(j).getId().equals(id))
				return hashTable[i].get(j);
		return null;
	}

	public void add(Node A) {
		// Check if it already exists
		if (exist(A) == true) {
			System.out.println("EXISTS!");
			return;
		}
		// If not full add it immediatly
		if (!isComplete()) {
			int position = hashFunction(A.getId());
			hashTable[position].add(A);
			// Else create new hashTable
		} else {
			height = 10 * height;
			// Hashtable initialization
			LinkedList<Node>[] newHash = new LinkedList[height];
			for (int i = 0; i < height; i++) {
				newHash[i] = new LinkedList<Node>();
			}
			// Add everynode node to the new Linked List of the Hashtable
			for (int i = 0; i < hashTable.length; i++) {
				for (int j = 0; j < hashTable[i].size(); j++) {
					String tempId = hashTable[i].get(j).getId();
					int position = hashFunction(tempId);
					newHash[position].add(hashTable[i].get(j));
				}
			}
			//Finally add the node to the new hashtable
			hashTable = newHash;
			int position = hashFunction(A.getId());
			hashTable[position].add(A);
		}
		attachAdjacents(A);
		this.numNodes++;
	}

	//Calculate each distance from every other node
	//if its close enough add to adjacents
	private void attachAdjacents(Node A) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				if (findDistance(A, hashTable[i].get(j)) < this.distance && A.compareTo(hashTable[i].get(j)) != 0) {
					//if A is adjacent to B, B is adjacent to A
					hashTable[i].get(j).addAdjacent(A);
					A.addAdjacent(hashTable[i].get(j));
				}
			}
		}
	}

	//Search and delete a node, using id
	//Also remove it froms its adjacents' list
	public void delete(String id) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < hashTable[i].size(); j++) {
				hashTable[i].get(j).deleteAdjacent(id);
				if (hashTable[i].get(j).getId().equals(id)) {
					hashTable[i].remove(j);
					j--;
				}
			}
	}

	// Minimum Spanning Tree
	public ArrayList<Link> minimumSpanningTree(String id) {
		// Keep track of which nodes are visited
		boolean visited[] = new boolean[numNodes];
		// Initialize priority queue
		PriorityQueue<Link> pq = new PriorityQueue<Link>();
		int m = numNodes - 1;
		int edgeCount = 0;
		// int mstCost = 0;
		// Links that are form the MST
		ArrayList<Link> mstLinks = new ArrayList<Link>();
		// Add the Links of the first node to the
		// priority queue
		addEdges(id, pq, visited);
		// Temp link
		Link edge;

		int i = 0;
		while (pq.peek() != null && edgeCount != m) {
			// System.out.println("pq: " + pq);
			edge = pq.poll();

			if (visited[edge.getB().number])
				continue;

			// mstCost += edge.getWeight();
			mstLinks.add(edge);

			addEdges(edge.getB().getId(), pq, visited);
		}
		return mstLinks;
	}

	// Supplementary Function for MST
	private void addEdges(String id, PriorityQueue<Link> pq, boolean[] visited) {
		Node A = find(id);
		visited[A.number] = true;
		for (int k = 0; k < A.adjacents.size(); k++) {
			Node B = A.adjacents.get(k);
			if (visited[B.number] == false) {
				Link edge = new Link(findDistance(A, B), A, B);
				pq.add(edge);
			}
		}
	}

	// Prints
	public void printHashtable() {
		for (int i = 0; i < height; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < hashTable[i].size(); j++) {
				System.out.print(hashTable[i].get(j));
				System.out.print("   ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printGraph() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				System.out.print(hashTable[i].get(j) + ": ");
				for (int k = 0; k < hashTable[i].get(j).adjacents.size(); k++) {
					System.out.print(hashTable[i].get(j).adjacents.get(k));
					System.out.print("   ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	// prints BFS traversal from a given source s
	void printBFS(Node A) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[this.numNodes];

		// Create a queue for BFS
		LinkedList<Node> queue = new LinkedList<Node>();

		// Mark the current node as visited and enqueue it
		Node s = A;
		visited[s.number] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Node> i = s.adjacents.iterator();
			while (i.hasNext()) {
				Node n = i.next();
				if (!visited[n.number]) {
					visited[n.number] = true;
					queue.add(n);
				}
			}
		}
		System.out.println("\n");
	}

	int temperatureBFS(Node A) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[this.numNodes];

		// Create a queue for BFS
		LinkedList<Node> queue = new LinkedList<Node>();

		// Mark the current node as visited and enqueue it
		Node s = A;
		visited[s.number] = true;
		queue.add(s);
		int maxTemp = -274;
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			int temp = s.getTemperature();
			if(temp > maxTemp)
				maxTemp = temp;

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Node> i = s.adjacents.iterator();
			while (i.hasNext()) {
				Node n = i.next();
				if (!visited[n.number]) {
					visited[n.number] = true;
					queue.add(n);
				}
			}
		}
		return maxTemp;
	}

}
