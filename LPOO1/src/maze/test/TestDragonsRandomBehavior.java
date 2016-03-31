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
			g.moveDragons();
			g.updateGameState();
			if (g.getState() == gameState.GAMEOVER)
				outcome1 = true;
			else if (g.getState() == gameState.HERO_UNARMED)
				outcome2 = true;
			else
				fail("some error message");
		}
	}
	
	@Test(timeout=1000)
	public void testDragonsMoveOrSleep() throws IOException {
		Game g = new Game("Test.txt");
		boolean outcome1 = false, outcome2 = false;
		while (! outcome1 || ! outcome2) {
			g.moveOrSleepDragons();
			g.updateGameState();
			if (g.getState() == gameState.GAMEOVER)
				outcome1 = true;
			else if (g.getState() == gameState.HERO_UNARMED)
				outcome2 = true;
			else
				fail("some error message");
		}
	}

}
