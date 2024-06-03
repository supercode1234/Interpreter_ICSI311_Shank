
abstract public class ParameterNode extends Node {
	private VariableReferenceNode varRef;
	private Node nonVar;
	
	public ParameterNode (VariableReferenceNode varRef) {
		this.varRef = varRef;
	}
	public ParameterNode (Node nonVar) {
		this.nonVar = nonVar;
	}

	@Override
	public String toString() {
		return null;
	}
	
}
