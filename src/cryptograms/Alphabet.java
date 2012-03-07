
package cryptograms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Charlie
 */
public class Alphabet {
    private Map<String, String> letters = new HashMap<String, String>();
    
    public Alphabet() {}
    
    public void solveLetter(String encrypted, String decrypted) {
        letters.put(encrypted, decrypted);
    }
    
    public boolean testWordsWith(String encrypted, String decrypted) {
        String[] eArray = encrypted.split("");
        String[] dArray = decrypted.split("");
        
        for (int i = 0; i < eArray.length; i++) {
            if (letters.containsKey(eArray[i])) {
                if (!(letters.get(eArray[i]).equals(dArray[i])))
                    return false;
            }
        }
        
        return true;
    }
    
    public String solveWordWith(String word) {
        String[] wordArray = word.split("");
        String solvedWord = "";
        
        for (String letter : wordArray) {
            if (letters.containsKey(letter)) {
                letter = letters.get(letter);
            }
            solvedWord += letter;
        }
        return solvedWord;
    }
    
    public String solvePuzzleWith(String puzzle) {
        String[] wordArray = puzzle.split(" ");
        String solvedPuzzle = "";
        
        for (String word : wordArray) {
            solvedPuzzle += (solveWordWith(word) + " ");
        }
        return solvedPuzzle;
    }
}
