//Final Submission
package mainApp;

import java.awt.Rectangle;

public abstract class Actor {
	protected double speedX, speedY, posX, posY, velX, velY, hitBoxHeight, hitBoxWidth;
	protected Rectangle.Double hitBox;


	public Actor(double x, double y) {
		this.posX = x;
		this.posY = y;
		this.velX = 0;
		this.velY = 0;
	}

	protected void move() {
		this.posX += this.velX;
		this.posY += this.velY;
		this.hitBox.setRect(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);
	}

	protected void collidingWith(Rectangle.Double tile) {
		// Top of tile collisions
		if (tile.y <= this.hitBoxHeight + this.hitBox.y + this.velY && !(tile.y < this.hitBoxHeight + this.hitBox.y)
				&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

			this.velY = tile.y - (this.hitBoxHeight + this.hitBox.y);

		}
		// Bottom of tile collisions
		else if (tile.y + tile.height >= this.hitBox.y + this.velY
				&& !(tile.y + tile.height >= this.hitBox.y + this.hitBoxHeight)
				&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

			this.velY = (tile.y + tile.height) - this.hitBox.y;

		}
		// Right of tile collisions
		if (tile.x + tile.width >= this.hitBox.x + this.velX
				&& !(tile.x + tile.width >= this.hitBox.x + this.hitBoxWidth)
				&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

			this.velX = (tile.x + tile.width) - this.hitBox.x;

		}
		// Left of tile collisions
		else if (tile.x <= this.hitBox.x + this.hitBoxWidth + this.velX && !(tile.x <= this.hitBox.x)
				&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

			this.velX = tile.x - (this.hitBoxWidth + this.hitBox.x);

		}
	}

	protected boolean collidingWith(Actor other) {
		if (other.hitBox.x < this.hitBox.x + this.hitBoxWidth && other.hitBox.x + other.hitBoxWidth > this.hitBox.x
				&& other.hitBox.y < this.hitBox.y + this.hitBoxHeight
				&& other.hitBox.y + other.hitBoxHeight > this.hitBox.y) {
			return true;
		} else
			return false;
	}

}
