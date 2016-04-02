package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CreateMazeGUI {

	private JFrame frame;
	private int mazeDim;
	private int gamemode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMazeGUI window = new CreateMazeGUI(10, 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateMazeGUI(int mazeDim, int gamemode) {
		this.mazeDim = mazeDim;
		this.gamemode = gamemode;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Create Maze");
		frame.setBounds(100, 100, 500, 550);
		frame.setPreferredSize(new Dimension(507, 580));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Back to main menu when closed
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				GenerateMazeGUI.mainWindow.setVisible(true);
			}
		});

		CreateMazePanel panel = new CreateMazePanel(mazeDim, gamemode);
		frame.getContentPane().add(panel);

		frame.pack();

		frame.setResizable(false);

		frame.setVisible(true);

		panel.requestFocus();
	}

}
