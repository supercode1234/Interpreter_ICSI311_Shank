import java.util.ArrayList;
public class BuiltInWrite extends FunctionNode {
	
	public void execute (ArrayList<InterpreterDataType> collect) {
		// loop over things, and print each out
		// System.out.println();// correct
	}
	
	@Override 
	public boolean isVariadic() {
		return true;
	}
}
