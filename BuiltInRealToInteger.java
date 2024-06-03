import java.util.ArrayList;
public class BuiltInRealToInteger extends FunctionNode {
	public void execute (ArrayList <InterpreterDataType> collect) {
		float num = 0;
		int result;
		result = (int)num;
	}
	@Override
	public boolean isVariadic() {
		return true;
	}
}
