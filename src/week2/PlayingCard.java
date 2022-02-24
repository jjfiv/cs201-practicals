package week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represent a standard playing card.
 */
public class PlayingCard {
    /**
     * All the possible families of playing cards; often called 'suits'.
     */
    public static String[] SUITS = {
            "HEARTS", "CLUBS", "DIAMONDS", "SPADES",
    };
    /**
     * All the possible values of playing cards; sometimes called ranks.
     */
    public static String[] VALUES = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
    };
    /**
     * An index into the {@link #SUITS} array.
     */
    public int suit;
    /**
     * An index into the {@link #VALUES} array.
     */
    public int value;

    /**
     * Create a new playing card from the suit index and the value index.
     * 
     * @param suit  - an index into the {@link #VALUES} array.
     * @param value - an index into the {@link SUITS} array.
     */
    public PlayingCard(int suit, int value) {
        // Make sure the numbers are OK:
        assert suit >= 0 && suit < SUITS.length;
        assert value >= 0 && value < VALUES.length;
        // assign to instance variables:
        this.suit = suit;
        this.value = value;
    }

    /**
     * Turn the two numbers in 'this' object into readable descriptions.
     */
    public String toString() {
        return VALUES[this.value] + " of " + SUITS[this.suit];
    }

    /**
     * Create a list of the fifty-two possible playing cards.
     * 
     * @return the list, in whatever order is most reasonable.
     */
    public static List<PlayingCard> makeDeck() {
        // TODO: a deck has 52 playing cards, one of each SUIT and VALUE combination.
        return List.of();
    }

    /**
     * Compare this playing card's value to the other playing card's value.
     * 
     * @param other a different playing card.
     * @return +1 if this is bigger than other, 0 if they're the same, -1 if this is
     *         smaller.
     */
    public int compareValue(PlayingCard other) {
        // TODO: actually do this comparison.
        return -2;
    }

    /**
     * Play a game of High/Low with playing cards.
     * Warning: no error handling on inputs.
     * 
     * @param args - ignored.
     */
    public static void main(String[] args) {
        // create input.
        Scanner input = new Scanner(System.in);

        // Keep looping until they quit.
        while (true) {
            // Create a deck:
            List<PlayingCard> deck = makeDeck();
            // Shuffle it:
            Collections.shuffle(deck, new Random());

            // take a card off the 'top'/front:
            PlayingCard first = deck.remove(0);
            System.out.println("The first card is the " + first);

            // ask the user to guess if the second will be better or worse:
            System.out.println("Do you think it will be higher (1), the same (0), or lower (-1)?");

            // read an int
            int choice = input.nextInt();

            // take a card off the 'top'/front:
            PlayingCard second = deck.remove(0);
            System.out.println("The second card is actually the " + second);

            // determine it's relationship to the first card.
            int comparison = second.compareValue(first);
            if (comparison < 0) {
                System.out.println(" .. it's smaller!");
            } else if (comparison == 0) {
                System.out.println(" .. it's the same.");
            } else {
                System.out.println(" .. it's bigger!");
            }

            // Did they get it right?
            if (choice == comparison) {
                System.out.println("Congratulations! You guessed right.");
            } else {
                System.out.println("Better luck next time!");
            }

            // discard the ENTER that came with their int;
            input.nextLine();
            // ask if they want to keep playing.
            System.out.println("Keep playing? (y/n)");
            String response = input.nextLine().trim().toLowerCase();
            // interpret no answer as 'yes'.
            if (response.length() == 0) {
                continue;
            }
            // interpret anything starting with 'n' as no.
            if (response.charAt(0) == 'n') {
                break;
            }
        }

        System.out.println("===");
        System.out.println("Goodbye!");

        input.close();
    }
}
