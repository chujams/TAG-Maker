#Makefile
#Runs, cleans, and compiles the adventure and all related files

JAVASRC = cyoa.java advtree.java
SOURCES = ${JAVASRC} Makefile
ALLSOURCES = ${SOURCES}
MAINCLASS = cyoa
CLASSES = ${patsubst %.java, %.class, ${JAVASRC}}

all: ${CLASSES}

%.class: %.java
	javac -Xlint $<

clean:
	rm -f *.class

test: all
	java cyoa three.adventure

.PHONY: clean all test
