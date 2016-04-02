package maze.gui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import maze.logic.Game;


public class GameGUI{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameGUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("Hero Maze");
		frame.setBounds(100, 100, 500, 500);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game g = new Game("Map");
		GamePanel panel = new GamePanel(g);
		frame.getContentPane().add(panel);
		
		frame.pack();
		
		frame.setResizable(false);
		
		frame.setVisible(true);
		
		panel.requestFocus();
	}

}
