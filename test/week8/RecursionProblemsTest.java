package week8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import adt.ListADT;
import adt.errors.EmptyListError;
import adt.errors.TODOErr;
import adt.impl.JavaList;

public class RecursionProblemsTest {
    @Test
    public void testFactorials() {
        for (int i = 1; i < 7; i++) {
            long expected = RecursionProblems.factorialLoop(i);
            long actual = RecursionProblems.recursiveFactorial(i);
            assertEquals(expected, actual);
        }
    }

    @Test(expected = EmptyListError.class)
    public void recursiveMinEmpty() {
        ListADT<Integer> empty = new JavaList<>();
        int min = RecursionProblems.recursiveMin(empty);
        Assert.fail("Should crash before this... min=" + min);
    }

    @Test
    public void recursiveMinSimple() {
        ListADT<Integer> numbers = new JavaList<>(List.of(5, 4, 3, 2, 1));
        assertEquals(1, RecursionProblems.recursiveMin(numbers));
    }

    @Test
    public void testIsPalindromeEmpty() {
        assertTrue(RecursionProblems.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeSingle() {
        assertTrue(RecursionProblems.isPalindrome("x"));
        assertTrue(RecursionProblems.isPalindrome("A"));
        assertTrue(RecursionProblems.isPalindrome("?"));
    }

    @Test
    public void testIsPalindromeMultiple() {
        assertTrue(RecursionProblems.isPalindrome("abba"));
        assertTrue(RecursionProblems.isPalindrome("xyx"));
        assertFalse(RecursionProblems.isPalindrome("river"));
        assertTrue(RecursionProblems.isPalindrome("xyzzyx"));
        assertFalse(RecursionProblems.isPalindrome("xy"));
        assertFalse(RecursionProblems.isPalindrome("xyzabzyx"));
    }
}
