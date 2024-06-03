
/* Parser 3 */
enum boolCompare{
	hit, greater_than, less_than, equal, 
	greater_or_equal, less_or_equal,
}

public class BooleanCompareNode extends Node {
	private Node left;
	private Node right;
	private boolCompare comp;
	
	public BooleanCompareNode(boolCompare comp, Node left, Node right) {
		this.comp = comp;
		this.left = left;
		this.right = right;
	}
	
	public Node getBoolLeft() {
		return left;
	}
	public Node getBoolRight() {
		return right;
	}
	public boolCompare getBoolComp() {
		return comp;
	}
	@Override
	public String toString() {
		return ""+left +" "+ comp + " "+right;
	}
	
	
}
