//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Bomb extends Item {

	protected static int HEIGHT = 20;
	protected static int WIDTH = 20;
	protected static int BOMB_POINT_VALUE = 200;

	public Bomb(double x, double y) {
		super(x, y);

		this.pointValue = BOMB_POINT_VALUE;

		this.hitBoxHeight = HEIGHT;
		this.hitBoxWidth = WIDTH;
		this.hitBox = new Rectangle.Double(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);
	}

	protected void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fill(new Ellipse2D.Double(super.posX, super.posY, this.hitBoxHeight, this.hitBoxWidth));
		g2.fill(new Rectangle.Double(super.posX + 5, super.posY - 2, 10, 5));
	}
}
