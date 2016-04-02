package maze.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import maze.logic.Game;
import maze.logic.MazeBuilder;


public class CreateMazePanel extends JPanel {

	private enum objectSelected{
		NONE,
		WALL,
		HERO,
		SWORD,
		DRAGON,
		EXIT,
		DELETE
	};

	public final int WIDTH = 500;
	public final int HEIGHT = 550;

	private BufferedImage wall;
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage dragon;
	private BufferedImage exit;
	private BufferedImage delete;
	//private int x=0, y=0;//, width=100, height=100;
	private JButton confirmBtn;

	private int mazeDim;
	private int gamemode;
	private double squareLength;
	private char[][] maze;
	private objectSelected selected = objectSelected.NONE;

	public CreateMazePanel(int mazeDim, int gamemode) {
		this.setLayout(null);

		try {
			wall = ImageIO.read(new File("img/wall.png"));
			hero = ImageIO.read(new File("img/hero.png"));
			sword = ImageIO.read(new File("img/sword.png"));
			dragon = ImageIO.read(new File("img/dragon.png"));
			exit = ImageIO.read(new File("img/free.png"));
			delete = ImageIO.read(new File("img/delete.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	

		this.mazeDim = mazeDim;
		this.gamemode = gamemode;
		maze = new char[mazeDim][mazeDim];
		squareLength = WIDTH/mazeDim;

		//Initializes maze
		for(int x = 0; x < mazeDim; x++){
			maze[0][x] = 'X';
			maze[mazeDim-1][x] = 'X';
		}
		for(int y = 1; y < mazeDim-1; y++){
			maze[y][0] = 'X';
			maze[y][mazeDim-1] = 'X';
		}
		for(int x = 1; x < mazeDim-1; x++){
			for(int y = 1; y < mazeDim-1; y++){
				maze[y][x] = ' ';
			}
		}

		confirmBtn = new JButton("Confirm and Play");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkMazeValidity() == 0){
					try {

						BufferedWriter writer = new BufferedWriter(new FileWriter("tmp"));
						for ( int y = 0; y < maze.length; y++)
						{
							for ( int x = 0; x < maze[y].length; x++)
							{    
								writer.write(maze[y][x]);
							}
							if(y != maze.length-1)
								writer.write("\n");
						}
						writer.close();
					} catch(IOException ex) {
						ex.printStackTrace();
					}
					try {
						GameGUI nextWindow = new GameGUI(new Game("tmp"), gamemode);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
			}
		});
		confirmBtn.setBounds(300, 500, 200, 50);
		this.add(confirmBtn);

		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getY() > WIDTH){
					if (e.getX() < 50)
						selected = objectSelected.WALL;
					else if (e.getX() < 100)
						selected = objectSelected.HERO;
					else if (e.getX() < 150)
						selected = objectSelected.SWORD;
					else if (e.getX() < 200)
						selected = objectSelected.DRAGON;
					else if (e.getX() < 250)
						selected = objectSelected.EXIT;
					else if (e.getX() < 300)
						selected = objectSelected.DELETE;
				} else {
					char tmp = ' ';
					if (e.getX() > squareLength && e.getX() < squareLength * (mazeDim - 1) &&
							e.getY() > squareLength && e.getY() < squareLength * (mazeDim - 1)){
						switch (selected){
						case WALL:
							tmp = 'X';
							break;

						case HERO:
							tmp = 'H';
							break;

						case SWORD:
							tmp = 'E';
							break;

						case DRAGON:
							tmp = 'D';
							break;

						case EXIT:
							tmp = 'S';
							break;

						case DELETE:
							tmp = ' ';
							break;

						default:
							break;
						}
						maze[(int)(e.getY()/squareLength)][(int)(e.getX()/squareLength)] = tmp;
					} else {
						//BORDERS CAN ONLY HAVE WALLS OR EXIT
						switch (selected){
						case EXIT:
							tmp = 'S';
							maze[(int)(e.getY()/squareLength)][(int)(e.getX()/squareLength)] = tmp;
							break;

						case DELETE:
							tmp = 'X';
							maze[(int)(e.getY()/squareLength)][(int)(e.getX()/squareLength)] = tmp;
							break;

						default:
							break;
						}
					}
				}
				repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//		//Draws walls on borders
		//		for(int x = 0; x < 10; x++){
		//			g.drawImage(wall, x*50, 0, (x+1)*50, 50, 0, 0, wall.getWidth(), wall.getHeight(), null);
		//			g.drawImage(wall, x*50, 450, (x+1)*50, 500, 0, 0, wall.getWidth(), wall.getHeight(), null);
		//		}
		//
		//		for(int y = 1; y < 9; y++){
		//			g.drawImage(wall, 0, y*50, 50, (y+1)*50, 0, 0, wall.getWidth(), wall.getHeight(), null);
		//			g.drawImage(wall, 450, y*50, 500, (y+1)*50, 0, 0, wall.getWidth(), wall.getHeight(), null);
		//		}

		//Draws grid
		for(int x = 0; x < mazeDim; x++){
			for(int y = 0; y < mazeDim; y++){
				g.drawRect((int)(x*squareLength), (int)(y*squareLength), (int)squareLength, (int)squareLength);
			}
		}

		//Draws maze
		for(int x = 0; x < mazeDim; x++){
			for(int y = 0; y < mazeDim; y++){
				switch (maze[y][x]){
				case 'X':
					g.drawImage(wall, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, wall.getWidth(), wall.getHeight(), null);
					break;
				case 'H':
					g.drawImage(hero, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, hero.getWidth(), hero.getHeight(), null);
					break;
				case 'E':
					g.drawImage(sword, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, sword.getWidth(), sword.getHeight(), null);
					break;
				case 'D':
					g.drawImage(dragon, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon.getWidth(), dragon.getHeight(), null);
					break;
				case 'S':
					g.drawImage(exit, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, exit.getWidth(), exit.getHeight(), null);
					break;
				default:
					break;
				}

			}
		}

		//Draws down "toolbar"
		g.drawImage(wall, 0, 501, 49, 550, 0, 0, wall.getWidth(), wall.getHeight(), null);
		g.drawImage(hero, 50, 501, 99, 550, 0, 0, hero.getWidth(), hero.getHeight(), null);
		g.drawImage(sword, 100, 501, 149, 550, 0, 0, sword.getWidth(), sword.getHeight(), null);
		g.drawImage(dragon, 150, 501, 199, 550, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		g.drawImage(exit, 200, 501, 249, 550, 0, 0, exit.getWidth(), exit.getHeight(), null);
		g.drawImage(delete, 250, 501, 299, 550, 0, 0, delete.getWidth(), delete.getHeight(), null);
		//g.drawImage(confirm, 50, 501, 99, 550, 0, 0, confirm.getWidth(), confirm.getHeight(), null);
		//g.drawImage(cancel, 50, 501, 99, 550, 0, 0, cancel.getWidth(), cancel.getHeight(), null);
		//g.dra
	}

	public int checkMazeValidity(){
		int heroCounter = 0, swordCounter = 0, dragonCounter = 0, exitCounter = 0;
		for(int x = 0; x < mazeDim; x++){
			for(int y = 0; y < mazeDim; y++){
				switch (maze[y][x]){
				case 'H':
					heroCounter++;
					break;

				case 'E':
					swordCounter++;
					break;

				case 'D':
					dragonCounter++;
					break;

				case 'S':
					exitCounter++;
					break;

				default:
					break;
				}
			}
		}

		if (heroCounter == 0){
			JOptionPane.showMessageDialog(getParent(), "Place at least one hero.");
			return 1;
		} else if (heroCounter > 1){
			JOptionPane.showMessageDialog(getParent(), "Place only one hero.");
			return 2;
		} else if (swordCounter == 0){
			JOptionPane.showMessageDialog(getParent(), "Place at least one sword.");
			return 3;
		} else if (swordCounter > mazeDim*mazeDim/4){
			JOptionPane.showMessageDialog(getParent(), "Number of swords must be lower than a quarter of the maze's area");
			return 4;
		} else if (dragonCounter == 0){
			JOptionPane.showMessageDialog(getParent(), "Place at least one dragon.");
			return 5;
		} else if (dragonCounter > mazeDim*mazeDim/4){
			JOptionPane.showMessageDialog(getParent(), "Number of dragons must be lower than a quarter of the maze's area");
			return 6;
		} else if (exitCounter == 0){
			JOptionPane.showMessageDialog(getParent(), "Place at least one exit.");
			return 7;
		} else if (exitCounter > 1){
			JOptionPane.showMessageDialog(getParent(), "Place only one exit.");
			return 8;
		}

		for(int x = 0; x < mazeDim; x++){
			for(int y = 0; y < mazeDim; y++){
				switch (maze[y][x]){
				case 'H':
					if (maze[y-1][x] == 'D' ||
					maze[y+1][x] == 'D' ||
					maze[y][x-1] == 'D' ||
					maze[y][x+1] == 'D'){
						JOptionPane.showMessageDialog(getParent(), "Hero cannot be placed near a dragon.");
						return 9;
					}
					break;

				case 'S':
					if (((x == 0 || x == mazeDim - 1) && y == x) || (x == 0 &&  y == mazeDim - 1) || (y == 0 &&  x == mazeDim - 1)){
						JOptionPane.showMessageDialog(getParent(), "Exit cannot be placed in a corner.");
						return 10;
					}
					break;

				default:
					break;
				}
			}
		}

		return 0;
	}

}
