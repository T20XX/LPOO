package maze.logic;

import java.awt.Point;

/**
 * Represents a sword that hero can pick
 */
public class Sword {
	private Point position;

	/**
	 * Creates a new sword
	 * @param x Initial x axis position
	 * @param y Initial y axis position
	 */
	public Sword(int x, int y){
		position = new Point(x,y);
	}
 
	/**
	 * Returns sword position
	 * @return Sword position
	 */
	public Point getPosition(){
		return position;
	}

	/**
	 * Set x axis sword position
	 * @param x New x axis coordinate 
	 */
	public void setX(int x) {
		position.x = x;
	}

	/**
	 * Set y axis sword position
	 * @param y New y axis coordinate
	 */
	public void setY(int y) {
		position.y=y;

	}
}
