

public class StringNode extends Node {
	// private variable
	private String string_element;
	
	// Constructor
	public StringNode (String string_element) {
		this.string_element = string_element;
	}

	// toString method
	@Override
	public String toString() {
		return string_element;
	}

	public String getStringNode() {
		return string_element;
	}
}
