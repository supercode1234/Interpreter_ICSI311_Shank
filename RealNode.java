/* Parser 1 */
public class RealNode extends Node {
	private float real = 0;
	// constructor that accepts a float memebr
	public RealNode(float real) {
		this.real = real;
	}
	// accessor 
	public float getRealNode() {
		return real;
	}
	@Override
	public String toString() {
		return real+"";
	}
	
}
