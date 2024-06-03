import java.util.HashMap;
import java.util.ArrayList;

public class Interpreter {
	public void InterpreterFunction(FunctionNode funcNode) throws Exception {
		HashMap<String, InterpreterDataType> map = new HashMap<>();
		// loop over funcNode
		
		ArrayList<VariableNode> var_list = funcNode.getVariableList();
		
		ArrayList<StatementNode> s_list = funcNode.getStatementList();
		int i = 0;
		for (i = 0; i < var_list.size(); i++) {
			VariableNode VarNode = var_list.get(i);
			// if the variable type of the variable is an integer
			if (VarNode.getVarType() == variableType.INTEGER) {
				// create an integer node with the value 
				// (retrieved by .getValue())
				IntegerNode intNode = (IntegerNode) VarNode.getValue();
				
				int intNodeValue = intNode.getIntegerNode();
				// put the name and IDT in the map
				map.put(VarNode.getName(), new IntegerDataType(intNodeValue));
			}
			// else if->int, string, real, boolean, char
			else if (VarNode.getVarType() == variableType.REAL) {
				RealNode realNode = (RealNode) VarNode.getValue();
				float realNodeValue = realNode.getRealNode();
				map.put(VarNode.getName(), new RealDataType(realNodeValue));
			} 
			else if (VarNode.getVarType() == variableType.BOOLEAN) {
				BooleanNode boolNode = (BooleanNode) VarNode.getValue();
				boolean boolNodeValue = boolNode.getBoolNode();
				map.put(VarNode.getName(), new BooleanDataType(boolNodeValue));
			} 
			else if (VarNode.getVarType() == variableType.STRING) {
				StringNode strNode = (StringNode)VarNode.getValue();
				String strNodeValue = strNode.getStringNode();
				map.put(strNodeValue, new StringDataType(strNodeValue));
			}
		}
		InterpreterBlock(map, s_list);
	}
	// function for MathOpNode in the InterpreterBlock
	public Node expression(Node n) throws Exception {
		/* should return an IDT */
		
		// Check the specific node type of (Node n).
		// could be mathOpNode, VarRefNode, IntNode, FloatNode, etc.
		if (n instanceof MathOpNode) {
			MathOpNode m = (MathOpNode)n;
			Node left = expression(m.getLeft());
			Node right = expression(m.getRight());
			return MathOpCalExp(left, right, m); // return node: int, real, string
		} 
		// return one of three: intNode, RealNode, StringNode
		else if (n instanceof VariableReferenceNode) {
			return new StringNode (((VariableReferenceNode) n).getName());
		} else if (n instanceof IntegerNode) {
			return new IntegerNode(((IntegerNode)n).getIntegerNode());
		} else if (n instanceof RealNode) {
			return new RealNode(((RealNode)n).getRealNode());
		} else if (n instanceof StringNode) {
			return new StringNode(((StringNode)n).getStringNode());
		} else
			throw new Exception("Either one is not a of valid node"
					+ "in expression()");
	} // end of expression ()
	
	
	// helper function for mathOpNode in InterpreterBlock, 
	// to help calculating, returns IDTs
	public InterpreterDataType MathOpCal(Node left, Node right, MathOpNode m) throws Exception {
		// if left and right are the same node, and they are integerNode
		if (left instanceof IntegerNode && right instanceof IntegerNode) {
			int resultInt = 0;
			int leftInt = ((IntegerNode)left).getIntegerNode();
			int rightInt = ((IntegerNode)right).getIntegerNode();
			
			if (m.getOp()==mathOperation.add) {
				resultInt = leftInt + rightInt;
			} 
			else if (m.getOp()==mathOperation.subtract) {
				resultInt = leftInt - rightInt;
			}
			else if (m.getOp()==mathOperation.multiply) {
				resultInt = leftInt * rightInt;
			}
			else if (m.getOp()==mathOperation.divide) {
				resultInt = leftInt / rightInt;
			}
			else if (m.getOp()==mathOperation.modulo) {
				resultInt = leftInt % rightInt;
			}
			return new IntegerDataType(resultInt);
		}
		// if left and right are the same node, and they are RealNode
		else if (left instanceof RealNode && right instanceof RealNode) {
			float resultReal=0;
			float leftReal = ((RealNode)left).getRealNode();
			float rightReal = ((RealNode)right).getRealNode();
			if (m.getOp()==mathOperation.add)
				resultReal = leftReal + rightReal;
			else if (m.getOp()==mathOperation.subtract)
				resultReal = leftReal - rightReal;
			else if (m.getOp()==mathOperation.multiply)
				resultReal = leftReal * rightReal;
			else if (m.getOp()==mathOperation.divide)
				resultReal = leftReal / rightReal;
			else if (m.getOp()==mathOperation.modulo)
				resultReal = leftReal % rightReal;
			return new RealDataType(resultReal);
		} 
		// if left and right are the same node, and they are StringNode
		else if (left instanceof StringNode && right instanceof StringNode) {
			// concatenate the two strings
			String leftString = ((StringNode)left).getStringNode();
			String rightString = ((StringNode)right).getStringNode();
			String resultString = leftString + rightString + "";
			return new StringDataType(resultString);
		}
		else 
			throw new Exception("Left and right are not compatiable types");
	}
	// helper function for expression () , returns Nodes
	public Node MathOpCalExp(Node left, Node right, MathOpNode m) throws Exception {
		// if left and right are the same node, and they are integerNode
		if (left instanceof IntegerNode && right instanceof IntegerNode) {
			int resultInt = 0;
			int leftInt = ((IntegerNode)left).getIntegerNode();
			int rightInt = ((IntegerNode)right).getIntegerNode();
			
			if (m.getOp()==mathOperation.add) {
				resultInt = leftInt + rightInt;
			} 
			else if (m.getOp()==mathOperation.subtract) {
				resultInt = leftInt - rightInt;
			}
			else if (m.getOp()==mathOperation.multiply) {
				resultInt = leftInt * rightInt;
			}
			else if (m.getOp()==mathOperation.divide) {
				resultInt = leftInt / rightInt;
			}
			else if (m.getOp()==mathOperation.modulo) {
				resultInt = leftInt % rightInt;
			}
			return new IntegerNode(resultInt);
		}
		// if left and right are the same node, and they are RealNode
		else if (left instanceof RealNode && right instanceof RealNode) {
			float resultReal=0;
			float leftReal = ((RealNode)left).getRealNode();
			float rightReal = ((RealNode)right).getRealNode();
			if (m.getOp()==mathOperation.add)
				resultReal = leftReal + rightReal;
			else if (m.getOp()==mathOperation.subtract)
				resultReal = leftReal - rightReal;
			else if (m.getOp()==mathOperation.multiply)
				resultReal = leftReal * rightReal;
			else if (m.getOp()==mathOperation.divide)
				resultReal = leftReal / rightReal;
			else if (m.getOp()==mathOperation.modulo)
				resultReal = leftReal % rightReal;
			return new RealNode(resultReal);
		} 
		// if left and right are the same node, and they are StringNode
		else if (left instanceof StringNode && right instanceof StringNode) {
			// concatenate the two strings
			String leftString = ((StringNode)left).getStringNode();
			String rightString = ((StringNode)right).getStringNode();
			String resultString = leftString + rightString + "";
			return new StringNode(resultString);
		}
		else 
			throw new Exception("Left and right are not compatiable types");
	}
	
	
	
	// helper function for VariableReferenceNode in InterpreterBlock
	public InterpreterDataType VarRefFunc(HashMap<String,InterpreterDataType>map,
			ArrayList<StatementNode>s_list, Node n) throws Exception {
		String name = ((VariableReferenceNode) n).getName();
		// look up the name in map
		if (map.containsKey(name.toString())) 
			return map.get(name.toString()); // var node IDT
		
		else 
			throw new Exception();
		
	}
	// helper function for BooleanCompareNode in InterpreterBlock
	public boolean BoolFunc(Node n) throws Exception {
		// creating a new BooleanCompareNode with the info of n
		BooleanCompareNode boolNode = (BooleanCompareNode)n;
		// call expression() on both left and right side
		Node left = expression (boolNode.getBoolLeft());
		Node right = expression (boolNode.getBoolRight());
		boolCompare boolComp = boolNode.getBoolComp();

		if (left instanceof IntegerNode && right instanceof IntegerNode
				|| left instanceof RealNode && right instanceof RealNode) {
			// get the integer value from left and right
			int leftInt=((IntegerNode)left).getIntegerNode();
			int rightInt=((IntegerNode)right).getIntegerNode();
			
			// get real value from left and right
			float leftReal = ((RealNode)left).getRealNode();
			float rightReal = ((RealNode)right).getRealNode();

			// compare left and right int or float value, and return true or false
			if(boolComp == boolCompare.equal) {
				if (leftInt == rightInt||leftReal == rightReal) 
					return true;
			} 
			else if (boolComp == boolCompare.greater_than) {
				if (leftInt > rightInt || leftReal > rightReal)
					return true;
			}
			else if (boolComp == boolCompare.less_than) {
				if (leftInt < rightInt || leftReal < rightReal)
					return true;
			}
			else if (boolComp == boolCompare.greater_or_equal) {
				if (leftInt >= rightInt || leftReal >= rightReal)
					return true;
			}
			else if (boolComp == boolCompare.less_or_equal) {
				if (leftInt <= rightInt || leftReal <= rightReal)
					return true;
			}
			return false;
		}
		else 
			throw new Exception("Boolean compare type not compatible");
	}
	
	// helper method for IfNode in InterpreterBlock
	public void ifNode_func(Node n, HashMap<String, InterpreterDataType>map,
			ArrayList<StatementNode> s_list ) throws Exception{
		IfNode if_node = (IfNode)n;
		IfNode NextIf = if_node.getNextIf();
		BooleanCompareNode boolCompNode = if_node.getBoolCompNode();
		BooleanCompareNode nextBoolCN = NextIf.getBoolCompNode();
		
		boolean BoolValue = BoolFunc(boolCompNode);
		boolean nextBoolVal = BoolFunc (nextBoolCN);
		if (BoolValue==true) 
			InterpreterBlock(map, s_list);
		else if (BoolValue == false && NextIf != null) {
			if (nextBoolVal == true)
				InterpreterBlock(map,s_list);
		}
	}
	
	// helper method for ForNode in InterpreterBlock
	public boolean evaluate (ForNode for_node) throws Exception {
		Node from = expression(for_node.getFrom());// will return either: string, integer, real nodes
		Node to = expression(for_node.getTo()); 
		if(from instanceof IntegerNode && to instanceof IntegerNode) {
			IntegerNode from_intNode = (IntegerNode)from;
			IntegerNode to_intNode = (IntegerNode)to;
			int from_value = from_intNode.getIntegerNode();
			int to_value = to_intNode.getIntegerNode();
			while (from_value<=to_value) {
				from_value++;
				return true;
			}
		}
		return false; // default 
	}
	
	// helper method for AssignmentNode in InterpreterBlock
	public void assignment (Node node, HashMap<String, InterpreterDataType>map,
			ArrayList<StatementNode> s_list) throws Exception {
		AssignmentNode assignmentN = (AssignmentNode)node;	// type casting
		VariableReferenceNode target = assignmentN.getTarget();//left side
		String target_name = target.getName();
		Node value = assignmentN.getValue();//right side
		// check to see what IDT is target
		InterpreterDataType targetIDT = VarRefFunc(map, s_list, target);
		if (targetIDT instanceof IntegerDataType && value instanceof IntegerNode) {
			int value_intNode = ((IntegerNode)value).getIntegerNode();
			// replaces the IDT entry for the variable
			map.put(target_name, new IntegerDataType(value_intNode)) ;
		} 
		else if (targetIDT instanceof RealDataType && value instanceof RealNode) {
			float value_realNode = ((RealNode)value).getRealNode();
			map.put(target_name, new RealDataType(value_realNode));
		}
		else if (targetIDT instanceof StringDataType && value instanceof StringNode) {
			String value_stringNode = ((StringNode)value).getStringNode();
			map.put(target_name,new StringDataType(value_stringNode));
		}
		else 
			throw new Exception("Incompatible data type for assignment");
	}
	
	
	public void InterpreterBlock(HashMap<String, InterpreterDataType> map,
			ArrayList<StatementNode> s_list) throws Exception {

		// loop over the statementNode collection
		for (int i = 0; i < s_list.size(); i++) {
			Node node = s_list.get(i);
			
			// if n is a variableReferenceNode
			if (node instanceof VariableReferenceNode) {
				VarRefFunc(map, s_list, node);
			}
			// if n is a mathOpNode
			else if (node instanceof MathOpNode) {
				MathOpNode m = (MathOpNode)node;
				// call expression() on left and right
				Node left = expression(m.getLeft());
				Node right = expression(m.getRight());
				// if left and right are the same node, and they are integerNode
				MathOpCal(left, right, m);// returns IDT
			} 
			// if n is a BooleanCompareNode
			else if (node instanceof BooleanCompareNode) {
				BoolFunc(node);//returns T or F
			}
			
			// below have a boolean compare and a collection of statements
			// evaluate the boolean compare, then call InterpretBlock()
			// if the boolean compare succeeds
			else if (node instanceof IfNode) {
				ifNode_func(node, map, s_list);// returns void
			}
			else if (node instanceof WhileNode) {
				WhileNode while_node = (WhileNode)node;
				BooleanCompareNode boolCompNode = while_node.getBoolCompNode();
				boolean BoolValue = BoolFunc(boolCompNode);	
				// repeat until condition is false
				while (BoolValue==true)
					InterpreterBlock(map, s_list);
			}
			else if (node instanceof RepeatNode) {
				RepeatNode repeat_node = (RepeatNode)node;
				BooleanCompareNode boolCompNode = repeat_node.getBoolCompNode();
				boolean BoolValue = BoolFunc(boolCompNode);
				// repeat until true, example: repeat until x=3;
				while (BoolValue == false)
					InterpreterBlock(map, s_list);
			}
			
			// below are straightforward, once evaluate() is completed
			else if (node instanceof ForNode) {
				ForNode for_node = (ForNode)node;
				// have to call the evaluate() function repeatedly to check and update
				while (evaluate(for_node) == true) 
					InterpreterBlock(map, s_list);
			}
			else if (node instanceof AssignmentNode) { 
				assignment(node, map, s_list);
			}
			
			// Constants Nodes, returns a new IDT
			else if (node instanceof IntegerNode) {
				IntegerNode intNode = (IntegerNode)node;
				new IntegerDataType(intNode.getIntegerNode());
			}
			else if (node instanceof RealNode) {
				RealNode realNode = (RealNode)node;
				new RealDataType(realNode.getRealNode());
			}
		}// end of looping for the collection of statementNodes
		
	}// end of InterpretBlock()
}
