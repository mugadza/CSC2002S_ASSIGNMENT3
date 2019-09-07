import java.lang.Math;


public class TimeStamp{
	private WindDetails[][] windDetails;

	TimeStamp(int xLength, int yLength)
	{
		windDetails = new  WindDetails[xLength][yLength];
	}

	public void addWindDetail(int xIndex, int yIndex, WindDetails windDetail){
		assert xIndex < this.windDetails.length : "Wind detail x index out of range.";
		assert yIndex < this.windDetails[0].length : "Wind detail y index out of range.";

		this.windDetails[xIndex][yIndex] = windDetail;
	}

	public int getYLength(){
		if (windDetails.length > 0){
			return this.windDetails[0].length;
		}
		return 0;
	}

	public int getXLength(){
		return this.windDetails.length;
	}

	public WindDetails at(int x, int y){
			return windDetails[x][y];
		
	}
	
	public void classifier(){
		WindDetails sumWindDetails = new WindDetails(0,0,0);
		float total = 0;

		for(int x = 0; x < this.windDetails.length; x++){
			for(int y = 0; y < this.windDetails[0].length; y++){
				sumWindDetails.addWindDetails(windDetails[x][y]);

				
				if (x-1 < windDetails.length &&  y-1 < windDetails[0].length && (x-1 >= 0) &&  (y-1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x-1][y-1] );
				}
				if (x-1 < windDetails.length &&  y < windDetails[0].length && (x-1 >= 0) &&  (y >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x-1][y] );
				}
				if (x-1 < windDetails.length &&  y+1 < windDetails[0].length && (x-1 >= 0) &&  (y+1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x-1][y+1] );
				}
				if (x < windDetails.length &&  y+1 < windDetails[0].length && (x >= 0) &&  (y+1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x][y+1] );
				}
				if (x < windDetails.length &&  y-1 < windDetails[0].length && (x >= 0) &&  (y-1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x][y-1] );
				}
				
				if (x+1 < windDetails.length &&  y-1 < windDetails[0].length && (x+1 >= 0) &&  (y-1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x+1][y-1] );
				}
				if (x+1 < windDetails.length &&  y < windDetails[0].length && (x+1 >= 0) &&  (y >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x+1][y] );
				}
				if (x+1 < windDetails.length &&  y+1 < windDetails[0].length && (x+1 >= 0) &&  (y+1 >= 0) ){
					sumWindDetails = sumWindDetails.addWindDetails(windDetails[x+1][y+1] );
				}

				float averageX = sumWindDetails.xAdvection /9;
				float averageY = sumWindDetails.yAdvection /9;

				double magnitude = magnitudeValue(averageX, averageY); 

				int cloudType = compare(magnitude, windDetails[x][y].convection);

				windDetails[x][y].setCloudClassification(cloudType);
			}
		}


				
	}



	public double magnitudeValue (float x, float y){
		float ans = (x*x) + (y*y);

		return Math.sqrt(ans);
	}

	public int compare(double magnitude , float convection){
		 if(Math.abs(convection) > magnitude){
		 	return 0;
		 }
		 if(Math.abs(convection) <= magnitude && magnitude > 0.2){
		 	return 1;
		 }

		 else{
		 	return 2;
		 }


	}

	@Override
	public String toString(){
		String timeStampString = "";
		for(int x = 0; x < this.windDetails.length; x++){
			for(int y = 0; y < this.windDetails[0].length; y++){
				timeStampString += this.windDetails[x][y].toString();
			}
			// timeStampString += "\n";
		}
		return timeStampString;
	}
}