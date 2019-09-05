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

	@Override
	public String toString(){
		return "xAdvection: " + xAdvection + ", yAdvection: " + yAdvection + ", convection: " + convection;
	}
}