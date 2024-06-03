
public class IntegerDataType extends InterpreterDataType {
	private int hi;
	
	public IntegerDataType (int hi) {
		this.hi = hi;
	}
	@Override
	public String ToString() {
		return hi+"";
	}
	public int getInt() {
		return hi;
	}
	@Override
	public void FromString(String input) {
		hi = Integer.valueOf(input);
	}
}
