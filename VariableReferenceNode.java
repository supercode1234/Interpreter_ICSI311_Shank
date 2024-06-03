
/* Parser 3 */
public class VariableReferenceNode extends Node {

	private String name;
	Node arrayIndex;
	public VariableReferenceNode (String name){
		this.name = name;
	}
	public VariableReferenceNode (String name, Node arrayIndex) {
		this.name = name;
		this.arrayIndex = arrayIndex;
	}
	public String getName () {
		return name;
	}
	// optional Node for array index expression
	// add this node in factor()
	@Override
	public String toString() {
		if (arrayIndex != null) {
		return name + arrayIndex;
		} else {
			return name;
		}
	}
}
