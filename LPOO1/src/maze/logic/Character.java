package maze.logic;

import java.awt.Point;

/**
 * Represents characters like dragons and hero
 */
public class Character {
	protected Point position;
	protected char atri;

	/**
	 * Creates a new character
	 * @param x Initial x axis position
	 * @param y Initial x axis position
	 * @param atri Symbol that represents the character
	 */
	public Character(int x, int y, char atri){
		position = new Point(x,y);
		this.atri = atri;
	}

	/**
	 * Returns character position
	 * @return Character position
	 */
	public Point getPosition(){
		return position;
	}
	
	/**
	 * Returns char that represents the character
	 * @return Char that represents the character
	 */
	public char getAtri(){
		return atri;
	}
	
	/**
	 * Sets char to represent the character (useful when character changes state/appearance) 
	 * @param a Char to represent the character
	 */
	public void setAtri(char a){
		atri = a;
	}
	
	/**
	 * Decrements y axis position
	 * Move character up
	 */
	public void moveUp(){
		position.y--;
	}
	
	/**
	 * Increments y axis position
	 * Move character up
	 */
	public void moveDown(){
		position.y++;
	}
	
	/**
	 * Decrements x axis position
	 * Move character left
	 */
	public void moveLeft(){
		position.x--;
	}
	
	/**
	 * Increments x axis position
	 * Move character down
	 */
	public void moveRight(){
		position.x++;
	}
}
