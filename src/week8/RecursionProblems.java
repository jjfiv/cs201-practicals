package week8;

import adt.ListADT;
import adt.errors.TODOErr;

// TODO: comment this, complete methods; complete recursiveMin test.
public class RecursionProblems {
    public static void countDown(int n) {
        if (n > 0) {
            System.out.println("Count: " + n);
            countDown(n - 1);
        } else {
            System.out.println("Done!");
        }
    }

    public static long factorialLoop(int n) {
        long fact = 1;
        for (int i = n; i > 0; i--) {
            fact *= i;
        }
        return fact;
    }

    public static long recursiveFactorial(int n) {
        throw new TODOErr("recursiveFactorial");
    }

    public static int recursiveMin(ListADT<Integer> numbers) {
        if (numbers.size() <= 1) {
            return numbers.getFront();
        }
        int first = numbers.getFront();
        int minRest = recursiveMin(numbers.slice(1, numbers.size()));
        return Math.min(first, minRest);
    }

    public static boolean isPalindrome(String word) {
        if (word.charAt(0) != word.charAt(word.length() - 1)) {
            return false;
        } else {
            throw new TODOErr("isPalindrome; outer letters match.");
        }
    }

    public static void main(String[] args) {
        countDown(10);
        System.out.println("Check the tests for your recursive functions.");
    }
}
