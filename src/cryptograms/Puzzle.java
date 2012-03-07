
package cryptograms;

import java.util.ArrayList;
import java.util.Arrays;
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
        int[] longestWordPattern = findWordPattern(longestWord);
        
        ArrayList<String> matches = getMatches(longestWord, longestWordPattern);
        
        System.out.printf("Possible matches for %s:\n", longestWord);
        for (String word : matches) {
            System.out.println(word);
        }
        
        Alphabet alpha1 = new Alphabet();
        for (int i = 0; i < longestWord.length(); i++) {
            alpha1.solveLetter(longestWord.substring(i, i+1), 
                    matches.get(0).substring(i, i+1));
        }
        
        if (alpha1.testWordsWith("gsatkb", "mother"))
            System.out.println("'mother' could be the answer");
        else
            System.out.println("'mother' is not the answer");
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
    
    private ArrayList<String> getMatches(String longestWord, 
            int[] longestWordPattern) {
        Words dict = new Words();
        String[] possibleMatches = dict.getWordsOfLength(longestWord.length());
        ArrayList<String> matches = new ArrayList<String>();
        for (String word : possibleMatches) {
            int[] wordPattern = findWordPattern(word);
            if (Arrays.equals(wordPattern, longestWordPattern)) 
                matches.add(word);
        }
        return matches;
    }
    
}
