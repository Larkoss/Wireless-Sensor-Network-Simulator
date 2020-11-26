import cy.ac.ucy.cs.epl231.IDs10111821039279.homework3.Node;

class Link {
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
        if (idA < idB) {
            this.A = A;
            this.B = B;
        } else {
            this.A = B;
            this.B = A;
        }
        this.weight = weight;
    }

    //Equal
    public boolean equal(Link link) {
        return (this.A.getId() == link.getA().getId() && this.B.getId() == link.getB().getId())
                && (this.weight == link.weight);
    }

    //Print
    @Override
    public String toString() {
        return(A.getId() + " - (" + weight + ") - " + B.getId());
    }
}
