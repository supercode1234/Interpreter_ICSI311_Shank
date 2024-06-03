
public class IntegerNode extends Node {
		private int integer=0;
		
		//  constructor
		public IntegerNode(int integer) {
			this.integer = integer;
		}

		// accessor(getter)
		public int getIntegerNode() {
			return integer;
		}

		@Override
		public String toString() {
			return integer+"";
		}
	
}