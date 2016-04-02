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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maze.logic.Game;
import maze.logic.Space;
import maze.logic.Game.gameState;
import maze.logic.Space.spaceType;


public class GamePanel extends JPanel {
	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage free;
	private BufferedImage hero;
	private BufferedImage dragon;
	private int x, y;
	private double squareLength;
	public final int WIDTH = 500;
	public final int HEIGHT = 500;
	private boolean heroMoved = true;
	private int gamemode;
	private JLabel stateLbl;
	private int sizeD;

	private Game game;

	public GamePanel(Game game, int gamemode) throws IOException {
		try {
			wall = ImageIO.read(new File("img/wall.png"));
			free = ImageIO.read(new File("img/free.png"));
			hero = ImageIO.read(new File("img/hero.png"));
			sword = ImageIO.read(new File("img/sword.png"));	
			dragon = ImageIO.read(new File("img/dragon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.gamemode = gamemode;
		this.game = game;
		squareLength = WIDTH/game.getMaze().length;
		sizeD = game.getDragons().size();
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
					break;

				case KeyEvent.VK_RIGHT: 
					heroMoved = game.moveHeroRight();
					update();
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

		//JÁ PUS ISTO AQUI, VE NA API COMO FUNCIONA O DRAWIMAGE
		for(int y = 0; y < game.getMaze().length; y++) {
			for(int x = 0; x < game.getMaze()[y].length; x++) {
				switch(game.getMaze()[y][x].getType()){
				case EXIT:
					g.drawImage(free, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, free.getWidth(), free.getHeight(), null);
					break;
				case WALL:
					g.drawImage(wall, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, wall.getWidth(), wall.getHeight(), null);
					break;
				case FREE:
					g.drawImage(free, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, free.getWidth(), free.getHeight(), null);
				}
			}
		}
		for(int i = 0; i < game.getDragons().size(); i++){
			x = game.getDragons().get(i).getPosition().x;
			y = game.getDragons().get(i).getPosition().y;
			g.drawImage(dragon, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		}

		for(int i = 0; i < game.getSwords().size(); i++){
			x = game.getSwords().get(i).getPosition().x;
			y = game.getSwords().get(i).getPosition().y;
			g.drawImage(sword, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		}
		x = game.getHero().getPosition().x;
		y = game.getHero().getPosition().y;
		g.drawImage(hero, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		
		g.fillRect(0, 510, WIDTH/sizeD * game.getDragons().size(), 20);
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
			System.exit(0);
		}
		else if(game.getState() == gameState.WIN){
			stateLbl.setText("Hero slained all dragons and found his way out of the maze!");
			System.exit(0);
		}
		else{
			stateLbl.setText("Play!");
		}
		this.revalidate();
		this.repaint();
	}
	{
	stateLbl = new JLabel("Play");
	stateLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
	stateLbl.setForeground(Color.GRAY);
	stateLbl.setBounds(10, 272, 391, 14);
	this.add(stateLbl);
	}
}
