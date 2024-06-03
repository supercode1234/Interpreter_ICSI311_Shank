
public class CharacterDataType extends InterpreterDataType {
	private char cha;
	CharacterDataType(char cha){
		this.cha = cha;
	}
	@Override
	public String ToString() {
		return cha+"";
	}

	@Override
	public void FromString(String input) {
		cha = input.charAt(0);
	}

}
