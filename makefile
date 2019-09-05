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

all: WindDetails.class \
	 TimeStamp.class \
	 Layer.class \
	 CloudCalculator.class 

run:
	cd bin && java CloudCalculator ../simplesample_input.txt && cd ..		
				
# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java 
	


clean:
	@rm -f  $(BINDIR)/*.class
	@rm -Rf doc