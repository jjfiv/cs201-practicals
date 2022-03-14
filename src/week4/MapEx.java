package week4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import week2.PlayingCard;

public class MapEx {

    public static void main(String[] args) {
        // create a Map; default is HashMap; also have TreeMap
        Map<String, Integer> items = new HashMap<>();
        // put adds a key->value pair:
        items.put("apple", 1);
        items.put("orange", 2);
        items.put("pineapple", 3);
        // put replaces values if keys are repeated.
        items.put("apple", 4);

        // size tells you how many pairs exist.
        System.out.println("I've put " + items.size() + " fruit.");

        // remove deletes by key:
        items.remove("orange");
        items.remove("onion");

        // get gives you a value for a key:
        Integer found = items.get("apple");
        System.out.println("apple -> " + found);
        // or null if that key doesn't exist.
        Integer missing = items.get("onion");
        System.out.println("onion -> " + missing);

        // you can check to see if something's there, too.
        System.out.println("Do we have onion? " + items.containsKey("onion"));

        // Loop over keys:
        for (String key : items.keySet()) {
            System.out.println("keySet: " + key + " -> " + items.get(key));
        }
        // Loop over values:
        for (Integer val : items.values()) {
            System.out.println("values: ??? -> " + val);
        }
        // Loop over entries:
        for (Map.Entry<String, Integer> kv : items.entrySet()) {
            System.out.println("entries: " + kv.getKey() + " -> " + kv.getValue());
        }
    }

    // /**
    // * Count the number of pairs present in a list of playing cards (look only at
    // * value, not suit).
    // *
    // * @param cards - a list of playing cards.
    // * @return the number of pairs in the list.
    // */
    // public static int countPairs(List<PlayingCard> cards) {
    // Map<Integer, Integer> valueCounts = new HashMap<>();
    // for (PlayingCard card : cards) {
    // int prevCount = valueCounts.getOrDefault(card.value, 0);
    // valueCounts.put(card.value, prevCount + 1);
    // }

    // int pairs = 0;
    // for (int copies : valueCounts.values()) {
    // if (copies == 2) {
    // pairs += 1;
    // }
    // }
    // return pairs;
    // }

}
