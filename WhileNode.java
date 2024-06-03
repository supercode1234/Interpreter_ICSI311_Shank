import java.util.ArrayList;

public class WhileNode extends Node {

	//private boolCompare hi;
	private BooleanCompareNode boolCompNode;
	public static ArrayList <StatementNode>statementList = new ArrayList<>();
	
	public WhileNode (boolCompare hi) {
		//this.hi = hi; //boolNode
	}
	
	public BooleanCompareNode getBoolCompNode() {
		return boolCompNode;
	}

	@Override
	public String toString() {
		return boolCompNode+"whileNode testing";
	}
	
}
