import java.util.ArrayList;
public class RepeatNode extends Node{
	//boolCompare hi;
	BooleanCompareNode hi; 
	
	public static ArrayList<StatementNode>statementList = new ArrayList<>();
	public RepeatNode (BooleanCompareNode hi) {
		this.hi = hi;
	}
	public BooleanCompareNode getBoolCompNode() {
		return hi;
	}
	@Override
	public String toString() {
		return null;
	}
}
