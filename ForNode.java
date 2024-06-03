import java.util.ArrayList;

public class ForNode extends Node {
	private boolCompare hi;
	private Node from; // could be any expression, like: from 1+1 to c+1
	private Node to;
	private Node var;
	private VariableReferenceNode hi1;
	
	public static ArrayList<StatementNode>statementList=new ArrayList<>();
	
	
	public ForNode(boolCompare hi){
		this.hi = hi;
	}
	public ForNode(VariableReferenceNode hi1, Node from, Node to, ArrayList<StatementNode> statementList) {
		this.hi1 = hi1;
		this.from = from;
		this.to = to;
		this.statementList = statementList;
	}
	public Node getFrom () {
		return from;
	}
	public Node getTo() {
		return to;
	}

	@Override
	public String toString() {
		return hi+"ForNode testing";
	}
}
