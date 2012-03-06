
package cryptograms;

import java.util.Arrays;

/**
 *
 * @author Charlie
 */
public class Cryptograms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Testing Arrays");
        int[] five = new int[5];
        
        
        
        int[] one = {1,2,3,4,5};
        int[] two = {1,2,3,4,5};
        int[] six = {1,2,3,2,4,2,5};
        if (Arrays.equals(one, two))
            System.out.println("one and two are equal");
        else
            System.out.println("one and two aren't equal");
    }
}
