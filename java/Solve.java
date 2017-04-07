import java.util.*;
import java.io.*;

class Solve {
    public static void main(String[] args) throws IOException {
        Importer in = new Importer();
        SudokuHelper sh = new SudokuHelper();
        ArrayList<SudokuBoard> arr = in.getInput();
        System.out.println("received sudokus: " + arr.size());
        for (SudokuBoard sb : arr) {
            sh.printSudoku(sb);
            PriorityRecursive r = new PriorityRecursive(sb);
            sh.printSudoku(r.solve());
            System.out.println("Valid : " + sh.isValidSolution(sb));
            System.out.println("Steps : " + sb.count);
        }
    }
}
