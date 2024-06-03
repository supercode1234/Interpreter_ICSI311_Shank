import java.util.HashMap;

public class ProgramNode extends Node {
	
	//Hash Map, <function_name, FunctionNode>
	public static HashMap<String, FunctionNode> collect_functionNode = new HashMap<>();

	// toString method
	@Override
	public String toString() {
		return collect_functionNode+"";
	}
}
