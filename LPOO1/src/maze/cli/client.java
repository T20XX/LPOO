package maze.cli;

import java.io.IOException;
import java.util.Scanner;

import maze.logic.game;

public class client {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		char sel;
		game g = new game("C:\\Users\\Telmo\\git\\lpoo1\\LPOO1\\Map");
		
		while (g.getState() == game.gameState.RUNNING){
			sel = s.next().charAt(0);
			sel = Character.toUpperCase(sel);
			System.out.println(sel);
			g.update(sel);

			g.print();
		}
		s.close();

	}

}
