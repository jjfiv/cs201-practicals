package week11;

import adt.ListADT;
import adt.impl.JavaList;

/**
 * Radix Sort, implemented with the help of Wikipedia refreshing my memory.
 * <a href="https://en.wikipedia.org/wiki/Radix_sort">Wikipedia: Radix Sort</a>
 * 
 * @author jfoley
 *
 */
public class RadixSort {

    /**
     * Radix Sort is famous for being the only O(n) sorting algorithm.
     * It only really works on numbers.
     * 
     * Also, the constants are insane! It's O(256 * 4n) at least and it uses a lot
     * of small lists to do its job.
     * 
     * @param input - a list of numbers.
     * @return output - a sorted list of numbers.
     */
    public static ListADT<Integer> sort(ListADT<Integer> input) {
        ListADT<Integer> current = input;

        // Work on each of the four bytes of the number.
        // Sort by each 256-size (0xff) "digit" one at a time.
        for (int step = 0; step < 4; step++) {
            ListADT<ListADT<Integer>> buckets = new JavaList<>();
            for (int n = 0; n < 256; n++) {
                buckets.addBack(new JavaList<>());
            }
            for (int x : current) {
                // Shifting a number to the left:
                // >>> 0, 8, 16, 24
                // Is the same as division:
                // >>> /2^0, /2^8, /2^16, /2^24
                // Anding a number with 0xff (=255) grabs only the last 8 bits of that number.
                // We then have one bucket for each digit!
                int bucket = (x >>> (step * 8) & 0xff);
                // Put the numbers directly in the appropriate bucket (now they're sorted by
                // that digit)!
                buckets.getIndex(bucket).addBack(x);
            }

            // Before the next loop, collect from buckets in order into current!
            current = new JavaList<>();
            for (ListADT<Integer> bucket : buckets) {
                for (int y : bucket) {
                    current.addBack(y);
                }
            }
        }

        // Now work on positive & negative separately:
        // https://stackoverflow.com/a/15306692/1057048
        ListADT<Integer> positive = new JavaList<>();
        ListADT<Integer> negative = new JavaList<>();

        // Add to either positive list or negative list:
        for (int x : current) {
            if (x < 0) {
                negative.addBack(x);
            } else {
                positive.addBack(x);
            }
        }

        // Collect the negative first and then the positive!
        current = new JavaList<>();
        for (int n : negative) {
            current.addBack(n);
        }
        for (int p : positive) {
            current.addBack(p);
        }

        // Now we're totally sorted!
        return current;
    }
}
