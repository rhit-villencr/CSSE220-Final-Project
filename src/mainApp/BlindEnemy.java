//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class BlindEnemy extends Enemy {

	protected static int HEIGHT = 20;
	protected static int WIDTH = 30;
	protected static int BLIND_POINT_VALUE = 50;

	public BlindEnemy(double x, double y) {
		super(x, y);
		this.speedY = 0;
		this.speedX = 3;
		this.killable = true;
		this.hitBoxHeight = HEIGHT;
		this.hitBoxWidth = WIDTH;
		this.hitBox = new Rectangle.Double(this.posX + 5, this.posY, this.hitBoxWidth + 5, this.hitBoxHeight);
		this.velX = this.speedX;
	}

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int[] xSLeft = { (int) (this.posX + this.hitBoxWidth / 2), (int) (this.posX + this.hitBoxWidth / 2),
				(int) this.posX - 10 };
		int[] ySLeft = { (int) this.posY, (int) (this.posY + this.hitBoxHeight),
				(int) (this.posY + (this.hitBoxHeight / 2)) };
		int[] xSRight = { (int) (this.posX + this.hitBoxWidth / 2), (int) (this.posX + this.hitBoxWidth / 2),
				(int) (this.posX + this.hitBoxWidth + 10) };
		int[] ySRight = { (int) this.posY, (int) (this.posY + this.hitBoxHeight),
				(int) (this.posY + (this.hitBoxHeight / 2)) };

		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(this.posX + 5, this.posY, this.hitBoxWidth - 10, this.hitBoxHeight));
		g2.setColor(Color.BLACK);
		g2.draw(new Ellipse2D.Double(this.posX + 5, this.posY, this.hitBoxWidth - 10, this.hitBoxHeight));
		g2.setColor(Color.RED);
		if (this.velX > 0) {
			this.hitBox = new Rectangle.Double(this.posX + 5, this.posY, this.hitBoxWidth + 5, this.hitBoxHeight);
			g2.fill(new Polygon(xSRight, ySRight, 3));
			g2.setColor(Color.BLACK);
			g2.drawLine(xSRight[0], ySRight[0], xSRight[2], ySRight[2]);
			g2.drawLine(xSRight[1], ySRight[1], xSRight[2], ySRight[2]);
			g2.setColor(Color.RED);
		} else {
			this.hitBox = new Rectangle.Double(this.posX - 10, this.posY, this.hitBoxWidth + 5, this.hitBoxHeight);
			g2.fill(new Polygon(xSLeft, ySLeft, 3));
			g2.setColor(Color.BLACK);
			g2.drawLine(xSLeft[0], ySLeft[0], xSLeft[2], ySLeft[2]);
			g2.drawLine(xSLeft[1], ySLeft[1], xSLeft[2], ySLeft[2]);
			g2.setColor(Color.RED);
		}
	}

	@Override
	protected void collidingWith(Rectangle.Double tile) {
		if (tile.x + tile.width == this.hitBox.x && tile.y < this.hitBox.y + this.hitBoxHeight
				&& tile.y + tile.height > this.hitBox.y) {

			velX = this.speedX;

		} else if (tile.x == this.hitBox.x + this.hitBoxWidth && tile.y < this.hitBox.y + this.hitBoxHeight
				&& tile.y + tile.height > this.hitBox.y) {

			velX = -1 * this.speedX;

		}

		super.collidingWith(tile);
	}
}
