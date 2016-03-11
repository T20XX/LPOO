package maze.cli;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import maze.logic.game;
import maze.logic.game.gameState;
import maze.logic.space.spaceType;

public class client {

	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		char sel;
		int gamemode = 0;
		boolean heroMoved = true;

		System.out.println("GAME MODE:");
		System.out.println("1. Dragons don't move");
		System.out.println("2. Dragons move randomly");
		System.out.println("3. Dragons move randomly and fall asleep");
		while (gamemode<1 || gamemode>3){
			gamemode = s.nextInt();
		}
		game g = new game("Map");

		while (g.getState() == game.gameState.HERO_UNARMED || g.getState() == game.gameState.HERO_ARMED){
			print(g);
			sel = s.next().charAt(0);
			sel = Character.toUpperCase(sel);
			switch(sel){
			case 'W':
				heroMoved = g.moveHeroUp();
				break;
			case 'S':
				heroMoved = g.moveHeroDown();
				break;
			case 'A':
				heroMoved = g.moveHeroLeft();
				break;
			case 'D':
				heroMoved = g.moveHeroRight();
			default:
				heroMoved = false;
				break;
			}
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
		}

		print(g);
		if(g.getState() == gameState.GAMEOVER){
			System.out.println("Hero was killed by a dragon!");
		}
		else{
			System.out.println("Hero slained all dragons and found his way out of the maze!");
		}
		s.close();
	}
	
	private static JFrame initializeFrame(){
		JFrame f = new JFrame();
		f.setSize(1000, 750);
		f.setResizable(true);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Hero Maze");
		f.setLayout(null);
		
		return f;
	}

	private static void draw(JFrame f, game g){
		

		ImageIcon wall_img = new ImageIcon("img\\wall_img.png");
		ImageIcon sword_img = new ImageIcon("img\\sword_img.png");
		ImageIcon free_img = new ImageIcon("img\\free_img.png");
		ImageIcon hero_img = new ImageIcon("img\\hero_img.png");
		ImageIcon dragon_img = new ImageIcon("img\\dragon_img.png");
		JLabel tmp;

		f.getContentPane().removeAll();
		//char temp[][] = new char[g.getMaze().length][g.getMaze()[0].length];

		for(int i = 0; i < g.getDragons().size(); i++){
			tmp = new JLabel(dragon_img); 
			tmp.setBounds(g.getDragons().get(i).getPosition().x*30, g.getDragons().get(i).getPosition().y*30, 30, 30);
			f.add(tmp);
			//temp.add(tmp);
		}

		for(int i = 0; i < g.getSwords().size(); i++){
			tmp = new JLabel(sword_img); 
			tmp.setBounds(g.getSwords().get(i).getPosition().x*30, g.getSwords().get(i).getPosition().y*30, 30, 30);
			//System.out.print((j+1)*20);
			//System.out.println((i+1)*20);
			f.add(tmp);
			//temp.add(tmp);
		}
		tmp = new JLabel(hero_img); 
		tmp.setBounds(g.getHero().getPosition().x*30, g.getHero().getPosition().y*30, 30, 30);
		f.add(tmp);
		//temp.add(tmp);
		
		for(int i = 0; i < g.getMaze().length; i++) {
			for(int j = 0; j < g.getMaze()[i].length; j++) {
				if (g.getMaze()[i][j].getType() == spaceType.WALL){
					tmp = new JLabel(wall_img); 
					tmp.setBounds(j*30, i*30, 30, 30);
					//System.out.print((j+1)*20);
					//System.out.println((i+1)*20);
					f.add(tmp);
					//temp.add(tmp);
				} else if (g.getMaze()[i][j].getType() == spaceType.FREE){
					tmp = new JLabel(free_img); 
					tmp.setBounds(j*30, i*30, 30, 30);
					f.add(tmp);
					//temp.add(tmp);
				}
			}
		}

		/*for(int i = 0; i < d.size(); i++){
			tmp[d.get(i).getY()][d.get(i).getX()] = d.get(i).getAtri();
			//tempa.get(d.get(i).getY()).get(d.get(i).getX()).equals('D');
		}*/

		/*for(int i= 0; i< temp.size();i++){
			f.add(temp.get(i));
		}*/
		f.getContentPane().revalidate();
		f.getContentPane().repaint();
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
