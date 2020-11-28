package cy.ac.ucy.cs.epl231.IDs10111821039279.homework3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("Not enough arguments");
			System.exit(0);
		}

		//Initialize graph
		int distance = Integer.parseInt(args[0]);
		Graph g = new Graph(distance);
		
		//Create File Stream
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(args[1]));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		//Read from file
		int x, y, temp;
		String sx, sy, id;
		while (inputStream.hasNext()) {
			id = inputStream.next();
			sx = inputStream.next();
			x = Integer.parseInt(sx.substring(1, sx.length() - 1));
			sy = inputStream.next();
			y = Integer.parseInt(sy.substring(0, sy.length() - 1));
			temp = Integer.parseInt(inputStream.next());
			//Add nodes
			Node n = new Node(id, x, y, temp);
			g.add(n);
		}
		//Prints
		g.printHashtable();
		g.printGraph();

	}

}
