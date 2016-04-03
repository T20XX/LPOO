package maze.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maze.logic.Game;
import maze.logic.Space;
import maze.logic.Game.gameState;
import maze.logic.Space.spaceType;


public class GamePanel extends JPanel {

	public final int WIDTH = 500;
	public final int HEIGHT = 500;

	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage free;
	private BufferedImage hero;
	private BufferedImage dragon_sleeping;
	private BufferedImage dragon_awake;
	private BufferedImage exit;

	private double squareLength;

	private boolean heroMoved = true;
	private int gamemode;
	private JLabel stateLbl;
	private int sizeD;
	private int heroLeft = 0;
	private int heroArmed = 0;

	private Game game;

	private JFrame parent;

	public GamePanel(JFrame parent, Game game, int gamemode) throws IOException {
		this.setLayout(null);

		try {
			wall = ImageIO.read(new File("img/wall.png"));
			free = ImageIO.read(new File("img/free.png"));
			hero = ImageIO.read(new File("img/hero.png"));
			sword = ImageIO.read(new File("img/sword.png"));	
			dragon_sleeping = ImageIO.read(new File("img/dragon_sleeping.png"));
			dragon_awake = ImageIO.read(new File("img/dragon.png"));
			exit = ImageIO.read(new File("img/exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.parent = parent;
		this.game = game;
		this.gamemode = gamemode;
		squareLength = WIDTH/game.getMaze().length;
		sizeD = game.getDragons().size();

		stateLbl = new JLabel("Equip your hero by getting him a sword");
		stateLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		stateLbl.setForeground(Color.GRAY);
		stateLbl.setBounds(10, 500, 480, 14);
		this.add(stateLbl);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("x=" + x);
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					heroMoved = game.moveHeroLeft();
					update();
					heroLeft = 1;
					break;

				case KeyEvent.VK_RIGHT: 
					heroMoved = game.moveHeroRight();
					update();
					heroLeft = 0;
					break;

				case KeyEvent.VK_UP: 
					heroMoved = game.moveHeroUp();
					update();
					break;

				case KeyEvent.VK_DOWN: 
					heroMoved = game.moveHeroDown();
					update();
					break;
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x, y;

		//JÁ PUS ISTO AQUI, VE NA API COMO FUNCIONA O DRAWIMAGE
		for(y = 0; y < game.getMaze().length; y++) {
			for(x = 0; x < game.getMaze()[y].length; x++) {
				switch(game.getMaze()[y][x].getType()){
				case EXIT:
					if(game.getExitClosed()){
						g.drawImage(exit, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0 , 0 , exit.getWidth()/2, exit.getHeight(), null);
					}
					else g.drawImage(exit, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), exit.getWidth()/2 , 0 , exit.getWidth(), exit.getHeight(), null);
					break;
				case WALL:
					g.drawImage(wall, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, wall.getWidth(), wall.getHeight(), null);
					break;
				case FREE:
					g.drawImage(free, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, free.getWidth(), free.getHeight(), null);
					break;
				}
			}
		}
		for(int i = 0; i < game.getDragons().size(); i++){
			x = game.getDragons().get(i).getPosition().x;
			y = game.getDragons().get(i).getPosition().y;
			if(game.getDragons().get(i).getSleeping())
				g.drawImage(dragon_sleeping, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon_sleeping.getWidth(), dragon_sleeping.getHeight(), null);
			else g.drawImage(dragon_awake, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon_awake.getWidth(), dragon_awake.getHeight(), null);
		}

		for(int i = 0; i < game.getSwords().size(); i++){
			if(game.getHero().getSword() != game.getSwords().get(i)){
				x = game.getSwords().get(i).getPosition().x;
				y = game.getSwords().get(i).getPosition().y;
				g.drawImage(sword, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, sword.getWidth(), sword.getHeight(), null);
			}
		}

		x = game.getHero().getPosition().x;
		y = game.getHero().getPosition().y;
		g.drawImage(hero, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), (hero.getWidth()/2)*heroLeft , (hero.getHeight()/2)*heroArmed , (hero.getWidth()/2)*(heroLeft+1), (hero.getHeight()/2)*(heroArmed+1), null);

		g.fillRect(0, 520, WIDTH/sizeD * game.getDragons().size(), 20);
	}
	public void update(){
		if (heroMoved){
			switch(gamemode){
			case 2:
				game.moveDragons();
				break;
			case 3:
				game.moveOrSleepDragons();
				break;
			default:
				break;
			}

			game.updateGameState();
		}
		updateState();
	}

	public void updateState(){
		if(game.getState() == gameState.GAMEOVER){
			stateLbl.setText("Hero was killed by a dragon!");
			JOptionPane.showMessageDialog(getParent(), "Hero was killed by a dragon!",
					"You Lost",
					JOptionPane.WARNING_MESSAGE);
			MainMenuGUI.mainWindow.setVisible(true);
			parent.dispose();
		}
		else if(game.getState() == gameState.WIN){
			stateLbl.setText("Hero slained all dragons and found his way out of the maze!");
			JOptionPane.showMessageDialog(getParent(), "Hero slained all dragons and found his way out of the maze!",
					"Congratulations",
					JOptionPane.PLAIN_MESSAGE);
			MainMenuGUI.mainWindow.setVisible(true);
			parent.dispose();
		}
		else{
			if(game.getState() == gameState.HERO_ARMED && heroArmed == 0){
				heroArmed = 1;
				stateLbl.setText("Use your sword to defeat the dragons and open the doors of the maze!");
			}
		}
		this.repaint();
	}
}
