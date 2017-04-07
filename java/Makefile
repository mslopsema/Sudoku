JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	SudokuBoard.java \
	SudokuHelper.java \
	Importer.java \
	PriorityRecursive.java \
	Recursive.java \
	Solve.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

