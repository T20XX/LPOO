package maze.gui;

import java.awt.EventQueue;
import maze.logic.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class temp {

	private JFrame frmStart;
	private JTextField mazeDim;
	private JTextField numDragons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp window = new temp();
					window.frmStart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public temp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStart = new JFrame();
		frmStart.setEnabled(false);
		frmStart.setTitle("Start");
		frmStart.setBounds(100, 100, 483, 389);
		frmStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStart.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Maze Dimension");
		lblNewLabel.setBounds(31, 28, 86, 14);
		frmStart.getContentPane().add(lblNewLabel);
		
		mazeDim = new JTextField();
		mazeDim.setBounds(127, 25, 54, 20);
		frmStart.getContentPane().add(mazeDim);
		mazeDim.setColumns(10);
		
		numDragons = new JTextField();
		numDragons.setBounds(127, 56, 54, 20);
		frmStart.getContentPane().add(numDragons);
		numDragons.setColumns(10);
		
		JLabel lblDragons = new JLabel("Dragons");
		lblDragons.setBounds(31, 59, 86, 14);
		frmStart.getContentPane().add(lblDragons);
		
		JLabel lblTypeOfDragons = new JLabel("Type of Dragons");
		lblTypeOfDragons.setBounds(31, 92, 86, 14);
		frmStart.getContentPane().add(lblTypeOfDragons);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.addItem("Dragons don't move");
		comboBox.addItem("Dragons move randomly");
		comboBox.addItem("Dragons move randomly and fall asleep");
		comboBox.setBounds(127, 92, 155, 20);
		frmStart.getContentPane().add(comboBox);
		
		JButton btnEndProgram = new JButton("End Program");
		btnEndProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnEndProgram.setBounds(294, 66, 99, 23);
		frmStart.getContentPane().add(btnEndProgram);
		
		JTextArea mazeBuilder = new JTextArea();
		mazeBuilder.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeBuilder.setEditable(false);
		mazeBuilder.setBounds(31, 132, 228, 163);
		frmStart.getContentPane().add(mazeBuilder);
		
				
		JButton btnNewMaze = new JButton("New Maze");
		//btnNewMaze.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				MazeBuilder maze = new  MazeBuilder();
				char[][] mazeta = maze.buildMaze(10);
				String mazest = "";
				for(int i = 0; i < mazeta[0].length; i++){
					mazest.valueOf(mazeta[i]);
					mazeBuilder.setText(mazest + "\n");
				}
			//}
		//});
		btnNewMaze.setBounds(294, 24, 99, 23);
		frmStart.getContentPane().add(btnNewMaze);	
		
		JButton north = new JButton("New button");
		north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mazeBuilder == null)
					north.setEnabled(false);
			}
		});
		north.setBounds(328, 165, 89, 23);
		frmStart.getContentPane().add(north);
		
		
		JButton east = new JButton("New button");
		east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mazeBuilder == null)
					east.setEnabled(false);
			}
		});
		east.setBounds(279, 191, 89, 23);
		frmStart.getContentPane().add(east);
		
		JButton west = new JButton("New button");
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mazeBuilder == null)
					west.setEnabled(false);
			}
		});
		west.setBounds(378, 191, 89, 23);
		frmStart.getContentPane().add(west);
		
		JButton south = new JButton("New button");
		south.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mazeBuilder == null)
					south.setEnabled(true);
			}
		});
		south.setBounds(328, 217, 89, 23);
		frmStart.getContentPane().add(south);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(31, 306, 46, 14);
		frmStart.getContentPane().add(lblNewLabel_1);
	}
}
