package maze.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class CreateMazePanel extends JPanel {
	private BufferedImage wall;
	private BufferedImage hero;
	private BufferedImage sword;
	private BufferedImage dragon;
	private BufferedImage exit;
	private BufferedImage delete;
	private int x=0, y=0, width=100, height=100;

	public CreateMazePanel() {
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

		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
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
		//Draws walls on borders
		for(int x = 0; x < 10; x++){
			g.drawImage(wall, x*50, 0, (x+1)*50, 50, 0, 0, wall.getWidth(), wall.getHeight(), null);
			g.drawImage(wall, x*50, 450, (x+1)*50, 500, 0, 0, wall.getWidth(), wall.getHeight(), null);
		}

		for(int y = 1; y < 9; y++){
			g.drawImage(wall, 0, y*50, 50, (y+1)*50, 0, 0, wall.getWidth(), wall.getHeight(), null);
			g.drawImage(wall, 450, y*50, 500, (y+1)*50, 0, 0, wall.getWidth(), wall.getHeight(), null);
		}

		//Draws grid
		for(int x = 1; x < 9; x++){
			for(int y = 1; y < 9; y++){
				g.drawRect(x*50, y*50, 50, 50);
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

}
