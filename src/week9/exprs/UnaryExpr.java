package week9.exprs;

/**
 * This is an expression with one argument, like !x or -x.
 */
public class UnaryExpr extends IntExpr {
    String op;
    IntExpr child;

    public UnaryExpr(String op, IntExpr child) {
        this.op = op;
        this.child = child;
    }

    @Override
    public String toString() {
        return "( " + op + " " + child.toString() + ")";
    }

    @Override
    public int evaluate() {
        if (op.equals("-")) {
            return -1 * child.evaluate();
        } else if (op.equals("!")) {
            int result = child.evaluate();
            // We consider zero false and anything else to be true.
            if (result == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
}