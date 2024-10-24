//Final Submission
package mainApp;

public abstract class Item extends Actor {
	protected int pointValue;

	public Item(double x, double y) {
		super(x, y);
	}

	public int getPointValue() {
		return this.pointValue;
	}

}
