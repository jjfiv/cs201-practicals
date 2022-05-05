package week11.adventure;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordSplitterTest {
    @Test
    public void testSingleWord() {
        List<String> words = WordSplitter.splitTextToWords("hello");
        Assert.assertEquals(1, words.size());
        Assert.assertEquals("hello", words.get(0));
    }

    @Test
    public void testDoesLowercase() {
        List<String> words = WordSplitter.splitTextToWords("HeLlO");
        Assert.assertEquals(1, words.size());
        Assert.assertEquals("hello", words.get(0));
    }

    @Test
    public void testSplitsUpWords() {
        List<String> words = WordSplitter.splitTextToWords("hello this is dog");
        Assert.assertEquals(4, words.size());
        Assert.assertEquals("hello", words.get(0));
        Assert.assertEquals("this", words.get(1));
        Assert.assertEquals("is", words.get(2));
        Assert.assertEquals("dog", words.get(3));
    }

    @Test
    public void testSplitsPunctuation() {
        List<String> words = WordSplitter.splitTextToWords("hello.  this      is! -dog");
        Assert.assertEquals(4, words.size());
        Assert.assertEquals("hello", words.get(0));
        Assert.assertEquals("this", words.get(1));
        Assert.assertEquals("is", words.get(2));
        Assert.assertEquals("dog", words.get(3));
    }
}
