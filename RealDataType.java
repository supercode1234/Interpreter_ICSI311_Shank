
public class RealDataType extends InterpreterDataType {

	private float hi;
	RealDataType (float hi) {
		this.hi = hi;
	}
	
	public void setValue(float in) {
		hi = in;
	}
	
	public float getValue() {
		return hi;
	}
	
	@Override
	public String ToString() {
		return hi+"";
	}
	@Override
	public void FromString(String input) {
		hi = Float.valueOf(input);
	}

}
