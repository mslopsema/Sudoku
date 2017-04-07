import java.util.*;

public class SudokuHelper {
    SudokuHelper() {
    }

    public void printSudoku(SudokuBoard sb) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6)
                System.out.println(" ---------------------");
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + sb.board[i][j]);
                if (j == 2 || j == 5)
                    System.out.print(" |");
            }
            System.out.println();
        }
    }

    public int getID(int row, int col) {
        return row * 9 + col;
    }

    public int getRow(int id) {
        return id / 9;
    }

    public int getCol(int id) {
        return id % 9;
    }

    public Queue<Integer> loadBlanks(SudokuBoard sb) {
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb.board[i][j] == '.') q.add(getID(i, j));
            }
        }
        return q;
    }

    public boolean isValidSolution(SudokuBoard sb) {
        for (int i = 0; i < 9; i++) if (rowContents(sb, i * 9).size() != 9) return false;
        for (int i = 0; i < 9; i++) if (colContents(sb, i).size() != 9) return false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blkContents(sb, getID(i, j)).size() != 9) return false;
            }
        }
        return true;
    }

    public HashSet<Character> getRemainders(SudokuBoard sb, int id) {
        HashSet<Character> set = fullContents();
        set.removeAll(rowContents(sb, id));
        set.removeAll(colContents(sb, id));
        set.removeAll(blkContents(sb, id));
        return set;
    }

    public HashSet<Character> fullContents() {
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 49; i <=57; i++) set.add((char)i);
        return set;
    }

    public HashSet<Character> rowContents(SudokuBoard sb, int id) {
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < 9; i++) set.add(sb.board[getRow(id)][i]);
        set.remove('.');
        return set;
    }

    public HashSet<Character> colContents(SudokuBoard sb, int id) {
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < 9; i++) set.add(sb.board[i][getCol(id)]);
        set.remove('.');
        return set;
    }

    public HashSet<Character> blkContents(SudokuBoard sb, int id) {
        HashSet<Character> set = new HashSet<Character>();
        int row = (getRow(id) / 3) * 3;
        int col = (getCol(id) / 3) * 3;
        for (int i = row; i < row + 3; i++) {
           for (int j = col; j < col + 3; j++) {
               set.add(sb.board[i][j]);
           }
        }
        set.remove('.');
        return set;
    }

    public void printQDepth(Queue<Integer> q) {
        for (int i = 0; i < q.size(); i++)
            System.out.print("|");
        System.out.println();
    }
}
