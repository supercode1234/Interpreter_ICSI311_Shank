import java.util.ArrayList;
import java.util.Scanner;
public class BuiltInRead extends FunctionNode {

	public void execute (ArrayList <InterpreterDataType> collect) {
		// E, do generic
		// use scanner to accept input
		//String a = collect.get(0).toString();
		//IntegerDataType b = (IntegerDataType) collect.get(1);
		//InterpreterDataType == 
		Scanner s = new Scanner (System.in);
		String v = s.nextLine();
		StringNode t = new StringNode(v);
		VariableNode a = new VariableNode("a",
				variableType.STRING, true, t);
		StringDataType st = new StringDataType(v);
		st.FromString(v);

		//collect.get(0)=v;
		// need to assign v to one of the variables in my ArrayList
	}
	
	@Override
	public boolean isVariadic() {
		return true;
	}

}
