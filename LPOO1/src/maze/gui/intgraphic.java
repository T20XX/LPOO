package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.MazeBuilder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class intgraphic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					intgraphic frame = new intgraphic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public intgraphic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMazeDim = new JLabel("Maze Dim");
		lblMazeDim.setBounds(10, 11, 64, 14);
		contentPane.add(lblMazeDim);

		textField = new JTextField();
		textField.setBounds(84, 8, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNumbDragons = new JLabel("Numb Dragons");
		lblNumbDragons.setBounds(10, 39, 70, 14);
		contentPane.add(lblNumbDragons);

		textField_1 = new JTextField();
		textField_1.setBounds(84, 36, 46, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 64, 46, 14);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Dragons don't move");
		comboBox.addItem("Dragons move randomly");
		comboBox.addItem("Dragons move randomly and fall asleep");
		comboBox.setBounds(84, 67, 166, 20);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(301, 11, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(301, 39, 89, 23);
		contentPane.add(btnNewButton_1);

		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		MazeBuilder maze = new  MazeBuilder();
		char[][] mazeta = maze.buildMaze(10);
		String mazest = "";
		for(int i = 0; i < mazeta[0].length; i++){
			for(int j = 0; j < mazeta[i].length;j++){
				if(mazest == "") mazest = String.valueOf(mazeta[i][j]);
				else mazest = mazest + String.valueOf(mazeta[i][j]);
			}
			mazest = mazest + "\n";
		}
		textArea.setText(mazest);

		textArea.setBounds(10, 89, 216, 172);
		contentPane.add(textArea);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText() == ""){
					btnNewButton_2.setEnabled(false);
				}
				else 		btnNewButton_2.setEnabled(true);
			}
		});
		btnNewButton_2.setBounds(228, 138, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText() == ""){
					btnNewButton_3.setEnabled(false);
				}
				else 		btnNewButton_3.setEnabled(true);
			}
		});
		btnNewButton_3.setBounds(288, 104, 89, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText() == ""){
					btnNewButton_4.setEnabled(false);
				}
				else 		btnNewButton_4.setEnabled(true);
			}
		});
		btnNewButton_4.setBounds(288, 174, 89, 23);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText() == ""){
					btnNewButton_5.setEnabled(false);
				}
				else 		btnNewButton_5.setEnabled(true);
			}
		});
		btnNewButton_5.setBounds(335, 138, 89, 23);
		contentPane.add(btnNewButton_5);

	}
}
