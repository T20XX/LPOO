package maze.logic;


/**
 * Represents a cell in the maze
 */
public class Space {
	/**
	 * Type of space
	 */
	public enum spaceType {
		/**
		 * Free space where the characters and sword are able to be and move
		 */
		FREE,
		/**
		 * Wall space where the characters are not able to be or go through
		 */
		WALL,
		/**
		 * Exit space to get out of the maze
		 */
		EXIT
		};
	private spaceType type;
	private char atri;
	private boolean allowMove;

	/**
	 * Creates a new space(cell)
	 * @param atri Char that represents the space
	 */
	public Space(char atri){
		this.atri = atri;
		switch(atri){
		case ' ':
			type = spaceType.FREE;
			allowMove = true;
			break;

		case 'X':
			type = spaceType.WALL;
			allowMove = false;
			break;

		case 'S':
			type = spaceType.EXIT;
			allowMove = true;
			break;

		default:
			break;
		}
	}
	
	/**
	 * Sets char to represent the space 
	 * @param atri Char to represent the space
	 */
	public void setAtri(char atri){
		this.atri = atri;
	}

	/**
	 * Returns char that represents the space
	 * @return Char that represents the space
	 */
	public char getAtri(){
		return atri;
	}
	
	/**
	 * Returns if the space allows the move/existence of character or sword
	 * @return Allow to move value (True or false)
	 */
	public boolean getAllowMove(){
		return allowMove;
	}
	
	/**
	 * Returns type of the space
	 * @return Space type
	 */
	public spaceType getType() {
		return type;
	}
}
