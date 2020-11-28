package cy.ac.ucy.cs.epl231.IDs10111821039279.homework3;

import java.util.*;

public class Graph {

	private int height;
	private LinkedList<Node>[] hashTable;
	private int distance;
	private int numNodes;

	// Getters
	private static int findDistance(Node A, Node B) {
		return (int) Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2));
	}

	private int getHeight() {
		return this.height;
	}

	private boolean isComplete() {
		for (int i = 0; i < height; i++)
			if (hashTable[i].size() > 19)
				return true;
		return false;
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

	// Hashfunction
	private int hashFunction(String id) {
		return (Integer.parseInt(id) % this.getHeight());
	}

	// Node Manipulation
	public boolean exist(Node A) {
		int i = hashFunction(A.getId());
		for (int j = 0; j < hashTable[i].size(); j++)
			if (hashTable[i].get(j).getId().equals(A.getId()))
				return true;
		return false;
	}

	public Node find(String id){
		int i = hashFunction(id);
		for (int j = 0; j < hashTable[i].size(); j++)
			if (hashTable[i].get(j).getId().equals(id))
				return hashTable[i].get(j);
		return null;
	}

	public void add(Node A) {
		// Check if it already exists
		if (exist(A) == true) {
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
			hashTable = newHash;
			int position = hashFunction(A.getId());
			hashTable[position].add(A);
		}
		attachAdjacents(A);
		this.numNodes++;
	}

	private void attachAdjacents(Node A) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				if (findDistance(A, hashTable[i].get(j)) < this.distance && findDistance(A, hashTable[i].get(j)) > 0) {
					hashTable[i].get(j).addAdjacent(A);
					A.addAdjacent(hashTable[i].get(j));
				}
			}
		}
	}

	public void delete(String id) {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < hashTable[i].size(); j++) {
				hashTable[i].get(j).deleteAdjacent(id);
				if (hashTable[i].get(j).getId().equals(id)) {
					hashTable[i].remove(j);
					this.numNodes--;
				}
			}
	}

	// Minimum Spanning Tree
	public void minimumSpanningTree(String id) {
		//Keep track of which nodes are visited
		boolean visited[] = new boolean[numNodes];
		//Initialize priority queue
		PriorityQueue<Link> pq = new PriorityQueue<Link>();
		int m = numNodes - 1;
		int edgeCount = 0, mstCost = 0;
		//Links that are form the MST
		ArrayList<Link> mstLinks = new ArrayList<Link>();
		//Add the Links of the first node to the
		//priority queue
		addEdges(id, pq, visited);
		//Temp link
		Link edge;
		
		int i = 0;
		while(pq.peek() != null && edgeCount != m) {
			//System.out.println("pq: " + pq);
			edge = pq.poll();

			if(visited[edge.getB().number])
				continue;
			
			mstCost += edge.getWeight();
			mstLinks.add(edge);

			addEdges(edge.getB().getId(), pq, visited);
			if(i == 10)
				break;
			i++;
		}
		System.out.println(mstLinks);
	}

	// Supplementary Function for MST
	private void addEdges(String id, PriorityQueue<Link> pq, boolean[] visited) {
		Node A = find(id);
		visited[A.number] = true;
		for(int k = 0; k < A.adjacents.size(); k++){
			Node B = A.adjacents.get(k);
			if(visited[B.number] == false){
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
}
