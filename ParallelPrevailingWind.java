import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelPrevailingWind extends RecursiveAction{
    private final int SEQUENTIAL_CUTTOFF = 1000000;

    private TimeStamp[] timestamps;
    private int startIndex;
    private int endIndex;
    private int matrixWidth;
    private int tWidth;
    private WindDetails sumWindDetails;

    ParallelPrevailingWind(TimeStamp [] timestamps, int startIndex, int endIndex){
        this.sumWindDetails = new WindDetails(0,0,0);
        this.timestamps = timestamps;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.matrixWidth = (timestamps.length > 0) ? timestamps[0].getYLength() : 0;
        this.tWidth = timestamps.length;

        // System.out.println("===================== Cloud Prevailing Wind =================================> Start: " + startIndex + " End: " + endIndex);
    }

    @Override
    protected void compute() {
        if(endIndex - startIndex <= SEQUENTIAL_CUTTOFF){
            // Execute the sequntial computation
            for(int i = startIndex; i < endIndex; i++)
            {
                Coordinates coordinates = mapIndexToThreeDimensionCoordinate(i);
                int x = coordinates.x();
                int y = coordinates.y();
                int z = coordinates.z();

                sumWindDetails.addWindDetails(this.timestamps[z].at(x, y));
            }

        }
        else{
            // create more threads
            int midIndex = (this.endIndex + this.startIndex)/2;
            ParallelPrevailingWind left = new ParallelPrevailingWind(this.timestamps, startIndex, midIndex);
            ParallelPrevailingWind right = new ParallelPrevailingWind(this.timestamps, midIndex, endIndex);

            left.fork();
            right.compute();
            left.join();

            WindDetails leftWindDetails = left.getSumWindDetails();
            WindDetails rightWindDetails = right.getSumWindDetails();
            rightWindDetails.addWindDetails(leftWindDetails);
            sumWindDetails = rightWindDetails;
        }
    }

    private int layerSize(){
		return (timestamps.length > 0) ? timestamps.length  * timestamps[0].getXLength() * timestamps[0].getYLength() : 0;
	}

    public WindDetails getSumWindDetails()
    {
        return sumWindDetails;
    }

    public void setSumWindDetails(WindDetails other)
    {
        sumWindDetails = other;
    }

    /**
     * Given an in index i, return coordinates of that given index
     */
    private Coordinates mapIndexToThreeDimensionCoordinate(int index){
        int z = index % tWidth;
        int y = (index / tWidth) % matrixWidth;
        int x = index / (matrixWidth * tWidth);
        return new Coordinates(x, y, z);
    }

    /** 
     * Given the average values and convection, get the cloud type
     */
	private int getCloudType(float averageX, float averageY, float convection){
		double magnitude = Math.sqrt((averageX*averageX) + (averageY*averageY));

        if(Math.abs(convection) > magnitude){
            return 0;
        }
        else if(Math.abs(convection) <= magnitude && magnitude > 0.2){
            return 1;
        }
        else{
            return 2;
        }
	}
}
