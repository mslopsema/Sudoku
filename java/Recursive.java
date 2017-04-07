import java.util.*;

class Recursive {
    private SudokuBoard sb;
    private SudokuHelper sh;
    private Queue<Integer> q;
    private int count = 0;

    Recursive(SudokuBoard sb) {
        sh = new SudokuHelper();
        this.sb = sb;
    }

    public SudokuBoard solve() {
        q = sh.loadBlanks(sb);
        if (!recurse()) System.out.println("Fail");
        sb.count = this.count;
        return sb;
    }

    private boolean recurse() {
        count++;
        if (q.isEmpty()) return true;
        int id = q.poll();
        HashSet<Character> options = sh.getRemainders(sb, id);
        for (Character c : options) {
            sb.board[sh.getRow(id)][sh.getCol(id)] = c;
            if (recurse()) return true;
        }
        sb.board[sh.getRow(id)][sh.getCol(id)] = '.';
        q.add(id);
        return false;
    }
}
