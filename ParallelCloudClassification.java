import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelCloudClassification extends RecursiveAction{
    private final int SEQUENTIAL_CUTTOFF = 50000;

    private WindDetails[][] windDetails;
    private int startIndex;
    private int endIndex;
    private int matrixWidth;

    ParallelCloudClassification(WindDetails[][] windDetails, int startIndex, int endIndex){
        this.windDetails = windDetails;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.matrixWidth = (windDetails.length > 0) ? windDetails[0].length : 0;

        // System.out.println("===================== Cloud Classification =================================> Start: " + startIndex + " End: " + endIndex);
    }

    @Override
    protected void compute() {
        if(endIndex - startIndex <= SEQUENTIAL_CUTTOFF){
            // Execute the sequntial computation
            WindDetails sumWindDetails = new WindDetails(0,0,0);
            for(int i = startIndex; i < endIndex; i++)
            {
                Coordinates coordinates = mapIndexToTwoDimensionCoordinate(i);
                int x = coordinates.x();
                int y = coordinates.y();

				sumWindDetails.addWindDetails(windDetails[x][y]);

                if (x-1 < windDetails.length &&  y-1 < windDetails[0].length && (x-1 >= 0) &&  (y-1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x-1][y-1] );
				}
				if (x-1 < windDetails.length &&  y < windDetails[0].length && (x-1 >= 0) &&  (y >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x-1][y] );
				}
				if (x-1 < windDetails.length &&  y+1 < windDetails[0].length && (x-1 >= 0) &&  (y+1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x-1][y+1] );
				}
				if (x < windDetails.length &&  y+1 < windDetails[0].length && (x >= 0) &&  (y+1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x][y+1] );
				}
				if (x < windDetails.length &&  y-1 < windDetails[0].length && (x >= 0) &&  (y-1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x][y-1] );
				}
				
				if (x+1 < windDetails.length &&  y-1 < windDetails[0].length && (x+1 >= 0) &&  (y-1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x+1][y-1] );
				}
				if (x+1 < windDetails.length &&  y < windDetails[0].length && (x+1 >= 0) &&  (y >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x+1][y] );
				}
				if (x+1 < windDetails.length &&  y+1 < windDetails[0].length && (x+1 >= 0) &&  (y+1 >= 0) ){
					sumWindDetails.addWindDetails(windDetails[x+1][y+1] );
				}

				float averageX = sumWindDetails.xAdvection /9;
				float averageY = sumWindDetails.yAdvection /9;

				int cloudType = getCloudType(averageX, averageY, windDetails[x][y].convection);

				windDetails[x][y].setCloudClassification(cloudType);
            }
        }
        else{
            // create more threads
            int midIndex = (this.endIndex + this.startIndex)/2;
            ParallelCloudClassification left = new ParallelCloudClassification(this.windDetails, startIndex, midIndex);
            ParallelCloudClassification right = new ParallelCloudClassification(this.windDetails, midIndex, endIndex);

            left.fork();
            right.compute();
            left.join();
        }
    }

    /**
     * Given an in index i, return coordinates of that given index
     */
    private Coordinates mapIndexToTwoDimensionCoordinate(int index){
        int x = index/matrixWidth;
        int y = index%matrixWidth;
        return new Coordinates(x, y);
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
