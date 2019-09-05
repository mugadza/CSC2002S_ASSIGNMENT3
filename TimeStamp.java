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
	
	@Override
	public String toString(){
		String timeStampString = "";
		for(int x = 0; x < this.windDetails.length; x++){
			for(int y = 0; y < this.windDetails[0].length; y++){
				timeStampString += this.windDetails[x][y].toString() + " | ";
			}
			timeStampString += "\n";
		}
		return timeStampString;
	}
}