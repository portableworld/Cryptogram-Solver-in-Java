package cryptograms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Charlie
 */
public class Words {
    
    private ArrayList<String> allWords = new ArrayList<String>();
    
    /**
     * Class constructor
     * <p>
     * Reads a built in text file as its dictionary of words.
     * 
     */ 
    public Words() {
        try {
            String word;
            // Get text file of Dictionary words. 
            // TODO - Maybe make into a loop for adding multiple Dictionaries?
            InputStream file = getClass().getResourceAsStream("2of4brif.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            // In Dictionary file, each line is a single word. 
            while ((word = reader.readLine()) != null) {
                allWords.add(word);
            }
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Gets all the words from the class's dictionary that have an equal 
     * length as the int passed in.
     * 
     * @param   len   Length of the words to pull out of the Dictionary
     * @return        A String Array of all the words in the Dictionary of the given length
     */
    public String[] getWordsOfLength(int len) {
        ArrayList<String> foundWords = new ArrayList<String>();
        for (String word : allWords) {
            if (word.length() == len)
                foundWords.add(word);
        }
        return foundWords.toArray(new String[foundWords.size()]);
    }
}
