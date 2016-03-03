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
		System.out.println("GAME MODE:");
		System.out.println("1. Dragons don't move");
		System.out.println("2. Dragons move randomly");
		System.out.println("3. Dragons move randomly and fall asleep");
		while (gamemode<1 || gamemode>3){
			gamemode = s.nextInt();
		}
		game g = new game("1.txt",gamemode);

		while (g.getState() == game.gameState.RUNNING){
			//g.print();
			draw(g);

			sel = s.next().charAt(0);
			sel = Character.toUpperCase(sel);
			g.update(sel);
		}
		g.print();
		if(g.getState() == gameState.GAMEOVER){
			System.out.println("Hero was killed by a dragon!");
		}
		else{
			System.out.println("Hero slained all dragons and found his way out of the maze!");
		}
		s.close();
	}

	private static void draw(game g){
		JFrame f = new JFrame();
		f.setSize(1000, 750);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Hero Maze");
		f.setLayout(new FlowLayout());

		ImageIcon wall_img = new ImageIcon("C:\\Users\\Telmo\\Desktop\\wall.png");
		ImageIcon sword_img = new ImageIcon("C:\\Users\\Telmo\\Desktop\\sword.png");
		ArrayList<JLabel> temp = new ArrayList<JLabel>();
		
		//char temp[][] = new char[g.getMaze().length][g.getMaze()[0].length];

		for(int i = 0; i < g.getMaze().length; i++) {
			for(int j = 0; j < g.getMaze()[i].length; j++) {
				if (g.getMaze()[i][j].getType() == spaceType.WALL){
					JLabel tmp = new JLabel(wall_img); 
					tmp.setBounds(j*30, i*30, 30, 30);
					System.out.print((j+1)*20);
					System.out.println((i+1)*20);

					temp.add(tmp);
				}
			}
		}
		
		/*for(int i = 0; i < d.size(); i++){
			tmp[d.get(i).getY()][d.get(i).getX()] = d.get(i).getAtri();
			//tempa.get(d.get(i).getY()).get(d.get(i).getX()).equals('D');
		}*/

		for(int i = 0; i < g.getSwords().size(); i++){
			JLabel tmp = new JLabel(sword_img); 
			tmp.setBounds(g.getSwords().get(i).getPosition().x*30, g.getSwords().get(i).getPosition().y*30, 30, 30);
			//System.out.print((j+1)*20);
			//System.out.println((i+1)*20);

			temp.add(tmp);
		}
		//tmp[h.getY()][h.getX()] = h.getAtri();

		for(int i= 0; i< temp.size();i++){
			f.add(temp.get(i));
		}
	}

}
