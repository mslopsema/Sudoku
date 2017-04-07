import java.util.*;
import java.io.*;

public class Importer {
    public ArrayList<SudokuBoard> getInput() throws IOException {
        ArrayList<SudokuBoard> arr = new ArrayList<SudokuBoard>();
        BufferedReader br = new BufferedReader(new FileReader("/home/pi/source/sudoku/bin/input"));
        int N = Integer.valueOf(br.readLine());
        for (int i = 0; i < N; i++) {
            SudokuBoard board = new SudokuBoard();
            for (int j = 0; j < 9; j++) {
                String[] s = br.readLine().split(" ");
                for (int k = 0; k < 9; k++) {
                    board.board[j][k] = s[k].charAt(0);
                }
            }
            arr.add(board);
        }
        return arr;
    }
}
