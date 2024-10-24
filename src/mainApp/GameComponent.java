//Final Submission
package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameComponent extends JComponent implements KeyListener {

	private static int AMOUNT_OF_LEVELS = 6;	
	
	private ArrayList<Hero> heroes;
	private ArrayList<TrackingEnemy> trackingEnemies;
	private ArrayList<BlindEnemy> blindEnemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<Spike> spikes;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Mine> mines;
	private LevelLoader ll;
	protected int life, score;

	public GameComponent() {
		this.setFocusable(true);
		this.addKeyListener(this);

		heroes = new ArrayList<>();
		trackingEnemies = new ArrayList<>();
		blindEnemies = new ArrayList<>();
		bombs = new ArrayList<>();
		spikes = new ArrayList<>();
		projectiles = new ArrayList<>();
		mines = new ArrayList<>();
		life = 4;
		score = 0;

		this.ll = new LevelLoader(1);
		ll.updateLevelLoader(1);
		addActors();
	}

	public void update() {
		// Movement and tile collisions
		for (Hero h : heroes) {
			h.setVelocity();
			for (Rectangle.Double t : ll.tiles) {
				h.collidingWith(t);
			}
			h.move();
		}
		for (TrackingEnemy e : trackingEnemies) {
			e.generateMovements(e.findClosestHero(heroes).hitBox);
			for (Rectangle.Double t : ll.tiles) {
				e.collidingWith(t);
			}
			e.move();
		}
		for (BlindEnemy e : blindEnemies) {
			for (Rectangle.Double t : ll.tiles) {
				e.collidingWith(t);
			}
			e.move();
		}
		for (Spike e : spikes) {
			for (Rectangle.Double t : ll.tiles) {
				e.setDirection(t);
			}
			for (Rectangle.Double t : ll.tiles) {
				e.setSight(t);
			}
			if (e.updateTick()) {
				if (e.up) {
					shootProjectile(e.posX + e.hitBoxWidth / 2 - 2.5, e.posY - 5 + 25, 0, -20);
				} else if (e.down) {
					shootProjectile(e.posX + e.hitBoxWidth / 2 - 2.5, e.posY + e.hitBoxHeight - 25, 0, 20);
				} else if (e.right) {
					shootProjectile(e.posX + e.hitBoxWidth - 25, e.posY + e.hitBoxHeight / 2 - 2.5, 20, 0);
				} else if (e.left) {
					shootProjectile(e.posX - 5 + 25, e.posY + e.hitBoxHeight / 2 - 2.5, -20, 0);
				}
			}
		}
		for (Mine e : mines) {
			for (Rectangle.Double t : ll.tiles) {
				e.setDirection(t);
			}
		}
		ArrayList<Projectile> psToRem = new ArrayList<>();
		for (Projectile p : projectiles) {
			for (Rectangle.Double t : ll.tiles) {
				p.collidingWith(t);
				if (p.velX == 0 && p.velY == 0) {
					psToRem.add(p);
				}
			}
			p.move();
		}
		for (Projectile p : psToRem) {
			projectiles.remove(p);
		}

		// Actor collisions
		for (Hero h : heroes) {
			// Item collisions
			ArrayList<Bomb> bombsToRem = new ArrayList<>();
			for (Bomb b : bombs) {
				if (h.collidingWith(b)) {
					bombsToRem.add(b);
					this.score += b.getPointValue();
				}
			}
			for (Bomb b : bombsToRem) {
				bombs.remove(b);
			}

			// Enemy collisions
			h.hitTimerTick();
			ArrayList<TrackingEnemy> tEnemiesToRemove = new ArrayList<>();
			ArrayList<BlindEnemy> bEnemiesToRemove = new ArrayList<>();

			for (BlindEnemy e : blindEnemies) {
				if (h.getHitTimer() == 0) {
					if (h.collidingWith(e)) {
						h.hit();
						if (this.life > 0) {
							this.life--;
						}
					}
				}
				if (h.hittingEnemy(e)) {
					bEnemiesToRemove.add(e);
					this.score += BlindEnemy.BLIND_POINT_VALUE;
				}
			}
			for (TrackingEnemy e : trackingEnemies) {
				if (h.getHitTimer() == 0) {
					if (h.collidingWith(e)) {
						h.hit();
						if (this.life > 0) {
							this.life--;
						}
					}
				}
				if (h.hittingEnemy(e)) {
					tEnemiesToRemove.add(e);
					this.score += TrackingEnemy.TRACKING_POINT_VALUE;
				}
			}

			for (Spike e : spikes) {
				if (h.getHitTimer() == 0) {
					if (h.collidingWith(e)) {
						h.hit();
						if (this.life > 0) {
							this.life--;
						}
					}
				}
				e.searching(h);
			}
			for (Projectile p : projectiles) {
				if (h.getHitTimer() == 0) {
					if (h.collidingWith(p)) {
						h.hit();
						if (this.life > 0) {
							this.life--;
						}
					}
				}
			}
			ArrayList<Mine> minesToRem = new ArrayList<>();
			for (Mine m : mines) {
				if (h.getHitTimer() == 0) {
					if (h.collidingWith(m)) {
						h.hit();
						if (this.life > 0) {
							this.life--;
						}
						minesToRem.add(m);
					}
				}
			}
			for (Mine m : minesToRem) {
				mines.remove(m);
			}

			for (BlindEnemy e : bEnemiesToRemove) {
				blindEnemies.remove(e);
			}
			for (TrackingEnemy e : tEnemiesToRemove) {
				trackingEnemies.remove(e);
			}
		}

		// Lose game
		if (this.life == 0) {
			changeLevel(-1 * AMOUNT_OF_LEVELS);
			this.life = 4;
			this.score = 0;
		}

		// Level won
		if (bombs.size() == 0) {
			this.score += 100 + 100 * this.life;
			if (ll.fileNum == AMOUNT_OF_LEVELS) {
				System.exit(1);
			} else
				changeLevel(1);
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		for (Projectile p : projectiles) {
			p.drawOn(g);
		}
		for (Bomb b : bombs) {
			b.drawOn(g);
		}
		for (TrackingEnemy e : trackingEnemies) {
			e.drawOn(g);
		}
		for (BlindEnemy e : blindEnemies) {
			e.drawOn(g);
		}
		for (Spike e : spikes) {
			e.drawOn(g);
		}
		for (Mine e : mines) {
			e.drawOn(g);
		}

		ll.paintLevel(g);

		// Creating fuel gauge
		for (int i = heroes.size() - 1; i >= 0; i--) {

			heroes.get(i).drawOn(g, this.life);
			if (heroes.get(i).fuel >= 75) {
				g.setColor(Color.GREEN);
			} else if (heroes.get(i).fuel >= 40) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.RED);
			}
			g.fillRect(550 - i * 120, 5, (int) heroes.get(i).fuel, 20);
			g.setColor(Color.BLACK);
			g.drawRect(550 - i * 120, 5, (int) heroes.get(i).fuel, 20);
		}

		// Life counter
		for (int i = 0; i < this.life; i++) {
			g.setColor(Color.RED);
			g.fillRect(5 + (i * 30), 5, 20, 20);
		}

		// Score
		g.setColor(Color.BLACK);
		g.drawRect((int) (GameViewer.FRAME_HEIGHT / 22.0 + 5), (int) (GameViewer.FRAME_WIDTH / 22.0 + 5),
				String.valueOf(this.score).length() * 5 + 50, 20);
		g.drawString("Score: " + this.score, (int) (GameViewer.FRAME_WIDTH / 22.0 + 8),
				(int) (GameViewer.FRAME_WIDTH / 22.0 + 20));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			for (Hero h : heroes) {
				h.left = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			for (Hero h : heroes) {
				h.right = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			for (Hero h : heroes) {
				h.up = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_U) {
			changeLevel(1);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			changeLevel(-1);
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			changeLevel(-1 * AMOUNT_OF_LEVELS);
			this.life = 4;
			this.score = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			for (Hero h : heroes) {
				h.left = false;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			for (Hero h : heroes) {
				h.right = false;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			for (Hero h : heroes) {
				h.up = false;
			}
		}
	}

	protected void changeLevel(int diff) {
		int fnum = ll.fileNum;
		heroes.clear();
		trackingEnemies.clear();
		blindEnemies.clear();
		bombs.clear();
		spikes.clear();
		projectiles.clear();
		mines.clear();

		if (fnum + diff <= 0) {
			ll = new LevelLoader(1);
		} else if (fnum + diff > AMOUNT_OF_LEVELS) {
			ll = new LevelLoader(AMOUNT_OF_LEVELS);
		} else
			ll = new LevelLoader(fnum + diff);

		addActors();
	}

	protected void addActors() {
		for (Point2D.Double h : ll.getHeroPoints()) {
			Hero hero = new Hero(h.getX(), h.getY());
			heroes.add(hero);
		}
		for (Point2D.Double t : ll.getTrackingPoints()) {
			TrackingEnemy enemy = new TrackingEnemy(t.getX(), t.getY());
			trackingEnemies.add(enemy);
		}
		for (Point2D.Double e : ll.getBlindPoints()) {
			BlindEnemy enemy = new BlindEnemy(e.getX(), e.getY());
			blindEnemies.add(enemy);
		}
		for (Point2D.Double b : ll.getBombPoints()) {
			Bomb bomb = new Bomb(b.getX(), b.getY());
			bombs.add(bomb);
		}
		for (Point2D.Double s : ll.getSpikePoints()) {
			Spike spike = new Spike(s.getX(), s.getY());
			spikes.add(spike);
		}
		for (Point2D.Double m : ll.getMinePoints()) {
			Mine mine = new Mine(m.getX(), m.getY());
			mines.add(mine);
		}
	}

	protected void shootProjectile(double x, double y, double velX, double velY) {
		Projectile p = new Projectile(x, y, velX, velY);
		projectiles.add(p);
	}

}
