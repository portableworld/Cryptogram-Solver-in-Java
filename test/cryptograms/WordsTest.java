/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptograms;


import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author Charlie
 */
public class WordsTest {
    
    public WordsTest() {
    }

    @Test
    public void testConstructor() {
        System.out.println("should be over 60,000 words");
        Words words = new Words();
        assertTrue(words.allWords.size() > 60000);
    }
    
    @Test
    public void testNoWhiteSpace() {
        System.out.println("words should contain no whitespace");
        Words words = new Words();
        assertEquals("aah", words.allWords.get(0));
        assertEquals(3, words.allWords.get(0).length());
    }
}
