JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \ Go_Board.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
