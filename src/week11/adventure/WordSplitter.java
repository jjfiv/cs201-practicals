package week11.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class provides a word splitter that will do a pretty good job for your
 * game.
 * 
 * @author jfoley
 *
 */
public class WordSplitter {

	/**
	 * This is needed for my solution of splitting into words.
	 * Regular expressions are a type of graph.
	 */
	private static Pattern spacesOrPunctuation = Pattern.compile("(\\s+|\\p{Punct})");

	/**
	 * I'm giving you a version of this that is slightly better than
	 * the String.split(" ") that I used in lecture.
	 * 
	 * @return words - the words in the input text.
	 */
	public static List<String> splitTextToWords(String text) {
		List<String> words = new ArrayList<String>();

		for (String token : spacesOrPunctuation.split(text.toLowerCase())) {
			token = token.trim();
			if (token.isEmpty()) {
				continue;
			}

			words.add(token);
		}

		return words;
	}
}
