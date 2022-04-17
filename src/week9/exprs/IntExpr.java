package week9.exprs;

/**
 * All expressions that our 'calculator' supports are integer expressions.
 */
public abstract class IntExpr {
    /**
     * This is an abstract 'evaluate' method to compute specific values.
     * Different subclasses represent differnet operations, and thus, different
     * values.
     * 
     * @return the value of this expression.
     */
    public abstract int evaluate();
}
