package maze.gui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import maze.logic.Game;


public class GameGUI{

	private Game game;
	private int gamemode;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public GameGUI(Game game, int gamemode) throws IOException {
		this.game = game;
		this.gamemode = gamemode;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("Hero Maze");
		frame.setBounds(600, 250, 500, 500);
		frame.setPreferredSize(new Dimension(500, 570));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Back to main menu when closed
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				MainMenuGUI.mainWindow.setVisible(true);
			}
		});
	
		GamePanel panel = new GamePanel(this.frame, game,gamemode);
		frame.getContentPane().add(panel);
		
		
		frame.pack();
		
		frame.setResizable(false);
		
		frame.setVisible(true);
		
		panel.requestFocus();
	}

}
