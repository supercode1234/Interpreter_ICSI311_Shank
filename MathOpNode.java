
enum mathOperation{
		add, subtract, multiply, divide, modulo
	}

 public class MathOpNode extends Node {
	private Node left;
	private Node right;
	private mathOperation op;
	
	public MathOpNode(mathOperation op , Node left , Node right){
		this.left=left;
		this.right =right;
		this.op = op;
	}
	
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public mathOperation getOp() {
		return op;
	}
	@Override
	public String toString() {
		return ""+left+" " + op + " "+right;
	}
}
