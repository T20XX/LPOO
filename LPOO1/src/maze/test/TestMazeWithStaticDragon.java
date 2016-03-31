package maze.test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import maze.logic.*;
import maze.logic.Game.gameState;

public class TestMazeWithStaticDragon {
	@Test
	public void testMoveHeroToFreeCell() throws IOException {
		Game g = new Game("Test.txt");
		assertEquals(1, g.getHero().getPosition().y);
		assertEquals(3, g.getHero().getPosition().x);
		g.moveHeroLeft();
		assertEquals(1, g.getHero().getPosition().y);
		assertEquals(2, g.getHero().getPosition().x);
	}
	@Test
	public void testMoveHeroToWall() throws IOException {
		Game g = new Game("Test.txt");
		assertEquals(1, g.getHero().getPosition().y);
		assertEquals(3, g.getHero().getPosition().x);
		g.moveHeroUp();
		assertEquals(1, g.getHero().getPosition().y);
		assertEquals(3, g.getHero().getPosition().x);
	}
	
	@Test
	public void testHeroPicksSword() throws IOException {
		Game g = new Game("Test.txt");
		assertEquals(gameState.HERO_UNARMED,g.getState());
		assertNull(g.getHero().getSword());
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		assertEquals(gameState.HERO_ARMED,g.getState());
		assertNotNull(g.getHero().getSword());
	}
	
	@Test
	public void testHeroKillsDragon() throws IOException {
		Game g = new Game("Test.txt");
		assertEquals(1,g.getDragons().size());
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		assertEquals(0,g.getDragons().size());
	}

	@Test
	public void testHeroDies() throws IOException {
		Game g = new Game("Test.txt");
		assertEquals(gameState.HERO_UNARMED,g.getState());
		g.moveHeroDown();
		g.updateGameState();
		assertEquals(gameState.GAMEOVER,g.getState());
	}
	@Test
	public void testExitsafterKillDragon() throws IOException{
		Game g = new Game("Test.txt");
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		g.moveHeroUp();
		g.updateGameState();
		g.moveHeroUp();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		assertEquals(gameState.WIN, g.getState());
	}
	@Test
	public void testExitsbeforeSword() throws IOException{
		Game g = new Game("Test.txt");
		g.moveHeroRight();
		g.updateGameState();
		assertEquals(gameState.HERO_UNARMED, g.getState());
	}
	@Test
	public void testExitsafterSwordBe4Dragon() throws IOException{
		Game g = new Game("Test.txt");
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroLeft();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroDown();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		g.moveHeroUp();
		g.updateGameState();
		g.moveHeroUp();
		g.updateGameState();
		g.moveHeroRight();
		g.updateGameState();
		assertEquals(gameState.WIN, g.getState());
	}
}