//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Hero extends Actor {

	protected static int HEIGHT = 20;
	protected static int WIDTH = 20;

	protected boolean up, left, right;
	protected int hitTimer;

	public Hero(double x, double y) {
		super(x, y);
		this.speedX = 8;
		this.speedY = 12;
		this.hitBoxHeight = HEIGHT;
		this.hitBoxWidth = WIDTH;
		this.hitBox = new Rectangle.Double(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);
		this.up = false;
		this.right = false;
		this.left = false;
		this.hitTimer = 0;
	}

	protected void drawOn(Graphics g, int lives) {
		Graphics2D g2 = (Graphics2D) g;

		if (hitTimer == 0) {
			g2.setColor(new Color(254, 213, 4));
		} else {
			g2.setColor(new Color(163, 65, 0));
		}
		g2.fill(new Rectangle2D.Double((double) super.posX, (double) super.posY, (double) this.hitBoxHeight,
				(double) this.hitBoxWidth));
		g2.setColor(Color.BLACK);
		g2.draw(new Rectangle2D.Double((double) super.posX, (double) super.posY, (double) this.hitBoxHeight,
				(double) this.hitBoxWidth));
		g2.setColor(new Color(1, 254, 253));
		g2.fill(new Rectangle2D.Double((double) super.posX + 4, (double) super.posY + 4, (double) this.hitBoxHeight / 5,
				(double) this.hitBoxWidth / 5));
		g2.fill(new Rectangle2D.Double((double) super.posX + 12, (double) super.posY + 4,
				(double) this.hitBoxHeight / 5, (double) this.hitBoxWidth / 5));
		g2.setColor(Color.BLACK);
		g2.draw(new Rectangle2D.Double((double) super.posX + 4, (double) super.posY + 4, (double) this.hitBoxHeight / 5,
				(double) this.hitBoxWidth / 5));
		g2.draw(new Rectangle2D.Double((double) super.posX + 12, (double) super.posY + 4,
				(double) this.hitBoxHeight / 5, (double) this.hitBoxWidth / 5));
		g2.setColor(new Color(1, 254, 253));
		if (lives == 4) {
			int[] xList = { (int) super.posX + 4, ((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX + 4) + ((int) this.hitBoxWidth - 8), (int) super.posX + 4 };
			int[] yList = { (int) super.posY + 12, (int) super.posY + 12,
					((int) super.posY + 12) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 12) + ((int) this.hitBoxHeight / 5) };
			g2.fill(new Polygon(xList, yList, 4));
			g2.setColor(Color.BLACK);
			g2.draw(new Polygon(xList, yList, 4));
		} else if (lives == 3) {
			int[] xList = { (int) super.posX + 4, ((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8), ((int) super.posX) + 8, ((int) super.posX) + 8,
					(int) super.posX + 4 };
			int[] yList = { (int) super.posY + 12, (int) super.posY + 12,
					((int) super.posY + 13) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 13) + ((int) this.hitBoxHeight / 5), (int) super.posY + 16,
					(int) super.posY + 16, ((int) super.posY + 13) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 13) + ((int) this.hitBoxHeight / 5) };
			g2.fill(new Polygon(xList, yList, 8));
			g2.setColor(Color.BLACK);
			g2.draw(new Polygon(xList, yList, 8));
		} else if (lives == 2) {
			int[] xList = { (int) super.posX + 4, ((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8), ((int) super.posX) + 8, ((int) super.posX) + 8,
					(int) super.posX + 4 };
			int[] yList = { (int) super.posY + 12, (int) super.posY + 12,
					((int) super.posY + 14) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 14) + ((int) this.hitBoxHeight / 5), (int) super.posY + 16,
					(int) super.posY + 16, ((int) super.posY + 14) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 14) + ((int) this.hitBoxHeight / 5) };
			g2.fill(new Polygon(xList, yList, 8));
			g2.setColor(Color.BLACK);
			g2.draw(new Polygon(xList, yList, 8));
		} else {
			int[] xList = { (int) super.posX + 4, ((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX + 4) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8),
					((int) super.posX) + ((int) this.hitBoxWidth - 8), ((int) super.posX) + 8, ((int) super.posX) + 8,
					(int) super.posX + 4 };
			int[] yList = { (int) super.posY + 12, (int) super.posY + 12,
					((int) super.posY + 15) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 15) + ((int) this.hitBoxHeight / 5), (int) super.posY + 16,
					(int) super.posY + 16, ((int) super.posY + 15) + ((int) this.hitBoxHeight / 5),
					((int) super.posY + 15) + ((int) this.hitBoxHeight / 5) };
			g2.fill(new Polygon(xList, yList, 8));
			g2.setColor(Color.BLACK);
			g2.draw(new Polygon(xList, yList, 8));
		}

		// g2.draw(hitBox);
	}

	@Override
	protected void move() {
		super.move();

		// Gravitational acceleration
		if (velY < this.speedY) {
			this.velY += 2;
		}

		// Horizontal sliding acceleration
		if (velX != 0) {
			this.velX += -2 * this.velX / Math.abs(this.velX);
		}

		// Stops accelerating at terminal velocity
		if (Math.abs(this.velY) > this.speedY) {
			this.velY = this.speedY * this.velY / Math.abs(this.velY);
		}
	}

	protected void hit() {
		this.hitTimer = GameViewer.DELAY / 2;
	}

	protected void hitTimerTick() {
		if (this.hitTimer > 0) {
			this.hitTimer--;
		}
	}

	protected int getHitTimer() {
		return this.hitTimer;
	}

	// ----------------------------------------------------------------------- \\

	protected double fuel = 100;

	@Override
	protected void collidingWith(Rectangle.Double tile) {
		super.collidingWith(tile);

		// Refuel
		if (tile.y == this.hitBoxHeight + this.hitBox.y && tile.x < this.hitBox.x + this.hitBoxWidth
				&& tile.x + tile.width > this.hitBox.x) {

			if (this.fuel < 100) {
				this.fuel += 4;
				if (this.fuel > 100) {
					this.fuel = 100;
				}
			}
		}
	}

	protected boolean collidingWith(Enemy other) {
		if (other.posY < this.hitBoxHeight + this.hitBox.y + this.velY
				&& !(other.posY < this.hitBoxHeight + this.hitBox.y) && other.posX < this.hitBox.x + this.hitBoxWidth
				&& other.posX + other.hitBoxWidth > this.hitBox.x && other.killable) {

			this.velY = other.posY + other.velY - (this.hitBoxHeight + this.hitBox.y);
			if (this.fuel < 100) {
				this.fuel += 4;
				if (this.fuel > 100) {
					this.fuel = 100;
				}
			}
		}
		return super.collidingWith(other);
	}

	protected void setVelocity() {
		if (up && this.fuel > 0) {
			this.velY = -1 * this.speedY * 0.8;

			this.fuel -= 1.65;

		}
		if (left && !right) {
			this.velX = -1 * this.speedX;
		} else if (right && !left) {
			this.velX = this.speedX;
		}
	}

	protected boolean hittingEnemy(Enemy other) {
		if (other.posY + other.velY <= this.hitBoxHeight + this.hitBox.y + this.velY
				&& !(other.posY < this.hitBoxHeight + this.hitBox.y) && other.posX < this.hitBox.x + this.hitBoxWidth
				&& other.posX + other.hitBoxWidth > this.hitBox.x && other.killable) {

			return true;
		}
		return false;
	}
}
