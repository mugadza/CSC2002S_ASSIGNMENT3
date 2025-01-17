public class Layer{
	private TimeStamp [] timestamps;

	public Layer (int numTimestamps){
		this.timestamps = new TimeStamp[numTimestamps];
	}

	public void addTimeStamp(int index, TimeStamp timeStamp){
		assert index < timestamps.length : "Time stamp index out of range.";

		timestamps[index] = timeStamp;
	}

	public TimeStamp at(int x){
		return timestamps[x];
	}

	public TimeStamp[] getTimeStamps(){
		return timestamps;
	}

	public int layerLength(){
		return timestamps.length;
	}

	public int layerSize(){
		return timestamps.length  * timestamps[0].getXLength() * timestamps[0].getYLength();
	}

	public Vector getAverage(){
		WindDetails sumWindDetails = new WindDetails(0,0,0);

		for (int t = 0; t < this.timestamps.length; t++){
			for(int x = 0; x < this.timestamps[t].getXLength(); x++){
				for(int y = 0; y < this.timestamps[t].getYLength(); y++){
					sumWindDetails.addWindDetails(this.timestamps[t].at(x, y));
				}
			}
		}
		float xAverage =  sumWindDetails.xAdvection/layerSize();
		float yAverage =  sumWindDetails.yAdvection/layerSize();

		return new Vector(xAverage, yAverage);
	}

	@Override
	public String toString(){
		String layerString = "";
		for(int x = 0; x < this.timestamps.length; x++){
			layerString += this.timestamps[x].toString() + "\n";
		}
		return layerString;
	}
}
