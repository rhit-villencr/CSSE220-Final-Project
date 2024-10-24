//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Projectile extends Enemy {
	public Projectile(double x, double y, double vx, double vy) {
		super(x, y);
		this.speedX = 10;
		this.speedY = 10;
		this.velX = vx;
		this.velY = vy;
		this.killable = false;
		if (vx == 0) {
			this.hitBoxWidth = 25;
		} else
			this.hitBoxWidth = 5;
		if (vy == 0) {
			this.hitBoxHeight = 25;
		} else
			this.hitBoxHeight = 5;
		this.hitBox = new Rectangle.Double(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);
	}

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fill(hitBox);
		g2.setColor(Color.BLACK);
		g2.draw(hitBox);
	}
}
