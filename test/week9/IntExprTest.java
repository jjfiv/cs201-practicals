package week9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import week9.exprs.BinaryExpr;
import week9.exprs.IfExpr;
import week9.exprs.IntExpr;
import week9.exprs.UnaryExpr;
import week9.exprs.Value;

public class IntExprTest {
    @Test
    public void testAdd() {
        IntExpr threePlusTwo = new BinaryExpr("+", new Value(3), new Value(2));
        assertEquals(5, threePlusTwo.evaluate());
    }

    @Test
    public void testSubtract() {
        IntExpr threeMinusTwo = new BinaryExpr("-", new Value(3), new Value(2));
        assertEquals(1, threeMinusTwo.evaluate());
    }

    @Test
    public void testMultiply() {
        IntExpr threeTimesTwo = new BinaryExpr("*", new Value(3), new Value(2));
        assertEquals(6, threeTimesTwo.evaluate());
    }

    @Test
    public void testDivide() {
        IntExpr threeDividedByTwo = new BinaryExpr("/", new Value(3), new Value(2));
        assertEquals(1, threeDividedByTwo.evaluate());
    }

    @Test
    public void testLectureExample() {
        IntExpr eightDividedByTwo = new BinaryExpr("/", new Value(8), new Value(2));
        assertEquals(4, eightDividedByTwo.evaluate());
        IntExpr twoPlusTwo = new BinaryExpr("+", new Value(2), new Value(2));
        assertEquals(4, twoPlusTwo.evaluate());
        IntExpr output = new BinaryExpr("*", eightDividedByTwo, twoPlusTwo);
        assertEquals(16, output.evaluate());
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        IntExpr badMath = new BinaryExpr("/", new Value(8), new Value(0));
        badMath.evaluate();
    }

    @Test
    public void testSupportModulus() {
        IntExpr e17div4 = new BinaryExpr("/", new Value(17), new Value(4));
        IntExpr e17mod4 = new BinaryExpr("%", new Value(17), new Value(4));

        // 16 / 4 = 4
        assertEquals(4, (new BinaryExpr("/", new Value(16), new Value(4))).evaluate());
        // 17 / 4 = 4
        assertEquals(4, e17div4.evaluate());
        // remainder of 17 / 4 -> 1
        assertEquals(1, e17mod4.evaluate());
    }

    @Test
    public void testUnaryOperators() {
        IntExpr five = new Value(5);
        assertEquals(5, five.evaluate());

        // try the negative operator:
        IntExpr negativeFive = new UnaryExpr("-", five);
        assertEquals(-5, negativeFive.evaluate());

        // try both cases of the not-operator:
        IntExpr notFive = new UnaryExpr("!", five);
        IntExpr notZero = new UnaryExpr("!", new Value(0));
        assertEquals(0, notFive.evaluate());
        assertEquals(1, notZero.evaluate());
    }

    @Test
    public void testIfElseOperators() {
        Value True = new Value(1);
        Value False = new Value(0);

        assertEquals(3, new IfExpr(True, new Value(3), new Value(7)).evaluate());
        assertEquals(7, new IfExpr(False, new Value(3), new Value(7)).evaluate());
    }
}
