package cy.ac.ucy.cs.epl231.ID1011182.ID1039279.ID1023259.homework3;

import cy.ac.ucy.cs.epl231.ID1011182.ID1039279.ID1023259.homework3.Node;

 
class Link implements Comparable<Link> {
    int weight;
    Node A, B;

    // Getters
    public int getWeight() {
        return weight;
    }

    public Node getA() {
        return A;
    }

    public Node getB() {
        return B;
    }
     

    //  Constuctor
    public Link(int weight, Node A, Node B) {
        this.A = A;
        this.B = B;
        this.weight = weight;
    }
  
    @Override
    public String toString() {
        return(A.getId() + " - (" + weight + ") - " + B.getId());
    }

    
    @Override
    //Implement compare to
    //Used in priority queue when comparing two links
    //to find the one with bigger priority
    public int compareTo(Link other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }
}  
