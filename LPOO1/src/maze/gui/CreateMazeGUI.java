package maze.gui;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;


public class CreateMazeGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMazeGUI window = new CreateMazeGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateMazeGUI() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateMazePanel panel = new CreateMazePanel(20);
		frame.getContentPane().add(panel);

		frame.pack();
		
		frame.setResizable(false);
		
		frame.setVisible(true);
		
		panel.requestFocus();
	}

}
