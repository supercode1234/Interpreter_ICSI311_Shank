import java.util.ArrayList;
public class FunctionCallNode extends Node{
	String name;
	private ArrayList <VariableNode> parameterList = new ArrayList<>();
	public FunctionCallNode (String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return parameterList+"";
	}

}
