import java.util.ArrayList;

public class IfNode extends Node {
	
	//private boolCompare hi;
	private IfNode nextIf;//else_or_else_if;// next 
	private BooleanCompareNode boolCompNode;
	
	//don't make static, otherwise, you will have same value
	public ArrayList<StatementNode>Ifstatement=new ArrayList<>();
	public void setParameter(ArrayList<StatementNode>Ifstatement) {
		this.Ifstatement = Ifstatement;
	}

	//  ver 1
//	public IfNode hi (BooleanCompareNode boolCompNode) {
//		this.boolCompNode = boolCompNode;
//		return else_or_else_if;
//	} // if or else if
	// ver 2
	public IfNode (BooleanCompareNode boolCompNode) {
		this.boolCompNode = boolCompNode;
	} // if or else if
	
	public IfNode getNextIf() {
		return nextIf;
	}
	
	public BooleanCompareNode getBoolCompNode() {
		return boolCompNode;
	}
	@Override
	public String toString() {
		return "if"+boolCompNode+"IfNode testing"+Ifstatement;
	}
}
