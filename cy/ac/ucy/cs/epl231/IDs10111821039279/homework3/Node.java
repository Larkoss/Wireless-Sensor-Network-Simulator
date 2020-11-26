package cy.ac.ucy.cs.epl231.IDs10111821039279.homework3;

import java.util.ArrayList;

public class Node {
	private int x, y;
	private String id;
	private int temperature;
	ArrayList<Node> adjacents;

	// Getters
	int getTemperature() {
		return this.temperature;
	}

	int getX() {
		return this.x;
	}

	int getY() {
		return this.y;
	}

	public boolean isStation() {
		return id.charAt(0) == '0';
	}

	public String getId(){
		return this.id;
	}

	// Constructor
	public Node(String id, int x, int y, int temperature) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.temperature = temperature;
		adjacents = new ArrayList<Node>();
	}

	public void removeAdjacent(String id) {
		for (int i = 0; i < adjacents.size(); i++) {
			if (adjacents.get(i).id.equals(id)) {
				adjacents.remove(i);
				break;//return
			}
		}
	}

	//Print
	@Override
	public String toString(){
		return "<ID: " + getID() + ">";
	}

	// public void printVertexShort() {
	// String s = "<ID: " + getID() + ">";
	// System.out.print(s);
	// }

	// public void printVertexOnlyNeighbours() {
	// String s = "<ID: " + getID();
	// s += " | Neighbour IDs[";
	// if (neighbours.size() > 0) {
	// for (int i = 0; i < neighbours.size() - 1; i++) {
	// s += neighbours.get(i).getID();
	// s += ", ";
	// }
	// s += neighbours.get(neighbours.size() - 1).getID();
	// }
	// s += "]>";
	// System.out.print(s);
	// }

	// public void printVertexLong() {
	// String s = "<ID: " + getID() + " | Position (" + getX() + ", " + getY() + ")
	// | Firefighter: " + isFirefighter()
	// + " | Temperature: " + getTemp();
	// s += " | Neighbour IDs[";
	// if (neighbours.size() > 0) {
	// for (int i = 0; i < neighbours.size() - 1; i++) {
	// s += neighbours.get(i).getID();
	// s += ", ";
	// }
	// s += neighbours.get(neighbours.size() - 1).getID();
	// }
	// s += "]>";
	// System.out.print(s);
	// }

}