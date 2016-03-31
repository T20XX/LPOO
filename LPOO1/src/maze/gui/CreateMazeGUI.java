package maze.gui;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;


public class CreateMazeGUI {

	private JFrame frmGraphicsDemo;

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
		frmGraphicsDemo = new JFrame();
		frmGraphicsDemo.setTitle("Graphics Demo");
		frmGraphicsDemo.setBounds(100, 100, 450, 300);
		frmGraphicsDemo.setPreferredSize(new Dimension(450, 300));
		frmGraphicsDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateMazePanel panel = new CreateMazePanel();
		frmGraphicsDemo.getContentPane().add(panel);

		frmGraphicsDemo.pack();
		
		frmGraphicsDemo.setVisible(true);
		
		panel.requestFocus();
	}

}
