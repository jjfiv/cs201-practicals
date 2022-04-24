package week10;

import java.util.*;

import adt.errors.TODOErr;

/**
 * A binary search tree (BST) of integers!
 * 
 * @author jfoley.
 */
public class IntBST {
    /**
     * The root of the tree begins here.
     */
    Node root;

    /**
     * Construct an empty tree:
     */
    public IntBST() {
        this.root = null;
    }

    /**
     * Construct a tree with a given root:
     * 
     * visibility: package
     * 
     * @param root - a reference to a BSTNode; or null.
     */
    IntBST(Node root) {
        this.root = root;
    }

    /**
     * A tree is empty if it's root is null.
     * 
     * @return - compare this root to null.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Ask root to compute its height, if available.
     * 
     * @return 0 if empty, the height of the tree otherwise.
     */
    public int getHeight() {
        if (this.root == null) {
            return 0;
        }
        return this.root.getHeight();
    }

    /**
     * Ask root to insert this value into the tree if possible.
     * 
     * @param value - the value to insert.
     */
    public void insert(int value) {
        if (this.root == null) {
            this.root = new Node(value);
        } else {
            this.root.insert(value);
        }
    }

    /**
     * Ask root if this value is contained in the tree.
     * 
     * @param value - the value to look for.
     * @return true if it is found, false if not.
     */
    public boolean contains(int value) {
        if (this.root == null) {
            return false;
        } else {
            return this.root.contains(value);
        }
    }

    /**
     * Start off an in-order traversal; create a list to use with
     * {@link Node#addToSortedList}
     * 
     * @return a sorted list of nodes.
     */
    public List<Integer> toSortedList() {
        ArrayList<Integer> output = new ArrayList<>();
        if (this.root != null) {
            this.root.addToSortedList(output);
        }
        return output;
    }

    /**
     * Collect all node values into a HashSet.
     * 
     * @return the set of node values.
     */
    public HashSet<Integer> toSet() {
        HashSet<Integer> values = new HashSet<>();
        if (this.root != null) {
            this.root.addToSet(values);
        }
        return values;
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "Empty IntBST";
        }
        return "IntBST: " + this.root.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof IntBST) {
            return Objects.equals(this.root, ((IntBST) other).root);
        }
        return false;
    }

    /**
     * Remove from this IntBST.
     * This method uses the trick of temporarily generating a new root value, to be
     * the 'parent' of the root.
     * 
     * Most of the work done here: {@linkplain Node#remove(int, Node)}
     * 
     * @param value - the value to remove (if found!)
     */
    public void remove(int value) {
        if (this.root == null) {
            return;
        }

        // Here's the deal, root doesn't have a valid parent, so let's make one,
        // temporarily!
        // That way BSTNode#remove can assume that every node has a parent!
        Node rootsFakeParent = new Node(Integer.MIN_VALUE);
        rootsFakeParent.left = this.root;
        this.root.remove(value, rootsFakeParent);
        // Now, let's grab the new root (if it's changed)!
        this.root = rootsFakeParent.left;
    }

    /**
     * This is our Node class; holds a value, a left and a right.
     */
    public static class Node {
        /**
         * The value contained in this node.
         */
        int value;
        /**
         * A node (or null) to the left of this value.
         */
        Node left;
        /**
         * A node (or null) to the right of this value.
         */
        Node right;

        /**
         * Construct a new node with a value.
         * 
         * @param value - the integer value.
         */
        public Node(int value) {
            this(value, null, null);
        }

        /**
         * Construct a new node with all instance variables.
         * 
         * @param value - the integer value.
         * @param left  - a node or null.
         * @param right - a node or null.
         */
        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * Is this node equivalent to the other?
         */
        public boolean equals(Object other) {
            if (other == null) {
                return false; // not if it's null.
            }
            if (other instanceof Node) {
                Node bst = (Node) other;
                // Check values first, this is easy.
                if (this.value != bst.value) {
                    return false;
                }
                // This will check for nulls and recurse.
                return Objects.equals(this.left, bst.left) && Objects.equals(this.right, bst.right);
            }
            return false;
        }

        @Override
        public String toString() {
            return "(" + this.value + " L:" + this.left + " R:" + this.right + ")";
        }

        /**
         * Recursively compute the height of the subtree starting at this node. The
         * height of a node is 1 + the maximum height of it's left and right branches.
         *
         * @return an integer greater than or equal to 1.
         */
        public int getHeight() {
            int leftHeight = 0;
            int rightHeight = 0;
            throw new TODOErr("IntBST.Node.getHeight");
        }

        /**
         * Recursively find the position in this tree of BSTNodes to insert the value.
         * 
         * @param value - an integer to insert.
         * @return true if we changed the tree.
         */
        public boolean insert(int value) {
            if (value == this.value) {
                // found it; no change to tree.
                return false;
            } else if (value < this.value) {
                // try-insert left:
                if (this.left == null) {
                    this.left = new Node(value);
                    return true;
                } else {
                    return this.left.insert(value);
                }
            } else {
                // try-insert right:
                if (this.right == null) {
                    this.right = new Node(value);
                    return true;
                } else {
                    return this.right.insert(value);
                }
            }
        }

        /**
         * Add all items to the output set. Order does not matter because it is a set
         * that we're building.
         * 
         * @param output - a set of integers.
         */
        public void addToSet(Set<Integer> output) {
            output.add(this.value);
            if (this.left != null) {
                this.left.addToSet(output);
            }
            if (this.right != null) {
                this.right.addToSet(output);
            }
        }

        /**
         * Call add (Java's addBack) on the output list to create an in-order traversal.
         * 
         * @param output - a list.
         */
        public void addToSortedList(List<Integer> output) {
            throw new TODOErr("IntBST.addToSortedList");
        }

        /**
         * Compute the minimum of this tree recursively.
         * 
         * @return the smallest value contained in the tree from here.
         */
        public int getMinimum() {
            if (this.left != null) {
                return this.left.getMinimum();
            }
            return this.value;
        }

        /**
         * Compute the maximum of this tree recursively.
         * 
         * @return the largest value contained in the tree from here.
         */
        public int getMaximum() {
            // Recurse to the bottom-right most element of the IntBST.
            throw new TODOErr("IntBST.Node.getMaximum");
        }

        /**
         * Removing from a Binary Search Tree is fairly complex.
         * 
         * The algorithm depends on whether the node is in the middle or the bottom:
         * 
         * 1. if there's no children, or only one, cutting it out is easy.
         * 2. if there's two children, we actually need to find the minimum from there
         * to swap upward...
         * 
         * 
         * @param value  - the value to remove.
         * @param parent - the parent of this current Node (needed to actually remove
         *               this node).
         */
        public void remove(int value, Node parent) {
            if (value < this.value) {
                if (left != null) {
                    left.remove(value, this);
                }
            } else if (value > this.value) {
                if (right != null) {
                    right.remove(value, this);
                }
            } else {
                assert (value == this.value);
                if (this.left != null && this.right != null) {
                    // find the minimum of the right subtree, and swap it up.
                    this.value = this.right.getMinimum();
                    // now chase down and remove the duplicate, recursively.
                    this.right.remove(this.value, this);
                } else {
                    // One or both of our links is null, so we can just keep it around:
                    Node child = this.left;
                    if (child == null) {
                        child = this.right;
                    }
                    // Update the link that brought us here:
                    if (parent.left == this) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }
            }
        }

        /**
         * Search through this BST and return ``true`` if it contains ``value``, false
         * otherwise; starting at this node.
         *
         * Cases: 1. This node has the value we're looking for. 2. We need to go left or
         * right. 2.a. The direction we need to go exists; call ``contains`` on that
         * node. 2.b. The direction we need to go does not exist. Return false. It's not
         * in the tree.
         * 
         * Look at {@linkplain #insert(int)} to see how to search the tree.
         *
         * @param value - the number we are searching for in this tree.
         * @return true if found, false if not found.
         */
        public boolean contains(int value) {
            if (this.value == value) {
                return true;
            }
            throw new TODOErr("IntBST.Node.contains");
        }

    }

    private static void printTraversals(Node node) {
        if (node == null) {
            return;
        }
        System.out.println("Pre-Order: " + node.value);
        printTraversals(node.left);
        System.out.println("In-Order: " + node.value);
        printTraversals(node.right);
        System.out.println("Post-Order: " + node.value);
    }

    public static void main(String[] args) {
        IntBST example = new IntBST();
        // L1:
        example.insert(5);
        // L2:
        example.insert(3);
        example.insert(7);
        // L3:
        example.insert(1);
        example.insert(2);
        example.insert(6);
        example.insert(8);
        // L4:
        example.insert(0);
        example.insert(9);
        IntBST.printTraversals(example.root);
    }
}
