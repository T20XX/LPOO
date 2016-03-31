package maze.logic;

/**
 * Represents a hero, character with a sword
 */
public class hero extends character{

	private sword s;
	
	/**
	 * Creates a new hero
	 * @param x Initial x axis position
	 * @param y Initial x axis position
	 * @param atri Symbol that represents the hero
	 */
	public hero(int x, int y, char atri){
		super(x,y,atri);
		s = null;
	}

	/**
	 * Returns the sword of the hero
	 * @return Hero's sword or null in case he doesn't have one
	 */
	public sword getSword(){
		return s;
	}

	/**
	 * Sets hero's sword
	 * @param s Sword to set
	 */
	public void setSword(sword s){
		this.s = s;
		atri = 'A';
	}

	/**
	 * Decrements y axis position
	 * Move hero and sword up
	 */
	public void moveUp(){
		super.moveUp();
		moveSwordAlong();
	}
	
	/**
	 * Increments y axis position
	 * Move hero and sword down
	 */
	public void moveDown(){
		super.moveDown();
		moveSwordAlong();
	}
	
	/**
	 * Decrements x axis position
	 * Move hero and sword left
	 */
	public void moveLeft(){
		super.moveLeft();
		moveSwordAlong();
	}
	
	/**
	 * Increments x axis position
	 * Move hero and sword right
	 */
	public void moveRight(){
		super.moveRight();
		moveSwordAlong();
	}
	
	/**
	 * Sets hero's sword position to his own position
	 */
	public void moveSwordAlong(){
		if (s != null){
			s.setX(position.x);
			s.setY(position.y);
		}
	}
}
