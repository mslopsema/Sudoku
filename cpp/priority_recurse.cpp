#include <algorithm>
#include <vector>
#include <set>
#include "sudoku.h"
#include "priority_recurse.h"

using namespace std;

void load_nodes(sudoku&);
bool search_nodes(sudoku&, int);

class Node {
public :
  int id;
  set<int> remainders;
  bool operator < (const Node &n) {
    return remainders.size() < n.remainders.size();
  }
};

vector<Node> nodes;

void priority_recurse::solve(sudoku& s) {
  load_nodes(s);
  sort(nodes.begin(), nodes.end());
  search_nodes(s, 0);
  nodes.clear();
}

void load_nodes(sudoku& s) {
  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      // Build Node for each empty space
      if (s.board[i][j] == '.') {
        Node * n = new Node();
        (*n).id = s.getId(i, j);
        (*n).remainders = s.getRemainders((*n).id);
        nodes.push_back(*n);
      }
    }
  }
}

bool search_nodes(sudoku& s, int i) {
  if (i > nodes.size() - 1) return true;
  int id = nodes[i].id;
  set<int> rem = s.getRemainders(id);
  if (rem.size() < 1) return false;
  for (auto it = rem.begin(); it != rem.end(); it++) {
    int row = s.getRow(id), col = s.getCol(id);
    s.board[row][col] = '0' + *it;
    if (search_nodes(s, i + 1)) return true;
    s.board[row][col] = '.';
  }
  return false;
}
