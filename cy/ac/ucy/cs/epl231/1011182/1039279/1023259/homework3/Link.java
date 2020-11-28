package cy.ac.ucy.cs.epl231.IDs10111821039279.homework3;
import cy.ac.ucy.cs.epl231.IDs10111821039279.homework3.Node;
class Link implements Comparable<Link>{
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


    //Constuctor
    public Link(int weight, Node A, Node B) {
        int idA = Integer.parseInt(A.getId());
        int idB = Integer.parseInt(B.getId());
        // if (idA < idB) {
        //     this.A = A;
        //     this.B = B;
        // } else {
        //     this.A = B;
        //     this.B = A;
        // }
        this.A = A;
        this.B = B;
        this.weight = weight;
    }

    //Print
    @Override
    public String toString() {
        return(A.getId() + " - (" + weight + ") - " + B.getId());
    }

    @Override
    public int compareTo(Link other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }
}
