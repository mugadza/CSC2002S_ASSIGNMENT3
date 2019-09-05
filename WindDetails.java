public class WindDetails{
	public float xAdvection;
	public float yAdvection;
	public float convection;
	public int cloudClassification;

	public WindDetails(float xAdvection, float yAdvection, float convection){
		this.xAdvection = xAdvection;
		this.yAdvection = yAdvection;
		this.convection = convection;
	}

	public WindDetails addWindDetails(WindDetails other){
		this.xAdvection = this.xAdvection + other.xAdvection;
		this.yAdvection = this.yAdvection + other.yAdvection;
		this.convection= this.convection + other.convection;

		return new WindDetails(this.xAdvection, this.yAdvection, this.convection);

	}

	@Override
	public String toString(){
		return "xAdvection: " + xAdvection + ", yAdvection: " + yAdvection + ", convection: " + convection;
	}
}