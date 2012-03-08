package cryptograms;


/**
 *
 * @author Charlie
 */
public class Cryptograms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle("gebo tev e cwaack cegn gsatkb ussyk");
        // When solved,  should be "mary had a little lamb mother goose"
        puzzle.solvePuzzle();
        System.out.println(puzzle.solution);
    }
}
