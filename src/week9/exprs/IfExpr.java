package week9.exprs;

/**
 * This is an "if expression" -- if a result is not zero, it's considered true.
 * We can encode "equals" by doing a subtraction of two values!
 */
public class IfExpr extends IntExpr {
    IntExpr cond;
    IntExpr thenExpr;
    IntExpr elseExpr;

    public IfExpr(IntExpr cond, IntExpr thenExpr, IntExpr elseExpr) {
        this.cond = cond;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public int evaluate() {
        int condition = cond.evaluate();
        if (condition != 0) {
            return this.thenExpr.evaluate();
        } else {
            return this.elseExpr.evaluate();
        }
    }

    @Override
    public String toString() {
        return "(if " + cond + " { " + thenExpr + " } else { " + elseExpr + " })";
    }
}