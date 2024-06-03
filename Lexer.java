
//Haolin Wang
//CIS311
//Assignment 3
import java.util.ArrayList;
import java.util.HashMap;

enum States {
	BEGIN_state, // beginning state
	NUMBER_state, // digits state
	IDENTIFIER_state, // letters state
	DECIMAL_state, COMMENT_state, INDENT_state, DEDENT_state, STRINGLITERAL_state, CHARACTERLITERAL_state,
	PUNCTUATION_state, endofline_state // end of line state
}

public class Lexer {
	/* an array list that stores tokens */
	public static ArrayList<Token> tokenList = new ArrayList<>();
	private Token token_store;
	private float previousIndent = 0;

	/* a hashMap that contains known words */
	static HashMap<String, tokenType> knownWords = new HashMap<String, tokenType>();
	// knownWords.put("while",Token.tokenType.WHILE);
	boolean doWeHaveWhile = knownWords.containsKey("while");
	tokenType whileType = knownWords.get("while");

	// The lex () method
	public void lex(String input) throws Exception {
		/* variables for indent */
		int indentLV=0, prevIndentLV,  
				currInLV=0, dedentCount = 0, lineNumber = 0;
		double indentCountSpace = 0;
		/* putting known words in the tokenList */
		knownWords.put("while", tokenType.WHILE);
		knownWords.put("for", tokenType.FOR);
		knownWords.put("define", tokenType.DEFINE);
		knownWords.put("variables", tokenType.VARIABLES);
		knownWords.put("constants", tokenType.CONSTANTS);
		knownWords.put("if", tokenType.IF);
		// Below are data types
		knownWords.put("integer", tokenType.INTEGER);
		knownWords.put("real", tokenType.REAL);
		knownWords.put("string", tokenType.STRING);
		knownWords.put("char", tokenType.CHAR);
		// Above are data types

		/* initialize the current state as BEGIN_state */
		States state = States.BEGIN_state;
		/* a string builder that build words */
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);

			/* check for identation or spaces in the front */
			if (currentChar == '\t' || currentChar == ' ') {
				/* tabs */
				
				if (currentChar == '\t') {
					//System.out.println(indentLV);
					indentLV++;
					prevIndentLV=indentLV;
					System.out.println(prevIndentLV);
					//if (indentLV>prevIndentLV) {
					token_store = new Token(tokenType.INDENT, "LV" + indentLV);
					tokenList.add(token_store);
					//}

				}
				/* spaces */
				else {
					indentCountSpace = indentCountSpace + 0.25;
//					if ((int)indentCountSpace >indentCount) {
//						//System.out.println("fahfjlaaaa");
//						// if (identCountSpace == 1||2)
//						indentCount++;
//						token_store = new Token(tokenType.INDENTLEVEL, ""+indentCount);
//						tokenList.add(token_store);
//					}
					// can't handle tab then space
				}
				/* End of indent */

				/* handles dedent */
				prevIndentLV = indentLV;
				// System.out.print("tempIndenLv is: " + tempIndentLV);
				if (indentLV < prevIndentLV) {
					dedentCount = prevIndentLV;
					token_store = new Token(tokenType.DEDENTLEVEL, "" + dedentCount);
					tokenList.add(token_store);
				}
			}
			/* end of dedent */

			/* check the current state (switch) */
			switch (state) {
			case BEGIN_state: /* Begin state */
				// if the current character is a digit, move to NUMBER_state
				if (Character.isDigit(currentChar)) {
					state = States.NUMBER_state;
					builder.append(currentChar);
					if (i + 1 == input.length()) {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
					}
				}

				// if the current character is a letter, move to IDENTIFIER_state
				else if (Character.isLetter(currentChar)) {
					// System.out.println("Testing id");
					state = States.IDENTIFIER_state;
					builder.append(currentChar);
					if (i + 1 == input.length()) {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
					}
				} else if (currentChar == '.') {
					state = States.DECIMAL_state;
					builder.append(currentChar);
				} else if (currentChar == '{') {
					state = States.COMMENT_state;
				} else if (currentChar == '"') {
					state = States.STRINGLITERAL_state;
				} else if (currentChar == '\'') {
					state = States.CHARACTERLITERAL_state;
				}

				/* check for operators & special characters */
				else if (currentChar == '+') {
					token_store = new Token(tokenType.PLUS, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '-') {
					token_store = new Token(tokenType.MINUS, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '*') {
					token_store = new Token(tokenType.MULTIPLY, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '/') {
					token_store = new Token(tokenType.DIVIDE, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '(') {
					token_store = new Token(tokenType.L_PARENT, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == ')') {
					token_store = new Token(tokenType.R_PARENT, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == ':') {
					token_store = new Token(tokenType.COLON, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == ';') {
					token_store = new Token(tokenType.SEMICOLON, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '=') {
					token_store = new Token(tokenType.EQUAL_SIGN, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == ',') {
					token_store = new Token(tokenType.COMMA, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				} else if (currentChar == '<') {
					token_store = new Token(tokenType.LESS_THAN_SIGN, String.valueOf(currentChar));
					tokenList.add(token_store);
					state = States.BEGIN_state;
				}
				break; /* End of begin state */

			case NUMBER_state:
				// if the current character is a digit, add it to the current token
				if (currentChar == ' ' /* || i + 1 == input.length() */) {
					// Create number token
					// builder.append(currentChar);

					// below if causing error
//					if (i + 1 == input.length()) {
//						builder.append(currentChar);
//					}//need this if don't have i--
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					// System.out.println("Testing i--, above setlength(0)"+i);
					builder.setLength(0);
					// System.out.println("Testing i--"+i);
					// i=0;// or below. i is currently 1. NVM can't do this,
					// it's an infinite loop
					i--;// crucial
					// System.out.println("Testing i--"+i);
				} else if (Character.isDigit(currentChar) || currentChar == '.') {
					builder.append(currentChar);
					// token_store = new Token(state, builder.toString());
					// tokenList.add(token_store);
				} else if (currentChar == '{') {
					state = States.COMMENT_state;
				} else if (currentChar == '"') {
					state = States.STRINGLITERAL_state;
				}
				/*
				 * else if (currentChar == '+') { //System.out.println("+"); token_store = new
				 * Token (tokenType.OPERATOR_PLUS, String.valueOf(currentChar));
				 * tokenList.add(token_store); state = States.OPERATOR_PLUS_state; }
				 */

				else if (currentChar == '+') {
					// make a number token if encounters a number
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.PLUS, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						// Number token
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
					// state = States.OPERATOR_PLUS_state;
				} else if (currentChar == '-') {
					// make a number token if encounters a number
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.MINUS, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						// Number token
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
				} else if (currentChar == '*') {
					/* make a number token if encounters a number */
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.MULTIPLY, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
				} else if (currentChar == '/') {
					// make a number token if encounters a number
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					// System.out.println("Testing /");
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.DIVIDE, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						// Number token
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
				}
				else if (currentChar == ']') {
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.R_BRAC, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						// Number token
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
				}
				
				else if (currentChar == ')') {
					// make a number token if encounters a number
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.R_PARENT, String.valueOf(currentChar));
					tokenList.add(token_store);
					if (Character.isDigit(currentChar)) {
						// Number token
						builder.append(currentChar);
						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
						tokenList.add(token_store);
						// state = States.NUMBER_state;
					}
				} else if (input.charAt(i) == '<') {
					if (input.charAt(i + 1) == '=') {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.LESS_OR_EQUAL_SIGN, String.valueOf("<="));
						tokenList.add(token_store);
						i = i + 1;// clever, "<=" is also clever. No need String.valeuOf for "<="
					} else {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.LESS_THAN_SIGN, String.valueOf(currentChar));
						tokenList.add(token_store);
					}
				}

				else if (currentChar == '>') {
					if (input.charAt(i + 1) == '=') {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.GREATER_OR_EQUAL_SIGN, ">=");
						tokenList.add(token_store);
						i = i + 1;
					} else {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.GREATER_THAN_SIGN, String.valueOf(currentChar));
						tokenList.add(token_store);
					}
				}

				else {
					// System.out.print("NUMBER(" + builder.toString()+")");
					state = States.BEGIN_state;
					token_store = new Token(tokenType.NUMBER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
				}
				break;

			case IDENTIFIER_state:
				if (Character.isLetter(currentChar) || Character.isDigit(currentChar)) {
					builder.append(currentChar);
					// token_store = new Token(state, builder.toString());
					// tokenList.add(token_store);
				} /* else */
				// if(currentChar ==' ' || i+1 == input.length()){
				if (currentChar == ' ') {
					// or just else{}
					// Create word token
					/*
					 * token_store = new Token(tokenType.IDENTIFIER, builder.toString());
					 * tokenList.add(token_store); //System.out.print("WORD(" +
					 * builder.toString()+")"); builder.setLength(0); state = States.BEGIN_state;
					 */
					// i--;

					if (knownWords.containsKey(builder.toString())) {
						// new token
						/*
						 * if(builder.toString().equals("while")) { token_store = new
						 * Token(tokenType.WHILE, builder.toString()); tokenList.add(token_store); }else
						 * if()
						 */
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
					} else if (currentChar == '{') {
						state = States.COMMENT_state;
					} else if (currentChar == '"') {
						state = States.STRINGLITERAL_state;
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
					}
					state = state.BEGIN_state; // purpose?

				} else if (currentChar == '+') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}// else is important
					builder.setLength(0);
					token_store = new Token(tokenType.PLUS, String.valueOf(currentChar));
					tokenList.add(token_store);
//					if (Character.isDigit(currentChar)) {
//						// Number token
//						builder.append(currentChar);
//						token_store = new Token(tokenType.NUMBER, String.valueOf(currentChar));
//						tokenList.add(token_store);
//						// state = States.NUMBER_state;
//					}
				} else if (currentChar == '-') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token (knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token (tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token (tokenType.MINUS, String.valueOf(currentChar));
					tokenList.add(token_store);
				}
				else if (currentChar == '*') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token (knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token (tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token (tokenType.MULTIPLY, String.valueOf(currentChar));
					tokenList.add(token_store);
				}
				else if (currentChar == '/') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token (knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token (tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token (tokenType.DIVIDE, String.valueOf(currentChar));
					tokenList.add(token_store);
				}
				else if (currentChar == ':') {
					if (input.charAt(i + 1) == '=') {
						state = States.BEGIN_state;
						if (knownWords.containsKey(builder.toString())) {
							token_store = new Token(knownWords.get(builder.toString()), builder.toString());
							tokenList.add(token_store);
						} else {
							token_store = new Token (tokenType.IDENTIFIER, builder.toString());
							tokenList.add(token_store);
						}
						builder.setLength(0);
						token_store = new Token (tokenType.COLON_EQUAL_SIGN, ":=");
						tokenList.add(token_store);
						i=i+1;
					} else {
						state = States.BEGIN_state;
						if (knownWords.containsKey(builder.toString())) {
							token_store = new Token(knownWords.get(builder.toString()), builder.toString());
							tokenList.add(token_store);
						} else {
							token_store = new Token(tokenType.IDENTIFIER, builder.toString());
							tokenList.add(token_store);
						}
						builder.setLength(0);
						token_store = new Token(tokenType.COLON, String.valueOf(currentChar));
						tokenList.add(token_store);
					}
				} else if (currentChar == '(') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token(tokenType.L_PARENT, String.valueOf(currentChar));
					tokenList.add(token_store);
				} 
				else if (currentChar == '[') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token(tokenType.L_BRAC, String.valueOf(currentChar));
					tokenList.add(token_store);
				}
				
				else if (currentChar == ']') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token(tokenType.R_BRAC, String.valueOf(currentChar));
					tokenList.add(token_store);
				}
				
				else if (currentChar == ')') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token(tokenType.R_PARENT, String.valueOf(currentChar));
					tokenList.add(token_store);
				} else if (currentChar == ';') {
					state = States.BEGIN_state;
					if (knownWords.containsKey(builder.toString())) {
						token_store = new Token(knownWords.get(builder.toString()), builder.toString());
						tokenList.add(token_store);
					} else {
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
					}
					builder.setLength(0);
					token_store = new Token(tokenType.SEMICOLON, String.valueOf(currentChar));
					tokenList.add(token_store);
				} else if (currentChar == ',') {
					state = States.BEGIN_state;
					token_store = new Token(tokenType.IDENTIFIER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.COMMA, String.valueOf(currentChar));
					tokenList.add(token_store);
				} else if (currentChar == '=') {
					state = States.BEGIN_state;
					token_store = new Token(tokenType.IDENTIFIER, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					token_store = new Token(tokenType.EQUAL_SIGN, String.valueOf(currentChar));
					tokenList.add(token_store);

				} else if (currentChar == '<') {
					if (input.charAt(i + 1) == '=') {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.LESS_OR_EQUAL_SIGN, "<=");
						tokenList.add(token_store);
						i = i + 1;
					} else {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.LESS_THAN_SIGN, String.valueOf(currentChar));
						tokenList.add(token_store);
					}
				} else if (currentChar == '>') {
					if (input.charAt(i + 1) == '=') {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.GREATER_OR_EQUAL_SIGN, ">=");
						tokenList.add(token_store);
						i = i + 1;
					} else {
						state = States.BEGIN_state;
						token_store = new Token(tokenType.IDENTIFIER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
						token_store = new Token(tokenType.GREATER_THAN_SIGN, String.valueOf(currentChar));
						tokenList.add(token_store);
					}
				}
				break; /* End of Identifier state */

			case DECIMAL_state:
				if (Character.isDigit(currentChar)) {
					builder.append(currentChar);
					state = States.NUMBER_state;
					if (i + 1 == input.length()) {
						// System.out.print("NUMBER(" + builder.toString()+")");
						state = States.BEGIN_state;
						token_store = new Token(tokenType.NUMBER, builder.toString());
						tokenList.add(token_store);
						builder.setLength(0);
					}
				} else if (currentChar == '{') {
					state = States.COMMENT_state;
				} else if (currentChar == '"') {
					state = States.STRINGLITERAL_state;
				} else {
					// System.out.print("NUMBER("+ builder.toString()+")");
					// System.out.print("There's an error");
					// builder.setLength(0);
					// state = States.BEGIN_state;
					// i--;
					throw new Exception("There's an error with decimal point!");
				}
				break; /* End of decimal state */

			case COMMENT_state:
				// if (currentChar == '{') {
				if (currentChar == '}') {
					state = States.BEGIN_state;
					// builder = builder index-1 ;
					// moved inside the bottom else backet
					// to get rid of } at the sentence
					token_store = new Token(tokenType.COMMENT, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
					// scan things until it hits }
				} else if (currentChar == '"') {
					state = States.STRINGLITERAL_state;
				}
				// cleaver else placement
				else {
					/*
					 * the location of this else is important to reduce the } at the end of the
					 * sentence
					 */
					builder.append(currentChar);
				}

				// if detects String literal or character literal
				// create appropriate token types.
			case STRINGLITERAL_state:
				if (currentChar == '"') {
					state = States.BEGIN_state;
					// builder = builder index-1 ;
					token_store = new Token(tokenType.STRINGLITERAL, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
				}
				// clever else placement
				else {
					builder.append(currentChar);
				}
				break;

			case CHARACTERLITERAL_state:
				if (currentChar == '\'') {
					state = States.BEGIN_state;
					token_store = new Token(tokenType.CHARACTERLITERAL, builder.toString());
					tokenList.add(token_store);
					builder.setLength(0);
				} else {
					builder.append(currentChar);
				}
				break;
			/*
			 * case endofline_state: if(currentChar == '\n' ) { token_store = new
			 * Token(tokenType.ENDOFLINE, builder.toString());
			 * //System.out.println("ENDOFLINE"); } break;
			 */
			}
		}

		if (state == States.IDENTIFIER_state) {
			// System.out.println("Testing id");
			if (knownWords.containsKey(builder.toString())) {
				token_store = new Token(knownWords.get(builder.toString()), builder.toString());
				tokenList.add(token_store);
			} else {
				token_store = new Token(tokenType.IDENTIFIER, builder.toString());
				tokenList.add(token_store);
			}
		} else if (state == States.NUMBER_state) {
			// System.out.println("Testing id");
			// was causing problem, IDENTIFIER(65)
			token_store = new Token(tokenType.NUMBER, builder.toString());
			tokenList.add(token_store);
		}

		token_store = new Token(tokenType.ENDOFLINE, "");
		tokenList.add(token_store);
//		lineNumber++;
//		System.out.println("Line number is: "+ lineNumber);

		// System.out.println(" ENDOFLINE");
	} /* End of the lex() method */

}
