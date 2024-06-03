
/* Parser 2 */
public class CharacterNode extends Node {
	// private variable
	private char char_element;
	
	// Constructor
	public CharacterNode (char char_element) {
		this.char_element = char_element;
	}
	
	// toString method
	@Override
	public String toString() {
		return char_element+"";
	}
}
