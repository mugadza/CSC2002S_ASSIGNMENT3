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
	 Vector.class \
	 Coordinates.class \
	 Layer.class \
	 ParallelCloudClassification.class \
	 ParallelPrevailingWind.class \
	 ParallelCloudCalculator.class \
	 CloudCalculator.class 

runParallel:
	cd bin && java ParallelCloudCalculator ../largesample_input.txt && cd ..	

runSerial:
	cd bin && java CloudCalculator ../largesample_input.txt && cd ..		
				
# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java 
	
clean:
	@rm -f  $(BINDIR)/*.class
	@rm -Rf doc
