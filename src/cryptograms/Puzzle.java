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
    public String oneLetterWord;
    public Alphabet theAnswer;
    private int checkedCount;
    
    public Puzzle(String puzzleText) {
        puzzle = puzzleText;
        words = puzzle.split(" ");
        solution = "";
        oneLetterWord = null;
    }
    
    public String solvePuzzle() {
        // Personal Note: 'e' is the most frequently used letter is a probibility of 12.7%
        
        String[] sortedWords = sortWordsByLength(words);
        ArrayList<Alphabet> alphas = new ArrayList<Alphabet>();
        
        for (String encryptedWord : sortedWords) {
            int[] patternedWord = findWordPattern(encryptedWord);
            ArrayList<String> possibleMatches = getMatches(encryptedWord, patternedWord);
            
            if (alphas.isEmpty()) {
                // Build Alphabets from possibleMatches
                for (String match : possibleMatches) {
                    Alphabet alpha = new Alphabet();
                    for (int i = 0; i < encryptedWord.length(); i++) {
                        alpha.solveLetter(encryptedWord.substring(i, i+1), 
                                match.substring(i, i+1));
                        
                    }
                    alphas.add(alpha);
                    if (alpha.containsEncryptedLetter(oneLetterWord) && 
                            !(alpha.getLetter(oneLetterWord).equals("a") || 
                            alpha.getLetter(oneLetterWord).equals("i")))
                    alphas.remove(alpha);
                 
                }
                for (Alphabet alphabet : alphas) {
                    if (solveWithRecursion(
                            Arrays.copyOfRange(sortedWords, 1, sortedWords.length), alphabet)) {
                        solution = theAnswer.solvePuzzleWith(puzzle);
                        return solution;
                    }
                }
            } else if (alphas.size() == 1) {
                // This Alphabet is the answer. Solve and return puzzle
                break;
            } else {
                // Check possible match against all possible Alphabets and combine
                System.out.println("Word: " + encryptedWord);
                ArrayList<String> matches = new ArrayList<String>();
                for (String pm : possibleMatches) {
                    if (alphas.get(0).testWordsWith(encryptedWord, pm))
                        matches.add(pm);
                }
                System.out.println("Possible solutions: " + matches.size());
            }
        }
        
        if (alphas.size() == 1) {
            // Solve puzzle
        } else {
            solution = "Could not solve puzzle. There are " + alphas.size() +
                    " possible solutions.";
        }
        
        return solution;
    }
    
    private boolean solveWithRecursion(String[] sortedWords, Alphabet alphabet) {
        if (sortedWords.length == 1) {
            theAnswer = alphabet;
            return true;
        }
        
        int[] patternedWord = findWordPattern(sortedWords[0]);
         ArrayList<String> possibleMatches = getMatches(sortedWords[0], patternedWord);
         
         for (String match : possibleMatches) {
             Alphabet alpha = new Alphabet();
             for (int i = 0; i < sortedWords[0].length(); i++) {
                alpha.solveLetter(sortedWords[0].substring(i, i+1), 
                        match.substring(i, i+1));      
             }
             if (alpha.compare(alphabet)) {
                 checkedCount++;
                 System.out.println("Checked " + checkedCount + " words");
                 alpha.combine(alphabet);
                 if (alpha.containsDuplicateValues())
                     continue;
                 if (solveWithRecursion(Arrays.copyOfRange(sortedWords, 1, sortedWords.length), 
                         new Alphabet(alpha))) 
                    return true;
             }
        }
         return false;
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

    private String getLongestWord(String[] wordArray) {
        int len = 0;
        String longestWord = "";
        for (String word : wordArray) {
            if (word.length() > len) {
                len = word.length();
                longestWord = word;
            } // End If
        } // End For
        return longestWord;
    } // End Method
    
    private ArrayList<String> getMatches(String encryptedWord, 
            int[] patternedWord) {
        Words dict = new Words();
        String[] possibleMatches = dict.getWordsOfLength(encryptedWord.length());
        ArrayList<String> matches = new ArrayList<String>();
        for (String word : possibleMatches) {
            int[] wordPattern = findWordPattern(word);
            if (Arrays.equals(wordPattern, patternedWord)) 
                matches.add(word);
        }
        return matches;
    }
    
    private String[] sortWordsByLength(String[] wordsArray) {
        ArrayList<String> wordsList = new ArrayList<String>();
        String[] sortedWordsArray = new String[wordsArray.length];
        wordsList.addAll(Arrays.asList(wordsArray));
        
        for (int i = 0; i < wordsArray.length; i++) {
            String word = getLongestWord(
                    wordsList.toArray(new String[wordsList.size()]));
            if (word.length() == 1) {oneLetterWord = word;}
            sortedWordsArray[i] = word;
            wordsList.remove(word);
        }
        return sortedWordsArray;
    } 
    
    private boolean checkAlphabet(Alphabet alpha) {
        boolean result;
        
        if (alpha.containsEncryptedLetter(oneLetterWord)) {
            String letter = alpha.getLetter(oneLetterWord);
            if (letter.equals("a") || letter.equals("i"))
                result = true;
            else
                result = false;
        } else {
            result = true;
        }
        
        return result;
    }
    
}
