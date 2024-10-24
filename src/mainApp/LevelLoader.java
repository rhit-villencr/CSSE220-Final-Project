//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {

	public int fileNum;
	protected ArrayList<Rectangle.Double> tiles;
	protected ArrayList<Point2D.Double> heroPoints, trackingPoints, blindPoints, bombPoints, spikePoints, minePoints;

	public LevelLoader(int fileNum) {
		this.fileNum = fileNum;
		this.tiles = new ArrayList<Rectangle.Double>();
		updateLevelLoader(fileNum);

	}

	public void updateLevelLoader(int fileNum) {
		this.fileNum = fileNum;
		int lineNumber = 0;
		String fileName = "levels/level" + fileNum + ".txt";
		Scanner scanner = null;

		heroPoints = new ArrayList<>();
		trackingPoints = new ArrayList<>();
		blindPoints = new ArrayList<>();
		bombPoints = new ArrayList<>();
		spikePoints = new ArrayList<>();
		minePoints = new ArrayList<>();

		try {
			scanner = new Scanner(new File(fileName));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '-' || line.charAt(i) == '|') {
						tiles.add(new Rectangle.Double(i * GameViewer.FRAME_WIDTH / 22.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0, GameViewer.FRAME_WIDTH / 22.0,
								GameViewer.FRAME_HEIGHT / 22.0));
					} else if (line.charAt(i) == 'H') {
						heroPoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - Hero.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - Hero.HEIGHT) / 2.0));
					} else if (line.charAt(i) == 'T') {
						trackingPoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - TrackingEnemy.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - TrackingEnemy.HEIGHT) / 2.0));
					} else if (line.charAt(i) == 'E') {
						blindPoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - BlindEnemy.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - BlindEnemy.HEIGHT) / 2.0));
					} else if (line.charAt(i) == 'B') {
						bombPoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - Bomb.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - Bomb.HEIGHT) / 2.0));
					} else if (line.charAt(i) == 'S') {
						spikePoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - Bomb.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - Bomb.HEIGHT) / 2.0));
					} else if (line.charAt(i) == 'M') {
						minePoints.add(new Point2D.Double(
								i * GameViewer.FRAME_WIDTH / 22.0
										+ ((GameViewer.FRAME_WIDTH / 22.0) - Mine.WIDTH) / 2.0,
								lineNumber * GameViewer.FRAME_HEIGHT / 22.0
										+ ((GameViewer.FRAME_HEIGHT / 22.0) - Mine.HEIGHT) / 2.0));
					}
				}
				lineNumber++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public ArrayList<Point2D.Double> getHeroPoints() {
		return this.heroPoints;
	}

	public ArrayList<Point2D.Double> getTrackingPoints() {
		return this.trackingPoints;
	}

	public ArrayList<Point2D.Double> getBlindPoints() {
		return this.blindPoints;
	}

	public ArrayList<Point2D.Double> getBombPoints() {
		return this.bombPoints;
	}

	public ArrayList<Point2D.Double> getSpikePoints() {
		return this.spikePoints;
	}

	public ArrayList<Point2D.Double> getMinePoints() {
		return this.minePoints;
	}

	public void paintLevel(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Rectangle.Double t : tiles) {
			g2.setColor(Color.GRAY);
			g2.fill(t);
			g2.setColor(Color.BLACK);
			g2.draw(t);

		}
	}
}
