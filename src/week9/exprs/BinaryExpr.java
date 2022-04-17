package week9.exprs;

/**
 * This is the class you're going to be modifying: nodes in the tree that are
 * BinaryExpr can be one of +, -, /, *, and %.
 */
public class BinaryExpr extends IntExpr {
    /**
     * The math operator to compute.
     */
    String op;
    /**
     * The left-side value of the operator, as an expression of its own.
     */
    IntExpr left;
    /**
     * The right-side value of the operator, as an expression of its own.
     */
    IntExpr right;

    public BinaryExpr(String op, IntExpr left, IntExpr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /**
     * Notice how this function is recursive, as well?
     * 
     * @return stringified: ( left op right )
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " " + op + " " + right.toString() + ")";
    }

    /**
     * Evaluate a binary expression, recursively. Supports operators
     * (this.op.equals(...)) -> "+", "-", "/", "*", and "%".
     *
     * @return the value that should be computed by this node.
     */
    @Override
    public int evaluate() {
        if (op.equals("+")) {
            return left.evaluate() + right.evaluate();
        } else {
            throw new RuntimeException("Unsupported operator=" + op);
        }
    }
}