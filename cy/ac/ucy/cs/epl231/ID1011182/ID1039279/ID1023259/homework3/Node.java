package cy.ac.ucy.cs.epl231.ID1011182.ID1039279.ID1023259.homework3;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private int x, y;
	private String id;
	private int temperature;
	ArrayList<Node> adjacents;
	int number;

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

	public String getId() {
		return this.id;
	}

	// Setters
	public void addAdjacent(Node A) {
		adjacents.add(A);
	}

	public void deleteAdjacent(String id) {
		for (int i = 0; i < adjacents.size(); i++) {
			if (adjacents.get(i).getId().equals(id)) {
				adjacents.remove(i);
			}
		}
	}

	// Constructor
	public Node(String id, int x, int y, int temperature, int num) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.temperature = temperature;
		adjacents = new ArrayList<Node>();
		this.number = num;
	}

	@Override
	public String toString() {
		return "<ID: " + getId() + ">";
	}

	@Override
	public int compareTo(Node B) {
		int idA = Integer.parseInt(this.getId());
		int idB = Integer.parseInt(B.getId());
		return Integer.compare(idA, idB);
	}
}