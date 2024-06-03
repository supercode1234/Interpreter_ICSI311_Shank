
public class StringDataType extends InterpreterDataType {
	private String str;
	
	StringDataType (String str){
		this.str = str;
	}
	@Override
	public String ToString() {
		return str+"";
	}

	@Override
	public void FromString(String input) {
		str = String.valueOf(input);
		
	}
	
}
