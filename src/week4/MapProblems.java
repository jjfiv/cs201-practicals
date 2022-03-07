package week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapProblems {

    /**
     * Given no input, return a list of roman numeral values for each letter.
     * 
     * See the corresponding test case for answers:
     * - {@link MapProblemsTest#testRomanValues}
     * 
     * @return capitalized letter keys, numeric values.
     */
    public static Map<Character, Integer> getRomanNumeralValues() {
        Map<Character, Integer> values = new HashMap<>();
        values.put('M', 1000);
        values.put('D', 500);
        values.put('C', 100);
        values.put('L', 50);
        // TODO: put X=10, V=5, I=1
        return values;
    }

    /**
     * Given a list of words (strings), count how many times each occurs in the
     * input list.
     * 
     * @param words - the input to process
     * @return - a map containing how many times each unique word occurs.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            // TODO: every time you see a word, add it to 'counts' or increment its value.
            // HINT: use counts.getOrDefault(key, defaultValue)
            counts.put(word, 1);
        }
        return counts;
    }

    /**
     * Given a list of words (strings), record the positions where they occur in the
     * input list.
     * 
     * @param words - the input to process
     * @return - a map containing the indices that each unique word occurs.
     */
    public static Map<String, List<Integer>> indexWords(List<String> words) {
        Map<String, List<Integer>> index = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (index.containsKey(word)) {
                // TODO: what if the word exists already?
            } else {
                List<Integer> positions = new ArrayList<>();
                positions.add(i);
                index.put(word, positions);
            }
        }
        return index;
    }

    /**
     * Given a map of counted strings, filter the keys to only those whose value
     * indicates they occur more than n times.
     * 
     * @param counts - map of string->int
     * @param n      - the count cutoff (not inclusive)
     * @return the output words that passed the test.
     */
    public static Set<String> countsLargerThan(Map<String, Integer> counts, int n) {
        Set<String> output = new HashSet<>();

        for (String word : counts.keySet()) {
            // TODO: look up word in counts and see if it's large enough
            // if so, add to output.
        }

        return output;
    }

}
