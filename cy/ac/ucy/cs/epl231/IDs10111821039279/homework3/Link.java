import cy.ac.ucy.cs.epl231.IDs10111821039279.homework3.Node;

class Link {
    int weight;
    Node A, B;

    // Getters
    public int getWeight() {
        return weight;
    }

    public Vertex getA() {
        return A;
    }

    public Vertex getB() {
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
    @Override
    public boolean isEqual(Link link) {
        return (this.A.getId() == link.getA().getId() && this.B.getId() == link.getB().getd())
                && (this.weight == link.weight);
    }

    //Print
    @Override
    public String toString() {
        return(A.getId() + " - (" + weight + ") - " + V.getId());
    }
}
