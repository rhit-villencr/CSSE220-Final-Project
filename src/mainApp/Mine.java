//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Mine extends Enemy {

	protected static int HEIGHT = 20;
	protected static int WIDTH = 20;

	protected boolean up, right, left, down;

	public Mine(double x, double y) {
		super(x, y);
		this.speedX = 0;
		this.speedY = 0;
		this.killable = false;
		this.hitBoxWidth = 20;
		this.hitBoxHeight = 20;

		this.up = false;
		this.right = false;
		this.left = false;
		this.down = false;

		this.hitBox = new Rectangle.Double(this.posX, this.posY, this.hitBoxHeight, this.hitBoxWidth);
	}

	public void drawOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.RED);
		if (this.up) {
			this.hitBox = new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0 - 8
							+ (GameViewer.FRAME_HEIGHT / 22.0),
					(GameViewer.FRAME_HEIGHT / 22.0), 8);
			g2.fill(this.hitBox);
			g2.setColor(Color.BLACK);
			g2.draw(this.hitBox);
		} else if (this.down) {
			this.hitBox = new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0,
					(GameViewer.FRAME_HEIGHT / 22.0), 8);
			g2.fill(this.hitBox);
			g2.setColor(Color.BLACK);
			g2.draw(this.hitBox);
		} else if (this.right) {
			this.hitBox = new Rectangle.Double(super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0,
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0));
			g2.fill(this.hitBox);
			g2.setColor(Color.BLACK);
			g2.draw(this.hitBox);
		} else if (this.left) {
			this.hitBox = new Rectangle.Double(
					super.posX - ((GameViewer.FRAME_WIDTH / 22.0) - this.hitBoxWidth) / 2.0 - 8
							+ (GameViewer.FRAME_WIDTH / 22.0),
					super.posY - ((GameViewer.FRAME_HEIGHT / 22.0) - this.hitBoxHeight) / 2.0, 8,
					(GameViewer.FRAME_HEIGHT / 22.0));
			g2.fill(this.hitBox);
			g2.setColor(Color.BLACK);
			g2.draw(this.hitBox);
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

}
