package cryptograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Charlie
 */
public class Alphabet {
    private Map<String, String> letters = new HashMap<String, String>();
    
    public Alphabet() {}
    
    public Alphabet(Alphabet otherAlphabet) {
        letters.putAll(otherAlphabet.letters);
    }
    
    public void solveLetter(String encrypted, String decrypted) {
        letters.put(encrypted, decrypted);
    }
    
    public String getLetter(String encrypted) {
        return letters.get(encrypted);
    }
    
    public boolean containsEncryptedLetter(String encrypted) {
        return letters.containsKey(encrypted);
    }
    
    public boolean compare(Alphabet otherAlphabet) {
        for (String letter : otherAlphabet.letters.keySet()) {
            // If both letter maps contain the same key but different
            // values, return false. Otherwise, keep testing
            if (letters.containsKey(letter) && 
                    !(letters.get(letter).equals(otherAlphabet.letters.get(letter))))
                return false;
        }
        return true;
    }
    
    public boolean containsDuplicateValues() {
        String[] all = letters.values().toArray(new String[letters.size()]);
        for (String letter : all) {
            int count = 0;
            for (int i = 0; i < all.length; i++) {
                if (all[i].equals(letter))
                    count++;
            }
            if (count > 1)
                return false;
        }
        return true;
    }
    
    public void combine(Alphabet otherAlphabet) {
        for (String letter : otherAlphabet.letters.keySet()) {
            if (!letters.containsKey(letter))
                letters.put(letter, otherAlphabet.letters.get(letter));
        }
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
