#include <iostream>
#include <vector>
#include <set>
#include <unordered_set>
#include "sudoku.h"

using namespace std;

void sudoku::print_board() {
  for (int i = 0; i < 9; i++) {
    if (i == 3 || i == 6) cout << " ---------------------" << endl;
    for (int j = 0; j < 9; j++) {
      cout << " " << board[i][j];
      if (j == 2 || j == 5) cout << " |";
    }
    cout << endl;
  }
}

bool sudoku::is_valid_solution() {
  // Check Rows are 1-9
  for (int i = 0; i < 9; i++) {
    unordered_set<char> st;
    for (int j = 0; j < 9; j++) {
      if (board[i][j] == '.') return false;
      st.insert(board[i][j]);
    }
    if (st.size() != 9) return false;
  }
  // Check Cols are 1-9
  for (int i = 0; i < 9; i++) {
    unordered_set<char> st;
    for (int j = 0; j < 9; j++) {
      st.insert(board[j][i]);
    }
    if (st.size() != 9) return false;
  }
  // Check Groups are 1-9
  for (int i = 0; i < 9; i += 3) {
    for (int j = 0; j < 9; j += 3) {
      unordered_set<char> st;
      for (int k = i; k < i + 3; k++) {
        for (int l = j; l < j + 3; l++) {
        st.insert(board[k][l]);
        }
      }
      if (st.size() != 9) return false;
    }
  }
  return true;
}

int sudoku::getId(int row, int col) {
  return row * 9 + col;
}

int sudoku::getRow(int id) {
  return id / 9;
}

int sudoku::getCol(int id) {
  return id % 9;
}

set<int> sudoku::getRemainders(int id) {
  set<int> rem = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  int row = getRow(id), col = getCol(id);
  for (int i = 0; i < 9; i++) rem.erase(board[row][i] - '0');
  for (int i = 0; i < 9; i++) rem.erase(board[i][col] - '0');
  int group_row = (row / 3) * 3;
  int group_col = (col / 3) * 3;
  for (int i = group_row; i < group_row + 3; i++) {
    for (int j = group_col; j < group_col + 3; j++) {
      rem.erase(board[i][j] - '0');
    }
  }
  return rem;
}
