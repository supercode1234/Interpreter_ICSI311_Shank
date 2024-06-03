import java.util.ArrayList;
public class BooleanDataType extends InterpreterDataType{
	private boolean bol;
	//need to add a storage (arrayList)?

	public BooleanDataType (boolean bol) {
		this.bol = bol;
	}
	@Override
	public String ToString() {
		return bol+"";
	}
	@Override
	public void FromString(String input) {
		bol = Boolean.valueOf(input);
	}
}
