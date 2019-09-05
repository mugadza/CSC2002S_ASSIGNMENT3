public class TimeStamp{
	public  WindDetails[][] windDetails;

	TimeStamp(int xLength, int yLength)
	{
		windDetails = new  WindDetails[xLength][yLength];
	}

	public void addWindDetail(int xIndex, int yIndex, WindDetails windDetail){
		assert xIndex < this.windDetails.length : "Wind detail x index out of range.";
		assert yIndex < this.windDetails[0].length : "Wind detail y index out of range.";

		this.windDetails[xIndex][yIndex] = windDetail;
	}

	@Override
	public String toString(){
		String timeStampString = "";
		for(int x = 0; x < this.windDetails.length; x++){
			for(int y = 0; y < this.windDetails.length; y++){
				timeStampString += this.windDetails[x][y].toString() + " | ";
			}
			timeStampString += "\n";
		}
		return timeStampString;
	}
}