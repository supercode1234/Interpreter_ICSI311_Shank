//Haolin Wang
//CIS311
enum tokenType {
		IDENTIFIER,
		NUMBER,
		ENDOFLINE, WHILE, FOR, DEFINE, INTEGER, 
		REAL, STRING, CHAR,
		VARIABLES, CONSTANTS,EQUAL_SIGN,
		IF, THEN, COMMENT, STRINGLITERAL,
		CHARACTERLITERAL, INDENT, DEDENTLEVEL,
		PLUS, MINUS, DIVIDE,
		MULTIPLY, OPERATOR_MODULO, 
		PUNCTUATION, L_PARENT, R_PARENT,
		COLON, SEMICOLON, COMMA,
		LESS_THAN_SIGN, GREATER_THAN_SIGN,
		LESS_OR_EQUAL_SIGN, GREATER_OR_EQUAL_SIGN,
		COLON_EQUAL_SIGN, L_BRAC, R_BRAC,
}
class Token {

	
	private tokenType Token;
	private String value;
	
	
	public Token(tokenType Token, String value) {
		this.Token = Token;
		this.value = value;
	}
	
	// getter for token type
	public tokenType getType() {
		return Token;
	}

	// setter for string
	public String getValue() {
		return value;
	}
	
	public String toString() {
		if (Token == tokenType.ENDOFLINE) {
			return Token + "";
		} 
//		else if (Token == tokenType.COMMENT){
//			return "{" + builder + "}";
//		}

		return Token + "("+ value + ")";

	}
}



/*public Token (States state, String value) {
switch (state) {
case WORD_state:
	this.Token = tokenType.WORD;
	break;
case NUMBER_state:
	this.Token = tokenType.NUMBER;
	break;
case endofline_state:
	this.Token = tokenType.ENDOFLINE;
	break;
default:
	break;
}
this.value=value;
}*/