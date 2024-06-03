import java.util.ArrayList;
import java.util.Random;
public class BuiltInGetRandom extends FunctionNode {
	public void execute (ArrayList <InterpreterDataType> collect) {
		Random random = new Random();
		int randomNumber = random.nextInt(100);
	}
	@Override
	public boolean isVariadic() {
		return true;
	}

}
