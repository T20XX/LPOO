package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import maze.logic.MazeBuilder;
import maze.logic.game;
import maze.logic.game.gameState;

import java.awt.Color;
import java.awt.Font;

public class intgraphic extends JFrame {

	private JPanel contentPane;
	private JTextField mazeDimensionText;
	private JTextField dragonsNumberText;
	
	private JLabel stateLbl;
	private JButton upBtn;
	private JButton leftBtn;
	private JButton downBtn;
	private JButton rightBtn;
	private game g;
	private boolean heroMoved = true;
	private int gamemode = 0;

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
		setBounds(100, 100, 500, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel mazeDimensionLbl = new JLabel("Maze Dimension");
		mazeDimensionLbl.setBounds(10, 11, 125, 14);
		contentPane.add(mazeDimensionLbl);

		mazeDimensionText = new JTextField();
		mazeDimensionText.setText("11");
		mazeDimensionText.setBounds(145, 8, 46, 20);
		contentPane.add(mazeDimensionText);
		mazeDimensionText.setColumns(10);

		JLabel dragonsNumberLbl = new JLabel("Number of Dragons");
		dragonsNumberLbl.setBounds(10, 36, 125, 14);
		contentPane.add(dragonsNumberLbl);

		dragonsNumberText = new JTextField();
		dragonsNumberText.setText("1");
		dragonsNumberText.setBounds(145, 33, 46, 20);
		contentPane.add(dragonsNumberText);
		dragonsNumberText.setColumns(10);

		JLabel dragonsTypeLbl = new JLabel("Dragons Type");
		dragonsTypeLbl.setBounds(10, 61, 94, 14);
		contentPane.add(dragonsTypeLbl);

		JComboBox dragonsTypeCB = new JComboBox();
		dragonsTypeCB.addItem("Dragons don't move");
		dragonsTypeCB.addItem("Dragons move randomly");
		dragonsTypeCB.addItem("Dragons move randomly and fall asleep");
		dragonsTypeCB.setBounds(101, 58, 260, 20);
		contentPane.add(dragonsTypeCB);

		JButton exitBtn = new JButton("Exit game");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(369, 43, 105, 30);
		contentPane.add(exitBtn);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
//		String mazest = "";
//		for(int i = 0; i < mazeta[0].length; i++){
//			for(int j = 0; j < mazeta[i].length;j++){
//				if(mazest == "") mazest = String.valueOf(mazeta[i][j]);
//				else mazest = mazest + String.valueOf(mazeta[i][j]);
//			}
//			mazest = mazest + "\n";
//		}
//		textArea.setText(mazest);

		textArea.setBounds(10, 89, 216, 172);
		contentPane.add(textArea);

		upBtn = new JButton("UP");
		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroUp();
				update();
			}
		});
		upBtn.setEnabled(false);
		upBtn.setBounds(312, 121, 89, 23);
		contentPane.add(upBtn);

		leftBtn = new JButton("LEFT");
		leftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroLeft();
				update();
			}
		});
		leftBtn.setEnabled(false);
		leftBtn.setBounds(252, 155, 89, 23);
		contentPane.add(leftBtn);

		downBtn = new JButton("DOWN");
		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroDown();
				update();
			}
		});
		downBtn.setEnabled(false);
		downBtn.setBounds(312, 191, 89, 23);
		contentPane.add(downBtn);

		rightBtn = new JButton("RIGHT");
		rightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroMoved = g.moveHeroRight();
				update();
			}
		});
		rightBtn.setEnabled(false);
		rightBtn.setBounds(359, 155, 89, 23);
		contentPane.add(rightBtn);

		JButton newMazeBtn = new JButton("Generate maze");
		newMazeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//generate maze
				MazeBuilder mb = new MazeBuilder();
				mb.buildMazetoTXT("tmp.txt",Integer.parseInt(mazeDimensionText.getText()), Integer.parseInt(dragonsNumberText.getText()));
				try {
					g = new game("tmp.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//set gamemode
				gamemode = dragonsTypeCB.getSelectedIndex() + 1;
				
				//enable movement buttons
				upBtn.setEnabled(true);
				leftBtn.setEnabled(true);
				downBtn.setEnabled(true);
				rightBtn.setEnabled(true);
			}
		});
		newMazeBtn.setBounds(369, 8, 105, 30);
		contentPane.add(newMazeBtn);
		
		stateLbl = new JLabel("Insert maze dimension, number of dragons and dragons type");
		stateLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		stateLbl.setForeground(Color.GRAY);
		stateLbl.setBounds(10, 272, 391, 14);
		contentPane.add(stateLbl);

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
		}
		else{
			stateLbl.setText("Play!");
		}
		//contentPane.revalidate();
		//contentPane.repaint();
	}
	
	public static void print(game g){
		char tmp[][] = new char[g.getMaze().length][g.getMaze()[0].length];

		for(int i = 0; i < g.getMaze().length; i++) {
			for(int j = 0; j < g.getMaze()[i].length; j++) {
				tmp[i][j] = g.getMaze()[i][j].getAtri();
			}
		}

		for(int i = 0; i < g.getDragons().size(); i++){
			tmp[g.getDragons().get(i).getPosition().y][g.getDragons().get(i).getPosition().x] = g.getDragons().get(i).getAtri();
			//tempa.get(d.get(i).getPosition().y).get(d.get(i).getPosition().x).equals('D');
		}

		for(int i = 0; i < g.getSwords().size(); i++){
			if(tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] == 'D')
				tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] = 'F';
			else 
				tmp[g.getSwords().get(i).getPosition().y][g.getSwords().get(i).getPosition().x] = 'E';
			//tempa.get(s.get(i).getPosition().y).get(s.get(i).getPosition().x).equals('S');
		}


		tmp[g.getHero().getPosition().y][g.getHero().getPosition().x] = g.getHero().getAtri();
		//tempa.get(h.getPosition().y).get(h.getPosition().x).equals('H');

		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
	}
}
