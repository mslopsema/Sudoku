import java.util.*;

class PriorityRecursive {
    private SudokuBoard sb;
    private SudokuHelper sh;
    private Queue<Node> q;
    private int count = 0;

    PriorityRecursive(SudokuBoard sb) {
        sh = new SudokuHelper();
        this.sb = sb;
    }

    public SudokuBoard solve() {
        Comparator<Node> c = new Compare();
        q = new PriorityQueue<Node>(1, c);
        loadNodes();
        if (!recurse()) System.out.println("Fail");
        sb.count = this.count;
        return sb;
    }

    private boolean recurse() {
        count++;
        if (q.isEmpty()) return true;
        Node n = q.poll();
        if (sb.board[sh.getRow(n.id)][sh.getCol(n.id)] != '.') return false;
        HashSet<Character> options = sh.getRemainders(sb, n.id);
        for (Character c : options) {
            sb.board[sh.getRow(n.id)][sh.getCol(n.id)] = c;
            if (recurse()) return true;
        }
        sb.board[sh.getRow(n.id)][sh.getCol(n.id)] = '.';
        q.add(new Node(n.id));
        return false;
    }

    private void loadNodes() {
        for (int i = 0; i < 81; i++) {
            if (sb.board[sh.getRow(i)][sh.getCol(i)] == '.')
                q.add(new Node(i));
        }
    }


    class Node {
        HashSet<Character> set;
        int id;

        Node (int id) {
            this.id = id;
            set = new HashSet<Character>();
            set = sh.getRemainders(sb, id);
        }
    }

    class Compare implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            Integer s1 = n1.set.size();
            Integer s2 = n2.set.size();
            return (s1.compareTo(s2));
        }
    }
}
