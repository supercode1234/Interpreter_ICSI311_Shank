import java.util.ArrayList;

/* Parser 2 */
public class FunctionNode extends Node {
	private String name;
	// set parameterList = things get passed
	public static ArrayList<VariableNode> parameterList = new ArrayList<>();
	// once I set it to public static, it does something
	public static  ArrayList<VariableNode> variableList = new ArrayList<>();
	private ArrayList<StatementNode> statementList = new ArrayList<>();

	// Constructor
	
	public FunctionNode(String name) {
		this.name = name;
	}
	public FunctionNode(){
		// needed, but don't understand how it works
	}


	//parameterList.add(variable); not in here
	// create setters here
	public void setFunction (String name) {
		this.name = name;
	}

	// set up arraylist in parser
	// accept arraylist of type variableNode
	// will be made in parser.java
	public void setParameter(ArrayList<VariableNode> parameterList) {
		this.parameterList = parameterList;
	}
	public void setVariable(ArrayList<VariableNode> variableList) {
		this.variableList = variableList;
	}
	
	// getter, accessor
	public ArrayList<VariableNode> getVariableList() {
		return variableList;
	}
	public ArrayList<StatementNode>getStatementList(){
		return statementList;
	}
	public void setStatement(ArrayList<StatementNode> statementList) {
		this.statementList = new ArrayList<>();
	}
//	public void addToParameterList(VariableNode parameterNode2) {
//		this.parameterList.add(parameterNode2);
//	} // a different way of adding parameter

	public boolean isVariadic () {
		return false;
	} //  how to override?
	

	@Override
	public String toString() {
		return ""+name+"  \nParameterList:"+parameterList+"  \nVariablesList:"+variableList
				+"	\nstatementList: "+ statementList;
	}

}
