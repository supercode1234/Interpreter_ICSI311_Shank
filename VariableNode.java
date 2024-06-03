enum variableType{
	 INTEGER, REAL, BOOLEAN, STRING, CHARACTER, 
}

public class VariableNode extends Node {
	private String name;
	private Node value;
	private boolean changeable;
	private variableType type;
	private tokenType type1;
	private int type3;

	// array, from and to, support array
	// if not array, set from and to to zero.

	/* [Constructors] */
	// Value is not assigned
	public VariableNode (String name, variableType type, boolean changeable,
			tokenType type1) {
		this.name = name;
		this.type = type;
		this.changeable = changeable;
		this.type1 = type1;
	}
	
	public VariableNode(String name, variableType type, boolean changeable,
			tokenType type1, Node value) {
		this.name = name;
		this.type = type;
		this.changeable = changeable;
		this.type1 = type1;
		this.value = value;
	}
	public VariableNode (String name, variableType type, boolean changeable,
			tokenType type1, int type3) {
		this.name = name;
		this.type = type;
		this.changeable = changeable;
		this.type1 = type1;
		this.type3 = type3;
	}
	public VariableNode (String name, variableType type, boolean changeable) {
		this.name = name;
		this.type = type;
		this.changeable = changeable;
	}
	
	// accessor 
	// Value is assigned
	public VariableNode (String name, variableType type, 
			boolean changeable, Node value) {
		this.name = name;
		this.type = type;
		this.changeable = changeable;
		this.value = value;
	}


	public VariableNode() {
		// what is this? Testing
	}
	public String getName() {
		return name;
	}
	public variableType getVarType() {
		return type;
	}
	public Node getValue() {
		return value;
	}
	@Override
	public String toString() {
		//if (changeable == true) {
		return "\nType: "+ type + " name: " + name + "    Value: "+value +
				"    " + /*type1+*/"  changeable:"+changeable+"  "+ type1;
		//}
//		else {
//		return "Type: "+ type + " 	Name: " + name + "		Value: "+value + type1 +changeable + "   ?";
//		}
	}
	
}
