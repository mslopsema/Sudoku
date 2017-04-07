#include <cmath>
#include <cstdio>
#include <iostream>
#include <algorithm>
#include <string>
#include <fstream>
#include "sudoku.h"
#include "priority_recurse.h"

using namespace std;

int main() {
  string file="../bin/input";
  ifstream fin(file.c_str());
  int success = 0;
  int T; // Number of Trials
  fin >> T;
  for (int h = 0; h < T; h++) {
    sudoku s;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        fin >> s.board[i][j];
      }
    }
    s.print_board();
    priority_recurse pr;
    pr.solve(s);
    cout << endl;
    s.print_board();
    if (s.is_valid_solution()) success++;
    cout << s.is_valid_solution() << endl;
  }
  cout << "Solve Success : " << success << " / " << T << endl;
  return 0;
}
