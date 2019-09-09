public class WindDetails{
	public float xAdvection;
	public float yAdvection;
	public float convection;
	public int cloudClassification;

	public WindDetails(float xAdvection, float yAdvection, float convection){
		this.xAdvection = xAdvection;
		this.yAdvection = yAdvection;
		this.convection = convection;
		this.cloudClassification = 0;
	}

	public void addWindDetails(WindDetails other){
		this.xAdvection = this.xAdvection + other.xAdvection;
		this.yAdvection = this.yAdvection + other.yAdvection;
		this.convection = this.convection + other.convection;
	}

	public void setCloudClassification(int cloudClassification){
		this.cloudClassification = cloudClassification;
	}

	@Override
	public String toString(){
		return Integer.toString(cloudClassification);
	}
}
