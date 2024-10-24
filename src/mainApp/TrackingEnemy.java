//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class TrackingEnemy extends Enemy {

	protected static int HEIGHT = 30;
	protected static int WIDTH = 30;
	protected static int TRACKING_POINT_VALUE = 100;

	public TrackingEnemy(double x, double y) {
		super(x, y);
		this.speedX = 3;
		this.speedY = 3;
		this.killable = true;
		this.hitBoxHeight = HEIGHT;
		this.hitBoxWidth = WIDTH;
		this.hitBox = new Rectangle.Double(this.posX - 5, this.posY + 5, this.hitBoxHeight, this.hitBoxWidth);
	}

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(new Rectangle.Double(super.posX - 5.0, super.posY + ((this.hitBoxHeight - 10) / 2) - 2.5,
				10 + (this.hitBoxWidth - 10), 5.0));
		g2.fill(new Rectangle.Double(super.posX + ((this.hitBoxWidth - 10) / 2) - 2.5, super.posY - 5.0, 5.0,
				(10 + this.hitBoxHeight - 10)));
		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(super.posX, super.posY, this.hitBoxHeight - 10, this.hitBoxWidth - 10));
		g2.setColor(Color.BLACK);
		g2.draw(new Ellipse2D.Double(super.posX, super.posY, this.hitBoxHeight - 10, this.hitBoxWidth - 10));

		this.hitBox = new Rectangle.Double(this.posX - 5, this.posY - 5, this.hitBoxHeight, this.hitBoxWidth);
	}

	public void generateMovements(Rectangle.Double heroHitBox) {
		double diffX = (heroHitBox.x + heroHitBox.width / 2) - (this.hitBox.x + this.hitBoxWidth / 2);
		double diffY = (heroHitBox.y + heroHitBox.height / 2) - (this.hitBox.y + this.hitBoxHeight / 2);

		if (diffX == 0) {
			this.velX = 0;
			this.velY = this.speedY * diffY / Math.abs(diffY);
		} else if (diffY == 0) {
			this.velY = 0;
			this.velX = this.speedX * diffX / Math.abs(diffX);
		} else if (diffX < 0) {
			this.velY = this.speedY * Math.sin(Math.atan(-1 * diffY / diffX));
			this.velX = -1 * this.speedX * Math.cos(Math.atan(-1 * diffY / diffX));
		} else {
			this.velY = -this.speedY * Math.sin(Math.atan(-1 * diffY / diffX));
			this.velX = this.speedX * Math.cos(Math.atan(-1 * diffY / diffX));
		}

	}

	public Hero findClosestHero(ArrayList<Hero> heroes) {
		Hero closest = heroes.get(0);
		double firstX = (heroes.get(0).hitBox.x + heroes.get(0).hitBox.width / 2)
				- (this.hitBox.x + this.hitBoxWidth / 2);
		double firstY = (heroes.get(0).hitBox.y + heroes.get(0).hitBox.height / 2)
				- (this.hitBox.y + this.hitBoxHeight / 2);
		double min = Math.sqrt(firstX * firstX + firstY * firstY);

		for (Hero h : heroes) {
			double diffX = (h.hitBox.x + h.hitBox.width / 2) - (this.hitBox.x + this.hitBoxWidth / 2);
			double diffY = (h.hitBox.y + h.hitBox.height / 2) - (this.hitBox.y + this.hitBoxHeight / 2);
			double dist = Math.sqrt(diffX * diffX + diffY * diffY);
			if (dist < min) {
				min = dist;
				closest = h;
			}
		}

		return closest;
	}
}
