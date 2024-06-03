import java.util.ArrayList;

public class ArrayDataType<T> extends InterpreterDataType{
	private T data;

	ArrayList <T> hi = new ArrayList<>();
	
	public ArrayDataType(ArrayList <T> hi) {
		this.data = data;
		hi=hi;
	} // don't need this constructor
	
	@Override
	public String ToString() {
		return null;
	}

	@Override
	public void FromString(String input) {
		// need to convert string to arrayList
	}
}
