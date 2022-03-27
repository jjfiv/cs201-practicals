package week6;

/**
 * This is a "node" class; that has a label and potentially a link to another
 * node.
 */
public class Node {
    /**
     * This label:
     */
    public String label;
    /**
     * Where this node points, (if anywhere).
     */
    public Node link;

    /**
     * Construct a node with a new label, no link.
     * 
     * @param label - the label of the node.
     */
    public Node(String label) {
        this.label = label;
        this.link = null;
    }

    /**
     * Construct a node that points at a particular label.
     * 
     * @param label - the label of the node.
     * @param other - the node to point at.
     */
    public Node(String label, Node other) {
        this.label = label;
        this.link = other;
    }

    /**
     * Represent this node as itself. You can't solve this problem just by printing!
     */
    public String toString() {
        return "Node(" + label + ", ...)";
    }

    /**
     * Here's a graph of nodes for you to draw on paper.
     * 
     * @param args - ignored.
     */
    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C", b);
        Node d = new Node("D", a);
        Node e = new Node("E");
        Node f = new Node("F", e);
        Node g = new Node("G", null);
        Node h = new Node("H");

        a.link = c;
        b.link = d;
        e.link = g;
        h.link = h;
        g.link = a;
        f.link = b;
        System.out.println(f);
    }
}
