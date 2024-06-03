
/* Parser 3 */
public class AssignmentNode extends StatementNode {

	private VariableReferenceNode target;
	private tokenType assign;
	private Node value;//could be anything from 
	//boolCompareNode to IntegerNode
	
	public AssignmentNode(VariableReferenceNode target, Node value) {
		this.target = target;
		this.value = value;
	}
	public VariableReferenceNode getTarget() {
		return target;
	}
	public Node getValue() {
		return value;
	}
	
	public String toString() {
		return target + " assignment := " + value;
	}
}
