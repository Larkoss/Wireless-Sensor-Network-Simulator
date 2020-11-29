package cy.ac.ucy.cs.epl231.ID1011182.ID1039279.ID1023259.homework3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Driver {

	public static void printMenu() {
		System.out.println("Enter a number to execute the operation described: ");
		System.out.println("0. Print Hashtables and Adjacency Lists");
		System.out.println("1. Calculate Minimum Spanning Tree(MST)");
		System.out.println("2. Print MST using BFS");
		System.out.println("3. Add new node and change MST");
		System.out.println("4. Remove node and change MST");
		System.out.println("5. Calculate network's maximum temparature using a fire station");
		System.out.println("6. Exit");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		if (args.length < 2) {
			System.out.println("Not enough arguments");
			System.exit(0);
		}

		// Initialize graph
		int distance = Integer.parseInt(args[0]);
		Graph g = new Graph(distance);

		// Create File Stream
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(args[1]));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		// Read from file
		int x, y, temp;
		String sx, sy, id;
		int number = 0;
		while (inputStream.hasNext()) {
			id = inputStream.next();
			sx = inputStream.next();
			x = Integer.parseInt(sx.substring(1, sx.length() - 1));
			sy = inputStream.next();
			y = Integer.parseInt(sy.substring(0, sy.length() - 1));
			temp = Integer.parseInt(inputStream.next());
			// Add nodes
			Node n = new Node(id, x, y, temp, number++);
			g.add(n);
		}
		// System.out.println("Print MST Edges");
		// System.out.println("Format: \"src\" - weight - \"dest\"");
		// System.out.println(mstLinks + "\n");

		int input = 0;
		printMenu();
		System.out.print("Enter option: ");
		input = scan.nextInt();
		Graph gMST = null;
		while (input != 6) {
			if (input == 1) {
				ArrayList<Link> mstLinks = g.minimumSpanningTree("02");
				gMST = new Graph(g, mstLinks);
			} else if (input == 2) {
				if (gMST == null)
					System.out.println("MST not yet calculated!");
				else {
					System.out.println("BFS");
					gMST.printBFS(gMST.find("02"));
				}
			} else if (input == 3) {
				System.out.print("Enter id: ");
				id = scan.next();
				System.out.print("Enter x: ");
				x = scan.nextInt();
				System.out.print("Enter y: ");
				y = scan.nextInt();
				System.out.print("Enter temperature: ");
				temp = scan.nextInt();
				// Add noded
				Node n = new Node(id, x, y, temp, number++);
				g.add(n);
				if(gMST != null) {
					gMST.add(n.clone());
					ArrayList<Link> mstLinks = gMST.minimumSpanningTree("02");
					gMST = new Graph(gMST, mstLinks);
				}
			} else if (input == 4) {
				System.out.print("Enter id: ");
				id = scan.next();
				// Add noded
				g.delete(id);
				if(gMST != null) {
					ArrayList<Link> mstLinks = g.minimumSpanningTree("02");
					gMST = new Graph(g, mstLinks);
				}
			} else if (input == 5) {
				if (gMST == null)
					System.out.println("MST not yet calculated!");
				else {
					System.out.println("Enter fire station: ");
					String stationID = scan.next();
					if (stationID.charAt(0) != '0') {
						System.out.println("ID doesn't belong to a fire station!");
						continue;
					}
					Node station = gMST.find(stationID);
					if (station == null) {
						System.out.println("Fire station doesnt exist!");
						continue;
					}
					System.out.printf("Max temperature found = %d\n", gMST.temperatureBFS(station));
				}
			} else if (input == 0) {
				System.out.println("g Print Hashtable");
				g.printHashtable();
				System.out.println("g Print Adjacency List");
				g.printGraph();
				System.out.println("mst Print Hashtable");
				if(gMST != null) {
					gMST.printHashtable();
					System.out.println("mst Print Adjacency List");
					gMST.printGraph();
				}
			}
			printMenu();
			System.out.print("Enter option: ");
			input = scan.nextInt();
		}

		// Prints
		// System.out.println("Print Hashtable");
		// gMST.printHashtable();
		// System.out.println("Print Adjacency List");
		// gMST.printGraph();

	}

}
