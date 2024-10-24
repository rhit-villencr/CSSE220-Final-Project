//Final Submission
package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

//viewer
public class GameViewer {
	protected static final int FRAME_WIDTH = 660;
	protected static final int FRAME_HEIGHT = 660;
	protected static final int DELAY = 50;

	public GameViewer() {

		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a component for watching simulations
		GameComponent gameComponent = new GameComponent();
		frame.add(gameComponent, BorderLayout.CENTER);

		// We study these in the MoreEventBasedProgramming project
		Timer t = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameComponent.update();
				gameComponent.repaint();
				frame.repaint();
			}
		});

		// Starts the simulator
		t.start();
		frame.pack();
		frame.setVisible(true);
	}
}
