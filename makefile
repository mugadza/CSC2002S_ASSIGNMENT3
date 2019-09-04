SRCDIR = src
BINDIR = bin
DOCDIR = doc


JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR):$(JUNIT)

vpath %.java $(SRCDIR):$(TESTDIR)
vpath %.class $(BINDIR)

# define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

#default rule - will be invoked by make

all: PowerObject.class \
				AVLTree.class\
				BinarySearchTree.class\
				PowerAVLApp.class \
				PowerBSTApp.class \
				test.class\

				
# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java 
	


clean:
	@rm -f  $(BINDIR)/*.class
	@rm -Rf doc