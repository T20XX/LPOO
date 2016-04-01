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
import maze.logic.Space.spaceType;


public class GamePanel extends JPanel {
	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage free;
	private BufferedImage hero;
	private BufferedImage dragon;
	private int x=0, y=0, width=50, height=50;
	
	private Game g;

	public GamePanel() throws IOException {
		try {
			wall = ImageIO.read(new File("img/wall.png"));
			free = ImageIO.read(new File("img/free.png"));
			hero = ImageIO.read(new File("img/hero.png"));
			sword = ImageIO.read(new File("img/sword.png"));	
			dragon = ImageIO.read(new File("img/dragon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g = new Game("Map");

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
		//g.drawImage(hero, x, y, x + width - 1, y + height - 1, 0, 0, hero.getWidth(), hero.getHeight(), null);
		
		//JÁ PUS ISTO AQUI, VE NA API COMO FUNCIONA O DRAWIMAGE
		
		/*for(int i = 0; i < g.getDragons().size(); i++){
			tmp.setBounds(g.getDragons().get(i).getPosition().x*30, g.getDragons().get(i).getPosition().y*30, 30, 30);
			
		}

		for(int i = 0; i < g.getSwords().size(); i++){
			tmp.setBounds(g.getSwords().get(i).getPosition().x*30, g.getSwords().get(i).getPosition().y*30, 30, 30);
		}

		for(int i = 0; i < g.getMaze().length; i++) {
			for(int j = 0; j < g.getMaze()[i].length; j++) {
					tmp.setBounds(j*30, i*30, 30, 30);
					//System.out.print((j+1)*20);
					//System.out.println((i+1)*20);
					//f.add(tmp);
					//temp.add(tmp);
				//} else if (g.getMaze()[i][j].getType() == spaceType.FREE){
					//tmp = new JLabel(free_img); 
					tmp.setBounds(j*30, i*30, 30, 30);
					//f.add(tmp);
					//temp.add(tmp);
				//}
			}
		}*/
	}

}
