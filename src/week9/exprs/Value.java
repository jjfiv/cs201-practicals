package week9.exprs;

/**
 * This is a literal number, like 5, 0, -7 or 89.
 */
public class Value extends IntExpr {
    int literal;

    public Value(int literal) {
        this.literal = literal;
    }

    @Override
    public int evaluate() {
        return this.literal;
    }

    @Override
    public String toString() {
        return Integer.toString(literal);
    }
}