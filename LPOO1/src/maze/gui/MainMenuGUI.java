package maze.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import maze.logic.MazeBuilder;
import maze.logic.Game;
import maze.logic.Game.gameState;

import java.awt.Color;
import java.awt.Font;

public class MainMenuGUI extends JFrame {

	public static MainMenuGUI mainWindow;

	private JPanel contentPane;
	private JTextField mazeDimensionText;
	private JTextField dragonsNumberText;

	private JLabel stateLbl;
	private JTextArea textArea;
	private JButton upBtn;
	private JButton leftBtn;
	private JButton downBtn;
	private JButton rightBtn;
	private Game g;
	private boolean heroMoved = true;
	private int gamemode = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow = new MainMenuGUI();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuGUI() {
		setResizable(false);
		setTitle("Hero Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 490, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel mazeDimensionLbl = new JLabel("Maze Dimension");
		mazeDimensionLbl.setBounds(10, 86, 125, 14);
		contentPane.add(mazeDimensionLbl);

		mazeDimensionText = new JTextField();
		mazeDimensionText.setText("11");
		mazeDimensionText.setBounds(145, 83, 46, 20);
		contentPane.add(mazeDimensionText);
		mazeDimensionText.setColumns(10);

		JLabel dragonsNumberLbl = new JLabel("Number of Dragons");
		dragonsNumberLbl.setBounds(10, 111, 125, 14);
		contentPane.add(dragonsNumberLbl);

		dragonsNumberText = new JTextField();
		dragonsNumberText.setText("1");
		dragonsNumberText.setBounds(145, 108, 46, 20);
		contentPane.add(dragonsNumberText);
		dragonsNumberText.setColumns(10);

		JLabel dragonsTypeLbl = new JLabel("Dragons Type");
		dragonsTypeLbl.setBounds(10, 136, 94, 14);
		contentPane.add(dragonsTypeLbl);

		JComboBox dragonsTypeCB = new JComboBox();
		dragonsTypeCB.addItem("Dragons don't move");
		dragonsTypeCB.addItem("Dragons move randomly");
		dragonsTypeCB.addItem("Dragons move randomly and fall asleep");
		dragonsTypeCB.setBounds(101, 133, 260, 20);
		contentPane.add(dragonsTypeCB);

		JButton exitBtn = new JButton("Exit game");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(371, 125, 105, 30);
		contentPane.add(exitBtn);

		textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setBounds(10, 164, 216, 172);
		contentPane.add(textArea);

		upBtn = new JButton("UP");
		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroUp();
				update();
			}
		});
		upBtn.setEnabled(false);
		upBtn.setBounds(312, 196, 89, 23);
		contentPane.add(upBtn);

		leftBtn = new JButton("LEFT");
		leftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroLeft();
				update();
			}
		});
		leftBtn.setEnabled(false);
		leftBtn.setBounds(252, 230, 89, 23);
		contentPane.add(leftBtn);

		downBtn = new JButton("DOWN");
		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroDown();
				update();
			}
		});
		downBtn.setEnabled(false);
		downBtn.setBounds(312, 266, 89, 23);
		contentPane.add(downBtn);

		rightBtn = new JButton("RIGHT");
		rightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroRight();
				update();
			}
		});
		rightBtn.setEnabled(false);
		rightBtn.setBounds(359, 230, 89, 23);
		contentPane.add(rightBtn);

		JButton newMazeBtn = new JButton("Generate maze");
		newMazeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkValuesInserted()){
				//generate maze
				MazeBuilder mb = new MazeBuilder();
				mb.buildMazetoTXT("tmp",Integer.parseInt(mazeDimensionText.getText()), Integer.parseInt(dragonsNumberText.getText()));
				try {
					g = new Game("tmp");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				//set gamemode
				gamemode = dragonsTypeCB.getSelectedIndex() + 1;

				try {
					GameGUI nextWindow = new GameGUI(new Game("tmp"), gamemode);
					mainWindow.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
//				UNCOMMENT TO TEST TEXTAREA AND MOVE WITH BUTTONS
//				//enable movement buttons
//				upBtn.setEnabled(true);
//				leftBtn.setEnabled(true);
//				downBtn.setEnabled(true);
//				rightBtn.setEnabled(true);
				}
			}
		});
		newMazeBtn.setBounds(371, 81, 105, 30);
		contentPane.add(newMazeBtn);

		stateLbl = new JLabel("Insert maze dimension, number of dragons and dragons type");
		stateLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		stateLbl.setForeground(Color.GRAY);
		stateLbl.setBounds(10, 347, 391, 14);
		contentPane.add(stateLbl);

		JButton createMazeBtn = new JButton("Create Maze");
		createMazeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkValuesInserted()){
					CreateMazeGUI nextWindow = new CreateMazeGUI(Integer.parseInt(mazeDimensionText.getText()), dragonsTypeCB.getSelectedIndex() + 1);
					mainWindow.setVisible(false);
				}
			}
		});
		createMazeBtn.setBounds(252, 81, 105, 30);
		contentPane.add(createMazeBtn);

		JLabel titleLbl = new JLabel("Maze Mania");
		titleLbl.setForeground(new Color(255, 102, 0));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Chiller", Font.PLAIN, 80));
		titleLbl.setBounds(0, 0, 484, 78);
		contentPane.add(titleLbl);

	}

	public void update(){
		if (heroMoved){
			switch(gamemode){
			case 2:
				g.moveDragons();
				break;
			case 3:
				g.moveOrSleepDragons();
				break;
			default:
				break;
			}

			g.updateGameState();
		}
		print(g);
		updateState();
	}

	public void updateState(){
		if(g.getState() == gameState.GAMEOVER){
			stateLbl.setText("Hero was killed by a dragon!");
			//disable movement buttons
			upBtn.setEnabled(false);
			leftBtn.setEnabled(false);
			downBtn.setEnabled(false);
			rightBtn.setEnabled(false);
		}
		else if(g.getState() == gameState.WIN){
			stateLbl.setText("Hero slained all dragons and found his way out of the maze!");
			//disable movement buttons
			upBtn.setEnabled(false);
			leftBtn.setEnabled(false);
			downBtn.setEnabled(false);
			rightBtn.setEnabled(false);
		}
		else{
			stateLbl.setText("Play!");
		}
		//contentPane.revalidate();
		//contentPane.repaint();
	}

	public void print(Game g){
		char tmp[][] = new char[g.getMaze().length][g.getMaze()[0].length];

		for(int i = 0; i < g.getMaze().length; i++) {
			for(int j = 0; j < g.getMaze()[i].length; j++) {
				tmp[i][j] = g.getMaze()[i][j].getAtri();
			}
		}

		for(int i = 0; i < g.getDragons().size(); i++){
			tmp[g.getDragons().get(i).getPosition().y][g.getDragons().get(i).getPosition().x] = g.getDragons().get(i).getAtri();
		}

		for(int i = 0; i < g.getSwords().size(); i++){
			if(tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] == 'D')
				tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] = 'F';
			else 
				tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] = 'E';
		}


		tmp[g.getHero().getPosition().y][g.getHero().getPosition().x] = g.getHero().getAtri();

		textArea.setText("");
		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				textArea.append(String.valueOf(tmp[i][j]));
				//System.out.print(tmp[i][j]);
			}
			textArea.append("\n");
			//System.out.println();
		}
	}

	public boolean checkValuesInserted(){
		if (Integer.parseInt(mazeDimensionText.getText()) < 4 || Integer.parseInt(mazeDimensionText.getText()) > 31){
			JOptionPane.showMessageDialog(this, "Maze dimension should be a value between 4 and 31",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if ((Integer.parseInt(dragonsNumberText.getText()) > (Integer.parseInt(mazeDimensionText.getText()) * Integer.parseInt(mazeDimensionText.getText())) / 4)){
			JOptionPane.showMessageDialog(getParent(), "Number of dragons must be lower than a quarter of the maze's area",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
