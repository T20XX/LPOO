package maze.cli;

import java.io.IOException;
import java.util.Scanner;

import maze.logic.game;

public class client {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		char sel;
		int gamemode;
		System.out.println("Modo de Jogo: ");
		gamemode = s.nextInt();
		game g = new game("Map",gamemode);
		
		while (g.getState() == game.gameState.RUNNING){
			g.print();
			
			sel = s.next().charAt(0);
			g.update(sel);
		}
		
		s.close();

	}

}
