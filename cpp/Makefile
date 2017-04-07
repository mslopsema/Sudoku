CC = g++
FLAGS = -g -std=c++11

#SRCS = $(shell find . -name "*.cpp")
OBJS = sudoku.o Solve.o priority_recurse.o

solve : $(OBJS)
	$(CC) $(OBJS) $(FLAGS) -o solve

Solve.o : sudoku.h priority_recurse.h Solve.cpp
	$(CC) $(FLAGS) -c Solve.cpp
sudoku.o : sudoku.h sudoku.cpp
	$(CC) $(FLAGS) -c sudoku.cpp
priority_recurse.o : sudoku.h priority_recurse.cpp
	$(CC) $(FLAGS) -c priority_recurse.cpp

all : solve
default : solve

clean :
	$(shell rm -r *.o solve)
