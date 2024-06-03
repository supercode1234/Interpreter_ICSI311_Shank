import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

public class Parser {
	// arrayList that stores tokens
	public ArrayList<Token> tokenList_parse = new ArrayList<>();
	int intMember = 0, realMember = 0;

	/* Below are the 3 helpers */
	/* Match and Remove method */
	public Token matchAndRemove(tokenType tokenType1) {
		if (tokenType1 == tokenList_parse.get(0).getType()) {
			Token current = tokenList_parse.get(0);
			tokenList_parse.remove(0);
			return current;
		} 
		else 
			return null;
	}

	/* expectEndsOfLine() */
	public void expectEndsOfLine() throws Exception {
		/* add a loop */
		if (peek(0).getType() != tokenType.ENDOFLINE) {
			throw new Exception("There's no ENDOFLINE token");
		}
	}

	/* Peek() method */
	public Token peek(int a) {
		if (a > tokenList_parse.size()) {
			return null;
		}
		Token temp = tokenList_parse.get(a);
		return temp;
	}
	/* Above are the 3 helpers */

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * a constructor that accepts a collection of tokens (Unsure about usage)
	 */
	public Parser(ArrayList<Token> tokenList_parse) {
		//super();
		this.tokenList_parse = tokenList_parse;
	}
	
	

	/* parser 1 is below */
	/* Expression() */
	public Node expression() throws Exception {
		// calling term()
		Node termL = term();

		
		/* If there's no + or - sign */
		if (peek(0).getType() != tokenType.PLUS && peek(0).getType() != tokenType.MINUS) {
			return termL;
		}
		
		
		// problem, need to fix
		// how to prevent termLL from being overriden when this case happens 1+2+3
		// output: 1add3
		// expected: 1add2add3
		while (peek(0).getType() == tokenType.PLUS || peek(0).getType() == tokenType.MINUS) {
			// if the right term is null, throw exception
			
			// if there's a plus sign, construct termL + termR
			if (peek(0).getType() == tokenType.PLUS) {
				matchAndRemove(tokenType.PLUS);
				Node termR = term();
				if (termR == null) {
					throw new Exception("There's no right-term for expression()");
				}
				termL = new MathOpNode(mathOperation.add, termL, termR);

			}
			
			// else if there's a minus sign, construct termL - termR
			else if (peek(0).getType() == tokenType.MINUS) {
				matchAndRemove(tokenType.MINUS);
				Node termR = term();
				if (termR == null) {
					throw new Exception("There's no right-term for expression()");
				}
				termL = new MathOpNode(mathOperation.subtract, termL, termR);
			}
		} // end of while loop

		
		
		// construct the whole thing to be term
		return termL;

	}

	
	

	
	
	/* Term() method */
	public Node term() throws Exception {

		Token multiply = null, divide = null;
		Node left = factor();// looks for number


		/* if peek != multi or divide return left */
		if (peek(0).getType() != tokenType.MULTIPLY && peek(0).getType() != tokenType.DIVIDE) {
			return left;
		}

		while (peek(0).getType() == tokenType.MULTIPLY || peek(0).getType() == tokenType.DIVIDE) {

			if (matchAndRemove(tokenType.MULTIPLY) != null) {
				matchAndRemove(tokenType.MULTIPLY);
				// call factor again and THEN make a mathopnode with a multiply
				Node right = factor();
				left = new MathOpNode(mathOperation.multiply, left, right);

			} else if (matchAndRemove(tokenType.DIVIDE) != null) {
				matchAndRemove(tokenType.DIVIDE);
				// call factor again and THEN make a mathopNode with a divide
				Node right = factor();
				left = new MathOpNode(mathOperation.divide, left, right);
			}
		}
		
		return left;
	}
	
	
	
	
	
	

	/* Factor() method */
	public Node factor() throws Exception {
		// lparen EXPRESSION rparen
		Node factor = null;
		boolean negative = false;
		
		
		
		if (peek(0).getType() == tokenType.MINUS) {
			negative = true;
			Token neg = matchAndRemove(tokenType.MINUS);
			return factor;
		}
		
		
		// check for parenthesis
		if (peek(0).getType() == tokenType.L_PARENT) {
			Token lpar = matchAndRemove(tokenType.L_PARENT);
			factor = expression();
			Token rpar = matchAndRemove(tokenType.R_PARENT);
			if (rpar == null) {
				throw new Exception("Right parenthesis missing");
			}
			return factor;
		}

		
		// check for number
		Token num = null;
		Token var_ref = null;
		if (peek(0).getType() == tokenType.NUMBER) {
			num = matchAndRemove(tokenType.NUMBER);
		} else if (peek(0).getType() == tokenType.IDENTIFIER) {
			var_ref = matchAndRemove(tokenType.IDENTIFIER);
			return new VariableReferenceNode(var_ref.getValue());
		}
		if (num == null && var_ref == null) {
			throw new Exception("There's no number or var_ref!");
		} else if (num != null && num.getValue().contains(".")) {
			return new RealNode(Float.parseFloat(num.getValue()));
		} else if (num != null && !num.getValue().contains(".")) {
			return new IntegerNode(Integer.parseInt(num.getValue()));
		} else if (var_ref != null) {
			return new VariableReferenceNode(var_ref.getValue());
		} else {
			return null;
		}

	} /* end of factor() */
	/* parser 1 is above */

	
	
	
	
	String id_test = "f";
	Token func_ID = null; // important, for fucntion_ID.
	
	
	/* parse() */
	/* main output method, important */
	public Node parse() throws Exception {
		/* Handles DEFINE function below */
		if (peek(0).getType() == tokenType.DEFINE) {
			ProgramNode program = new ProgramNode();
			FunctionNode funcNode = function();
			

			program.collect_functionNode.put(func_ID.getValue(), funcNode); // can't figure this out
			ArrayList paramList = parameterDeclarations();// might need again

			
			
			System.out.println("TokenList in parse" + tokenList_parse);
			return program;// I guess doesn't matter what it returns
		} /* Handles DEFINE above */

		
		
		
		
		
		
		/* Handles numbers below */	//ignore below
		else if (peek(0).getType() == tokenType.IDENTIFIER || peek(0).getType() == tokenType.NUMBER) {
			Node exp = null;
//			if (peek(0).getType() == tokenType.IDENTIFIER|| 
//					peek(0).getType() == tokenType.NUMBER) {
			// peek(0).getType() == tokenType.GREATER_THAN_SIGN
			// peek(0).getType() == tokenType.PLUS
//				exp = expression();
//				System.out.println(exp);
//				while (!tokenList_parse.isEmpty()) {// peek(0).getType() == tokenType.NUMBER &&
//					exp = expression();
//					expectEndsOfLine();
//					matchAndRemove(tokenType.ENDOFLINE);
//					System.out.println(exp);
//				}// important
			// }
			// if(peek(0).getType() == tokenType.GREATER_THAN_SIGN) {
//				bool = boolCompare();
//				System.out.println(bool);
//				while (!tokenList_parse.isEmpty()) {
//					bool = boolCompare();
//					expectEndsOfLine();
//					matchAndRemove(tokenType.ENDOFLINE);
//					System.out.println(bool);
//				}// important

			while (!tokenList_parse.isEmpty()) {
				Node assign = assignment();
				System.out.println(assign);
			}
//				while (!tokenList_parse.isEmpty()) {
//					assign = boolCompare();
//					expectEndsOfLine();
//					matchAndRemove(tokenType.ENDOFLINE);
//					System.out.println(assign);
//				}
			// }
//			expectEndsOfLine(); // might need these 2
//			matchAndRemove(tokenType.ENDOFLINE);

			// System.out.println(z);
//			while (!tokenList_parse.isEmpty()) {// peek(0).getType() == tokenType.NUMBER &&
//				x = expression();
//				expectEndsOfLine();
//				matchAndRemove(tokenType.ENDOFLINE);
//				System.out.println(x);
//			}
			System.out.println("parser arrayList content: " + tokenList_parse);
			return exp;
			// return assign;

		} /* Handles numbers above */
		
		
		
		
		// return new ProgramNode();
		return null;
		
		
	} /* End of parse() */

	
	
	
	
	
	
	
	
	
	/* parser 2 is below */
	/* processes a function */
	public FunctionNode function() throws Exception { // should it be Node or func_node?
		FunctionNode functNode = new FunctionNode(null);
		VariableNode paramNode = null;
		VariableNode var_const = null;
		// Example adding the variable to the function's parameterlist
		// functNode.addToParameterList(paramNode);
		ArrayList<VariableNode> parameterList = new ArrayList<>();
		ArrayList<VariableNode> variableList = new ArrayList<>();

		/* 1st line of function */
		Token define = matchAndRemove(tokenType.DEFINE);
		if (define == null) {
			throw new Exception("There's no DEFINE token");
		}
		
		func_ID = matchAndRemove(tokenType.IDENTIFIER);
		if (func_ID == null) {
			throw new Exception("There's no function name");
		}
		
		Token L_P = matchAndRemove(tokenType.L_PARENT);
		if (L_P == null) {
			throw new Exception("There's no left parent in function declaration");
		}

		/* Empty parameter */
		if (peek(0).getType() != tokenType.IDENTIFIER) {
			Token R_P1 = matchAndRemove(tokenType.R_PARENT);
			if (R_P1 == null) {
				throw new Exception("Right paren is missing");
			}
		}

		/* Non-empty parameter */
		else if (peek(0).getType() == tokenType.IDENTIFIER) {
			Token ID2 = matchAndRemove(tokenType.IDENTIFIER);
			if (ID2 == null) {
				throw new Exception("Invalid input for parameter");
			}
			Token colon = matchAndRemove(tokenType.COLON);
			if (colon == null) {
				throw new Exception("It's not a colon");
			}

			/* check for data types */
			Token func_type = null;
			variableType var_type = null;
			if (peek(0).getType() == tokenType.INTEGER) {
				func_type = matchAndRemove(tokenType.INTEGER);
				var_type = variableType.INTEGER;
				var_const = new VariableNode(ID2.toString(), var_type, true);
			} else if (peek(0).getType() == tokenType.REAL) {
				func_type = matchAndRemove(tokenType.REAL);
				var_type = variableType.REAL;
				var_const = new VariableNode(ID2.toString(), var_type, true);
			} else if (peek(0).getType() == tokenType.STRING) {
				func_type = matchAndRemove(tokenType.STRING);
			} else if (peek(0).getType() == tokenType.CHAR) {
				func_type = matchAndRemove(tokenType.CHAR);
			} else {
				throw new Exception("Wrong data type");
			}
			var_const = null;
			
			/* Loop, when it sees semicolon */
			while (peek(0).getType() == tokenType.SEMICOLON) {
				Token semic = matchAndRemove(tokenType.SEMICOLON);
				if (semic == null) {
					throw new Exception("There should be a semicolon");
				} else if (peek(0).getType() == tokenType.IDENTIFIER) {
					Token ID3 = matchAndRemove(tokenType.IDENTIFIER);
					if (ID3 == null) {
						throw new Exception("Invalid input for parameter");
					}
					Token colon1 = matchAndRemove(tokenType.COLON);
					if (colon1 == null) {
						throw new Exception("It's not a colon");
					}

					/* check for data types */
					if (peek(0).getType() == tokenType.INTEGER) {
						func_type = matchAndRemove(tokenType.INTEGER);
						var_type = variableType.INTEGER;
						// instead of toString, do getValue()?
						var_const = new VariableNode(ID3.toString(), var_type, true);
					} else if (peek(0).getType() == tokenType.REAL) {
						func_type = matchAndRemove(tokenType.REAL);
						var_type = variableType.REAL;
						var_const = new VariableNode(ID3.toString(), var_type, true);
					}
					// should I only do numbers?
					else if (peek(0).getType() == tokenType.STRING) {
						func_type = matchAndRemove(tokenType.STRING);
					} else if (peek(0).getType() == tokenType.CHAR) {
						func_type = matchAndRemove(tokenType.CHAR);
					} else {
						throw new Exception("Wrong data type");
					}
				}
			} /* end of while loop */

			Token R_P2 = matchAndRemove(tokenType.R_PARENT);
			if (R_P2 == null) {
				throw new Exception("There's no right parent in function declaratioin");
			}
		} /* end of non-empty parameter */
		/* end of 1st line of function */

		// delete EOF, so that we can begin next line
		expectEndsOfLine();
		matchAndRemove(tokenType.ENDOFLINE);

		// VariableNode var_const = null;
		/* next 2 lines in function */

		/* while loop has problem */
		while ((peek(0).getType() == tokenType.CONSTANTS || peek(0).getType() == tokenType.VARIABLES)
				&& peek(0).getType() != tokenType.INDENT && !tokenList_parse.isEmpty()
				&& peek(0).getType() != tokenType.ENDOFLINE) {
			
			/* Start of constant type */
			if (peek(0).getType() == tokenType.CONSTANTS) {
				matchAndRemove(tokenType.CONSTANTS);

				if (peek(0).getType() != tokenType.IDENTIFIER) {
					throw new Exception("Wrong or no constant type name");
				}

				while (peek(0).getType() == tokenType.IDENTIFIER) {
					Token const1_name = matchAndRemove(tokenType.IDENTIFIER);
					variableType const1 = null;
					// matchAndRemove(tokenType.CONSTANTS);
					if (peek(0).getType() == tokenType.EQUAL_SIGN) {
						matchAndRemove(tokenType.EQUAL_SIGN);
						if (peek(0).getType() == tokenType.NUMBER) {
							Token const1_value = matchAndRemove(tokenType.NUMBER);
							if (const1_value.getValue().contains(".")) {
								const1 = variableType.REAL;
								float const_real_num = Float.parseFloat(const1_value.getValue());
								RealNode const_real = new RealNode(const_real_num);
								var_const = new VariableNode(const1_name.getValue(), const1, false, tokenType.CONSTANTS,
										const_real);
								variableList.add(var_const);
								functNode.setVariable(variableList);
							} else {
								const1 = variableType.INTEGER;
								int const_int_num = Integer.parseInt(const1_value.getValue());
								IntegerNode const_int = new IntegerNode(const_int_num);
								var_const = new VariableNode(const1_name.getValue(), const1, false, tokenType.CONSTANTS,
										const_int);
								variableList.add(var_const);
								functNode.setVariable(variableList);
							}
						} else {
							throw new Exception("Wrong or no number for constants type");
						}
					} else {
						throw new Exception("no equal sign");
					}
				}
				matchAndRemove(tokenType.ENDOFLINE); // giving trouble in the loop
			} /* End of constant type */

			
			
			
			/* Start of variable type */
			else if (peek(0).getType() == tokenType.VARIABLES) {
				matchAndRemove(tokenType.VARIABLES);
				Token varT;
				variableType varV;
				if (peek(0).getType() == tokenType.IDENTIFIER) { // or while?
					varT = matchAndRemove(tokenType.IDENTIFIER);
				} else {
					throw new Exception("Wrong or no variable name");
				}
				while (peek(0).getType() == tokenType.COMMA) {
					matchAndRemove(tokenType.COMMA);
					varT = matchAndRemove(tokenType.IDENTIFIER);
					if (varT == null) {
						throw new Exception("Wrong or no ID after comma in variable type");
					}
				}
				if (peek(0).getType() == tokenType.COLON) {
					matchAndRemove(tokenType.COLON);
				} else {
					throw new Exception("There should be a colon for variable");
				}
				Token var_type = null;
				if (peek(0).getType() == tokenType.INTEGER) {
					var_type = matchAndRemove(tokenType.INTEGER);
					varV = variableType.INTEGER;
					var_const = new VariableNode(varT.getValue(), varV, true, tokenType.VARIABLES);
					variableList.add(var_const);
					functNode.setVariable(variableList);
				} else if (peek(0).getType() == tokenType.REAL) {
					var_type = matchAndRemove(tokenType.REAL);
					varV = variableType.REAL;
					var_const = new VariableNode(varT.getValue(), varV, true, tokenType.VARIABLES);
					variableList.add(var_const);
				} // only 2 data types
				else {
					throw new Exception("Wrong or no data type for variables");
				}
				matchAndRemove(tokenType.ENDOFLINE);
			}
		} /* End of variable type */
		/* End of variable initialization */
		
		
		// call statements()
		statements();


		// then a dedent
//			ProgramNode program = new ProgramNode();
//			program = null;
//			//Node y = null;
//			FunctionNode funcNode = null;
//			funcNode = function(); // returns function
//			//program = function();
//			program.collect_functionNode.put("start1", funcNode);
		return new FunctionNode(func_ID.getValue());
	}/* end of function() */
	
	
	
	
	

	/* parser 2 */
	/* process parameters */
	public ArrayList parameterDeclarations(/* arrayList a, */) {
		ArrayList<VariableNode> parameterList = new ArrayList<>();
		FunctionNode functNode = new FunctionNode(null);
		VariableNode var_const = new VariableNode("hi, just testing", variableType.INTEGER, false, tokenType.CONSTANTS);
		parameterList.add(var_const);
		functNode.setParameter(parameterList);
		
		
		// process the parameters and returns a collection of VariableNode
		return parameterList;
	}
	/* parser 2 is above */

	
	
	
	
	/* parser 3 is below */
	public Node boolCompare() throws Exception {
		Node left = expression();
		if (peek(0).getType() == tokenType.GREATER_THAN_SIGN) {
			matchAndRemove(tokenType.GREATER_THAN_SIGN);
			Node right = expression();
			left = new BooleanCompareNode(boolCompare.greater_than, left, right);
			return left;
		} else if (peek(0).getType() == tokenType.LESS_THAN_SIGN) {
			matchAndRemove(tokenType.LESS_THAN_SIGN);
			Node right = expression();
			left = new BooleanCompareNode(boolCompare.less_than, left, right);
			return left;
		} else if (peek(0).getType() == tokenType.LESS_OR_EQUAL_SIGN) {
			matchAndRemove(tokenType.LESS_OR_EQUAL_SIGN);
			Node right = expression();
			left = new BooleanCompareNode(boolCompare.less_or_equal, left, right);
			return left;
		} else if (peek(0).getType() == tokenType.GREATER_OR_EQUAL_SIGN) {
			matchAndRemove(tokenType.GREATER_OR_EQUAL_SIGN);
			Node right = expression();
			if (right == null) {
				throw new Exception("Missing right side for bool()");
			} else {
				left = new BooleanCompareNode(boolCompare.greater_or_equal, left, right);
				return left;
			}
		} else if (peek(0).getType() == tokenType.EQUAL_SIGN) {
			matchAndRemove(tokenType.EQUAL_SIGN);
			Node right = expression();// was factor()
			left = new BooleanCompareNode(boolCompare.equal, left, right);
			return left;
		} 
		else {
			return left;
		}
	} /* End of boolCompare() */

	
	
	
	
	public AssignmentNode assignment() throws Exception {
		AssignmentNode assignmentNode = null;
		Token assign_left = matchAndRemove(tokenType.IDENTIFIER); // IDENTIFIER
		VariableReferenceNode left = new VariableReferenceNode(assign_left.getValue());
		if (assign_left != null) {
			left = new VariableReferenceNode(assign_left.getValue());
			matchAndRemove(tokenType.COLON_EQUAL_SIGN);
			Node right = boolCompare();
			matchAndRemove(tokenType.ENDOFLINE);
			return assignmentNode = new AssignmentNode(left, right);
		}
		return null;
	}

	/* Statement(no s) */
	public StatementNode statement() throws Exception {
		if (!tokenList_parse.isEmpty()) {
			if (peek(0).getType() == tokenType.IDENTIFIER)
				return assignment();
			else
				return null;
		}
		return null;
	}

	public ArrayList<StatementNode> statements() throws Exception {
		ArrayList<StatementNode> stateNodeList = new ArrayList<>();
		
		if (peek(0).getType() == tokenType.INDENT) {
			matchAndRemove(tokenType.INDENT);
		} else {
			throw new Exception("There should be an indent");
		}
		
		
		
		// how to make it to return null?
		StatementNode statement = statement();
		while (statement != null) {
			stateNodeList.add(statement);
			//System.out.println("Testing statements()-> "+ statement);
			statement = statement();
		}
		// matchAndRemove(tokenType.INDENTLEVEL);

//		 else if(peek(0).getType() == tokenType.IF) {
//			 parseIf();
//			 //matchAndRemove(tokenType.ENDOFLINE);
//		 } else if (peek(0).getType() == tokenType.WHILE) {
//			 parseWhile();
//		 } else if (peek(0).getType() == tokenType.FOR) {
//			 parserFor();
//		 }
//		 else {
//			 //throw new Exception("there should be statements");
//		 }
		
		
		
//		if (peek(0).getType() == tokenType.DEDENTLEVEL) {
//			matchAndRemove(tokenType.DEDENTLEVEL);
//		} else {
//			//throw new Exception("There should be a dedent");
//		}
		return stateNodeList;
	}
	/* parser 3 is above */

	/* parser 4 is below */
	LinkedList<String> parse4 = new LinkedList<String>();

	public void parserFor() throws Exception {
		ArrayList<StatementNode> FORstatementList = new ArrayList<>();
		if (peek(0).getType() == tokenType.FOR) {
			matchAndRemove(tokenType.FOR);
			Node boo = boolCompare();
			System.out.println("For " + boo);
			// put things into the arrayList
		} else {
			throw new Exception("There should be a FOR");
		}
	}

	public void parseWhile() throws Exception {
		ArrayList<StatementNode> WHILEstatementList = new ArrayList<>();
		if (peek(0).getType() == tokenType.WHILE) {
			matchAndRemove(tokenType.WHILE);
			Node boo = boolCompare();
			System.out.println("while " + boo);
		} else {
			throw new Exception("There should be a WHILE");
		}
	}

	public void parseIf() throws Exception {
		ArrayList<StatementNode> IFstatementList = new ArrayList<>();
		if (peek(0).getType() == tokenType.IF) {
			matchAndRemove(tokenType.IF);
			Node boo = boolCompare();
			if (peek(0).getType() == tokenType.IDENTIFIER) {
				matchAndRemove(tokenType.IDENTIFIER);
				matchAndRemove(tokenType.ENDOFLINE);
				matchAndRemove(tokenType.INDENT);
				matchAndRemove(tokenType.INDENT);
			} else {
				throw new Exception("There should be a 'THEN'");
			}
			Node assign = assignment();
			// Node sign = boolCompare();
			matchAndRemove(tokenType.INDENT);
			matchAndRemove(tokenType.ENDOFLINE);
			IfNode ifNode = new IfNode(null);
			ifNode.setParameter(IFstatementList);
			System.out.println("if " + boo + ", " + assign + IFstatementList);
		}
	}

	public void parseFunctionCalls() {

	}
	/* parser 4 is above */

}
