public class Layer{
	private TimeStamp [] timestamps;

	public Layer (int numTimestamps){
		this.timestamps = new TimeStamp[numTimestamps];
	}

	public void addTimeStamp(int index, TimeStamp timeStamp){
		assert index < timestamps.length : "Time stamp index out of range.";

		timestamps[index] = timeStamp;
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