import java.util.ArrayList;
public class BuiltInSquareRoot extends FunctionNode {
	
	public void execute (ArrayList <InterpreterDataType> collect) {
		// use instance of() to see if IDT is StringDataType.etc
		RealDataType input = (RealDataType)collect.get(0);
		RealDataType output = (RealDataType)collect.get(1);
		output.setValue((float)Math.sqrt((double)(input.getValue())));
	}
	@Override
	public boolean isVariadic() {
		return true;
	}
}
