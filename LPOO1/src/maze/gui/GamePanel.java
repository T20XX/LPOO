package maze.gui;
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

import maze.logic.Game;
import maze.logic.Space;
import maze.logic.Space.spaceType;


public class GamePanel extends JPanel {
	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage free;
	private BufferedImage hero;
	private BufferedImage dragon;
	private int x=0, y=0;
	private double squareLength;

	private Game game;

	public GamePanel(Game game) throws IOException {
		try {
			wall = ImageIO.read(new File("img/wall.png"));
			free = ImageIO.read(new File("img/free.png"));
			hero = ImageIO.read(new File("img/hero.png"));
			sword = ImageIO.read(new File("img/sword.png"));	
			dragon = ImageIO.read(new File("img/dragon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.game = game;
		squareLength = WIDTH/game.getMaze().length;

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("x=" + x);
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					x--; 
					break;

				case KeyEvent.VK_RIGHT: 
					x++; 
					//System.out.println("x=" + x);
					break;

				case KeyEvent.VK_UP: 
					y--; 
					break;

				case KeyEvent.VK_DOWN: 
					y++; 
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
		for(int i = 0; i < game.getDragons().size(); i++){
			g.drawImage(dragon, (int)(x*squareLength), (int)(y*squareLength), (int)((x+1)*squareLength), (int)((y+1)*squareLength), 0, 0, dragon.getWidth(), dragon.getHeight(), null);

		}

		for(int i = 0; i < game.getSwords().size(); i++){
			//tmp.setBounds(game.getSwords().get(i).getPosition().x*30, game.getSwords().get(i).getPosition().y*30, 30, 30);
		}

		for(int i = 0; i < game.getMaze().length; i++) {
			for(int j = 0; j < game.getMaze()[i].length; j++) {
				switch(game.getMaze()[i][j].getType()){
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
	}
}
