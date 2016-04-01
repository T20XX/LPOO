package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Menu {

	private JFrame menuFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.menuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuFrame = new JFrame();
		menuFrame.setResizable(false);
		menuFrame.setTitle("Hero Maze");
		menuFrame.setBounds(100, 100, 450, 240);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().setLayout(null);
		
		JButton generateBtn = new JButton("Generate Maze");
		generateBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				GenerateMazeGUI window = new GenerateMazeGUI();
				window.show();
				menuFrame.hide();
			}
		});
		generateBtn.setBounds(129, 67, 186, 52);
		menuFrame.getContentPane().add(generateBtn);
		
		JButton drawBtn = new JButton("Draw Maze");
		drawBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				CreateMazeGUI window = new CreateMazeGUI();
				//window.show();
				menuFrame.hide();
			}
		});
		drawBtn.setBounds(129, 130, 186, 52);
		menuFrame.getContentPane().add(drawBtn);
		
		JLabel gameTitleLbl = new JLabel("HERO MAZE");
		gameTitleLbl.setFont(new Font("Stencil", Font.PLAIN, 26));
		gameTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleLbl.setBounds(129, 11, 186, 45);
		menuFrame.getContentPane().add(gameTitleLbl);
	}
}
