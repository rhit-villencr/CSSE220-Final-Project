//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Spike extends Enemy {

	protected static int HEIGHT = 20;
	protected static int WIDTH = 20;

	protected boolean up, right, left, down, firing;
	protected Rectangle.Double sight;
	protected Double bound = 660.0;
	protected int timer = 0;

	public Spike(double x, double y) {
		super(x, y);
		this.killable = false;

		this.hitBoxHeight = HEIGHT;
		this.hitBoxWidth = WIDTH;
		this.hitBox = new Rectangle.Double(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);

		this.up = false;
		this.right = false;
		this.left = false;
		this.down = false;
		this.firing = false;
	}

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fill(new Rectangle.Double(super.posX, super.posY, this.hitBoxHeight, this.hitBoxWidth));
		g2.setColor(Color.BLACK);
		g2.draw(hitBox);
		g2.setColor(Color.RED);

		if (this.up) {
			g2.fill(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0 - 8
							+ (GameViewer.FRAME_HEIGHT / 22.0),
					(GameViewer.FRAME_HEIGHT / 22.0), 8));
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0 - 8
							+ (GameViewer.FRAME_HEIGHT / 22.0),
					(GameViewer.FRAME_HEIGHT / 22.0), 8));
		} else if (this.down) {
			g2.fill(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0,
					(GameViewer.FRAME_HEIGHT / 22.0), 8));
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0,
					(GameViewer.FRAME_HEIGHT / 22.0), 8));
		} else if (this.right) {
			g2.fill(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0)));
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0)));
		} else if (this.left) {
			g2.fill(new Rectangle.Double(
					super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0 - 8
							+ (GameViewer.FRAME_WIDTH / 22.0),
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0)));
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle.Double(
					super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0 - 8
							+ (GameViewer.FRAME_WIDTH / 22.0),
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0)));
		}

		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle.Double(super.posX + this.hitBoxWidth / 3.0, super.posY + this.hitBoxHeight / 3.0,
				this.hitBoxHeight / 3.0, this.hitBoxWidth / 3.0));
		g2.setColor(Color.BLACK);
		g2.draw(new Rectangle.Double(super.posX + this.hitBoxWidth / 3.0, super.posY + this.hitBoxHeight / 3.0,
				this.hitBoxHeight / 3.0, this.hitBoxWidth / 3.0));

		if (firing) {
			g2.setColor(Color.RED);
			g2.fill(new Rectangle.Double(super.posX + this.hitBoxWidth / 2.4, super.posY + this.hitBoxHeight / 2.4,
					this.hitBoxHeight / (6.0), this.hitBoxWidth / (6.0)));
			g2.setColor(Color.BLACK);
			g2.draw(new Rectangle.Double(super.posX + this.hitBoxWidth / 2.4, super.posY + this.hitBoxHeight / 2.4,
					this.hitBoxHeight / (6.0), this.hitBoxWidth / (6.0)));
		}

		if (sight != null) {
			Color c = new Color(1f, 0f, 0f, .12f);
			g2.setColor(c);
			g2.fill(sight);
		}
	}

	protected void setDirection(Rectangle.Double tile) {
		if (!up && !right && !left && !down) {

			// Top of tile collisions
			if (tile.y <= 2 * this.hitBoxHeight + this.hitBox.y && !(tile.y < this.hitBoxHeight + this.hitBox.y)
					&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

				this.up = true;
				this.right = false;
				this.left = false;
				this.down = false;

			}
			// Bottom of tile collisions
			else if (tile.y + tile.height >= this.hitBox.y - this.hitBoxHeight
					&& !(tile.y + tile.height >= this.hitBox.y + this.hitBoxHeight)
					&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

				this.up = false;
				this.right = false;
				this.left = false;
				this.down = true;

			}
			// Right of tile collisions
			else if (tile.x + tile.width >= this.hitBox.x - this.hitBoxWidth
					&& !(tile.x + tile.width >= this.hitBox.x + 2 * this.hitBoxWidth)
					&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

				this.up = false;
				this.right = true;
				this.left = false;
				this.down = false;

			}
			// Left of tile collisions
			else if (tile.x <= this.hitBox.x + 2 * this.hitBoxWidth && !(tile.x <= this.hitBox.x + this.hitBoxWidth)
					&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

				this.up = false;
				this.right = false;
				this.left = true;
				this.down = false;

			}

		}
	}

	protected void setSight(Rectangle.Double tile) {
		if (up) {
			if (tile.y + tile.height < this.hitBox.y && this.bound > tile.y + tile.height - this.hitBox.y
					&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

				this.bound = tile.y + tile.height;
				this.sight = new Rectangle.Double(this.hitBox.x, this.bound, this.hitBoxWidth,
						this.hitBox.y - this.bound);

			}
		} else if (down) {
			if (tile.y > this.hitBox.y + this.hitBoxHeight && this.bound > tile.y - this.hitBox.y
					&& tile.x < this.hitBox.x + this.hitBoxWidth && tile.x + tile.width > this.hitBox.x) {

				this.bound = tile.y;
				this.sight = new Rectangle.Double(this.hitBox.x, this.hitBox.y + this.hitBoxHeight, this.hitBoxWidth,
						this.bound - this.hitBox.y - this.hitBoxHeight);

			}
		} else if (left) {
			if (tile.x + tile.width < this.hitBox.x && this.bound > tile.x + tile.width - this.hitBox.x
					&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

				this.bound = tile.x + tile.width;
				this.sight = new Rectangle.Double(this.bound, this.hitBox.y, this.hitBox.x - this.bound,
						this.hitBoxHeight);

			}
		} else if (right) {
			if (tile.x > this.hitBox.x + this.hitBoxWidth && this.bound > tile.x - this.hitBox.x
					&& tile.y < this.hitBox.y + this.hitBoxHeight && tile.y + tile.height > this.hitBox.y) {

				this.bound = tile.x;
				this.sight = new Rectangle.Double(this.hitBox.x + this.hitBoxWidth, this.hitBox.y,
						this.bound - this.hitBox.x, this.hitBoxHeight);

			}
		}
	}

	protected void searching(Hero hero) {
		if (hero.hitBox.x + hero.hitBoxWidth > this.sight.x && hero.hitBox.x < this.sight.x + this.sight.width
				&& hero.hitBox.y + hero.hitBoxHeight > this.sight.y
				&& hero.hitBox.y < this.sight.y + this.sight.height) {

			if (!firing) {
				this.timer = 15;
			}
			this.firing = true;
		}
	}

	protected boolean updateTick() {
		if (this.timer > 0) {
			this.timer--;
		} else {
			firing = false;
		}
		if (timer == 14) {
			return true;
		}
		return false;
	}
}
