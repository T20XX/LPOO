package maze.cli;

import java.io.IOException;
import java.util.Scanner;

import maze.logic.game;
import maze.logic.game.gameState;

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
		game g = new game("Map",gamemode);

		while (g.getState() == game.gameState.RUNNING){
			g.print();

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

}
