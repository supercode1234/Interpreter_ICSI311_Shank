import java.util.ArrayList;

public class drafts {


/*
define start123(t:integer;s:real)
variables a:  integer
constants pi=100.23
	1+2
*/

public void function(){
	
	// Example functionNode
	FunctionNode functNode = new FunctionNode(null);
	// Example creating a variable node
	VariableNode paramNode = null;
	VariableNode var_const = null;
	// Example adding the variable to the function's parameterlist
	// functNode.addToParameterList(paramNode);
	ArrayList<VariableNode> parameterList = new ArrayList<>();
	ArrayList<VariableNode> variableList = new ArrayList<>();
	// const1 = variableType.REAL;
	// var_const = new VariableNode(const1_name.getValue(), const1, false);
	// variableList.add(var_const);
	// functNode.setVariable(variableList);
	
	/* Testing below */
	System.out.println("Testing paramList in function()");
	variableType const8 = variableType.REAL;
	// var_const = new VariableNode("testing paramListgjhfhfkkkf", const8, true);
	parameterList.add(var_const); // not variable list
	// variableList.add(var_const);
	functNode.setParameter(parameterList); // not variableList, not set variable
	// functNode.setVariable(parameterList);;
/* Testing above */
}


//z:=3.1415926525
//x:=4
//y:=1.2

//expectEndsOfLine();
//matchAndRemove(tokenType.ENDOFLINE);
// VariableNode var_const = new VariableNode("hi123", variableType.REAL, true,
// tokenType.CONSTANTS);


//in parse()
// System.out.println(funcNode+"This is parse()y");
//while (!tokenList_parse.isEmpty()) {
//	y = function();
//	expectEndsOfLine();
//	matchAndRemove(tokenType.ENDOFLINE);
//}
// parameterList.add(var_const);

//if a<0 then
//a:=a>1
//for 


//while(peek(0).getType() == tokenType.IDENTIFIER
//		||peek(0).getType() == tokenType.COLON_EQUAL_SIGN) {
// assign_left = matchAndRemove(tokenType.IDENTIFIER);
//if (assign_left == null) {
//	return null;
//}
//if(assign_left!=null) {


//assignment
//while(peek(0).getType() == tokenType.IDENTIFIER) {
//	return null;
// }
// left = new AssignmentNode((VariableReferenceNode) factor(), factor());
// return left;
// }

// return null;

// }
// VariableReferenceNode x = new VariableReferenceNode("Testing_assignment()");
// Node y = new RealNode((float) 3.2123);

// return new AssignmentNode(x,y);
// return left;



//function()
// matchAndRemove(tokenType.INDENTLEVEL);
//if (peek(0).getType() == tokenType.IDENTIFIER) {
//	 Node assign = assignment();
//	 System.out.println(assign+" this is in function()");
//}
//else if(peek(0).getType() == tokenType.IF) {
//	 parseIf();
//	 //matchAndRemove(tokenType.ENDOFLINE);
//} else if (peek(0).getType() == tokenType.WHILE) {
//	 parseWhile();
//} else if (peek(0).getType() == tokenType.FOR) {
//	 parserFor();
//}
//else {
//	 //throw new Exception("there should be statements");
//}

//else if (peek(0).getType() == tokenType.PLUS) {
//System.out.println("Testing plus in bool()");
//matchAndRemove(tokenType.PLUS);
//// Node right = expression();
//return left;
//} // don't need this
// }

//else if (peek(1).getType() == tokenType.COLON_EQUAL_SIGN) {
//Node asd = null;
//asd = assignment();
//	return asd;
//}

//if (peek(1).getType() != tokenType.COLON_EQUAL_SIGN) {
//left = expression();
// Node assignmentN = assignment();
//if (assignmentN != null) {
//return assignmentN;// or return method
//}
// throw new Exception("Missing something for bool comparison method()");


// VariableReferenceNode x1 = (VariableReferenceNode) factor();
// VariableReferenceNode x2 = new VariableReferenceNode(factor().toString());
// if (peek(0).getType() == tokenType.COLON_EQUAL_SIGN) {

/* function() */
// const1 = variableType.REAL;
// var_const = new VariableNode(const1_name.getValue(), const1, false);
// variableList.add(var_const);
// functNode.setVariable(variableList);

/* Testing below */
// System.out.println("Testing paramList in function()");
// variableType const8 = variableType.REAL;
// var_const = new VariableNode("testing paramListgjhfhfkkkf", const8, true);
// parameterList.add(var_const); // not variable list
// variableList.add(var_const);
// functNode.setParameter(parameterList); // not variableList, not set variable
// functNode.setVariable(parameterList);;
/* Testing above */

// might be important
// parameterList.add(paramNode);
// functNode.setParameter(parameterList);
// functNode.setVariable(variableList);
// FunctionNode.setParameter(parameterList);

// tokenType var_test = tokenType.INTEGER;
// variableType var3 = variableType.INTEGER;
//var_const = new VariableNode(var_test.toString(), var3, true);
//variableList.add(var_const);

//ArrayList <InterpreterDataType> collect = new ArrayList<>();
//public BuiltInRead(ArrayList <InterpreterDataType> collect) {
//	this.collect = collect;
//}

//for(int i =0; i) {
//while () {
//	
//}
//int b = s.nextInt();
//should this be a string?
//}

// remember shank functions have no return type
// but they can return through var

//public FunctionNode(){
//
//}





// builtin squareRoot
//float num=0;
//String numS = "100";
//VariableNode a = new VariableNode ("a",variableType.REAL,
//		true);
//RealDataType result = new RealDataType(num);
//
//float sqRt = (float) Math.sqrt(num);
////InterpreterDataType = sqRt;
//result.FromString(numS);

/*(IntegerNode)hi.getValue()).getIntegerNode()*/



// write a method
// you have an IDT
// what do I do with this IDT?
// String DT?
// return type of InterpreterBlock?
// should it be IDT?
// put it to the map
//new StringDataType(name);


//if (a instanceof IntegerNode) {
//	return new IntegerNode(3);
//} else if (a instanceof RealNode) {
//	return new RealNode(b);
//} else {
//	return new StringNode("a");
//}

public static void main(String[]args) {
	System.out.println("This is interpreter");
	Node n = null;
	Node n1 = null;
	MathOpNode m = null;
	MathOpNode m1 = null;
	String st1 = "hi";
	String st2 = "hi";
	
	if (st1.getClass().equals(st2.getClass())) {
		System.out.println("true, getClass check");
	}

	if (st1==st2)
		System.out.println("true, == check");
	if (variableType.INTEGER == variableType.INTEGER)
		System.out.println("true, varType == check");
}
}