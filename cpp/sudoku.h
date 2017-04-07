#ifndef SUDOKU
#define SUDOKU

#include <set>

class sudoku {
public:
  char board[9][9];
  void print_board();
  bool is_valid_solution();
  int getId(int, int);
  int getRow(int);
  int getCol(int);
  std::set<int> getRemainders(int);
};

#endif
