package maze.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import maze.logic.Game;
import maze.logic.Game.gameState;

public class TestDragonsRandomBehavior {

	@Test(timeout=1000)
	public void testDragonsMove() throws IOException {
		Game g = new Game("Test.txt");
		boolean outcome1 = false, outcome2 = false;
		while (! outcome1 || ! outcome2) {
			if (g.getState() == gameState.GAMEOVER)
				outcome1 = true;
			else if (g.getState() == gameState.HERO_UNARMED)
				outcome2 = true;
			else
				fail("Game reached impossible game state");
			g.moveDragons();
			g.updateGameState();
		}
	}
	
	@Test(timeout=1000)
	public void testDragonsMoveOrSleep() throws IOException {
		Game g = new Game("Test.txt");
		boolean outcome1 = false, outcome2 = false;
		while (! outcome1 || ! outcome2) {
			if (g.getState() == gameState.GAMEOVER)
				outcome1 = true;
			else if (g.getState() == gameState.HERO_UNARMED)
				outcome2 = true;
			else
				fail("Game reached impossible game state");
			g.moveOrSleepDragons();
			g.updateGameState();
		}
	}

}
