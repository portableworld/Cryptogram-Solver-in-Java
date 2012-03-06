
package cryptograms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Charlie
 */
public class Puzzle {
    public String puzzle;
    public String[] words;
    public String solution;
    
    public Puzzle(String puzzleText) {
        puzzle = puzzleText;
        words = puzzle.split(" ");
        solution = "";
    }
    
    public String solvePuzzle() {
        String longestWord = getLongestWord();
        Words dict = new Words();
        String[] possibleMatches = dict.getWordsOfLength(longestWord.length());
        
        return null;
    }
    
    private int[] findWordPattern(String word) {
        int[] pattern = new int[word.length()];
        Map<Character, Integer> lettersMap = new HashMap<Character, Integer>();
        
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (lettersMap.containsKey(letter)){
                pattern[i] = lettersMap.get(letter);
            } else {
                lettersMap.put(letter, i);
                pattern[i] = i;
            } // End If
        } // End For
        return pattern;
    } // End Method

    private String getLongestWord() {
        int len = 0;
        String longestWord = "";
        for (String word : words) {
            if (word.length() > len) {
                len = word.length();
                longestWord = word;
            } // End If
        } // End For
        return longestWord;
    } // End Method
    
    
}
