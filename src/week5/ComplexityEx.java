package week5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ComplexityEx {

    /**
     * Find the position of a given value in a list, or return -1 if it's not
     * present.
     * 
     * @param dataset - the list to search
     * @param query   - the value to find
     * @return the position of the value, or -1
     */
    public static int listSearch(List<Integer> dataset, int query) {
        for (int i = 0; i < dataset.size(); i++) {
            if (dataset.get(i) == query) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Compute whether a given number is prime.
     * 
     * @param n - the number in question.
     * @return true if only divisors are 1 and itself.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Make a list of random integers.
     * 
     * @param n     - how many?
     * @param lower - the smallest possible number.
     * @param upper - the largest possible number.
     * @return the list of n integers.
     */
    public static List<Integer> makeRandomInts(int n, int lower, int upper) {
        List<Integer> output = new ArrayList<>(n);
        Random rand = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) {
            output.add(rand.nextInt(upper - lower) + lower);
        }
        return output;
    }

    /**
     * Timing Experiment #1: isPrime
     */
    static void timeIsPrime() {
        // create random data:
        List<Integer> data = makeRandomInts(1000, 0, 5000);
        // create answer set, so that work happens.
        Set<Integer> foundPrimes = new HashSet<>();

        // write down the current time:
        long startTime = System.currentTimeMillis();
        // do the experiment:
        for (int x : data) {
            if (isPrime(x)) {
                foundPrimes.add(x);
            }
        }
        // write down the current time:
        long endTime = System.currentTimeMillis();
        // estimate the time that passed:
        double timeDiff = (endTime - startTime) / 1e3;
        // Summarize what we've learned.
        System.out.println("Found " + foundPrimes.size() + " primes out of " + data.size() + " random numbers in "
                + timeDiff + " seconds. Speed = " + (data.size() / timeDiff) + " Tests/Sec.");

    }

    /**
     * Timing experiment for listSearch.
     */
    static void timeListSearch() {
        // TODO: create a timing experiment for listSearch:
        List<Integer> dataToSearch = makeRandomInts(4000, -1000, 1000);
        List<Integer> queries = makeRandomInts(1000, -1000, 1000);
        Set<Integer> foundQueries = new HashSet<>();

        System.err.println("TODO");
    }

    public static void main(String[] args) {
        timeIsPrime();
        timeListSearch();
    }
}
