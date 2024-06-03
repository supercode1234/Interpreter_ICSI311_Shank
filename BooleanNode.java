

public class BooleanNode extends Node {
	// private variable
	private boolean bool_element;
	
	// constructor
	public BooleanNode (boolean bool_element) {
		this.bool_element = bool_element;
	}
	
	// toString method
	@Override
	public String toString() {
		return bool_element+"";
	}

	public boolean getBoolNode() {
		return bool_element;
	}

}
