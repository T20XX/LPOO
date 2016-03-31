package maze.logic;

import java.util.Random;

/**
 * Represents a dragon, character able to sleep
 */
public class dragon extends character{
	private boolean sleeping;

	/**
	 * Creates a new dragon
	 * A dragon is not sleeping when is instantiated
	 * @param x Initial x axis position
	 * @param y Initial x axis position
	 * @param atri Symbol that represents the dragon
	 */
	public dragon(int x, int y, char atri){
		super(x,y,atri);
		sleeping = false;
	}

	/**
	 * Returns on whether the dragon is sleeping or not
	 * @return True if the dragon is sleeping and false if it's not
	 */
	public boolean getSleeping(){
		return sleeping;
	}

	/**
	 * Updates dragon position randomly
	 * @param up Space upside dragon
	 * @param down Space downside dragon
	 * @param left Space on the left side of the dragon
	 * @param right Space on the right side of the dragon
	 */
	public void move(space up, space down, space left, space right){
		Random rn = new Random();
		String possDir = "";
		if (up.getAllowMove())
			possDir+= 'N';
		if (down.getAllowMove())
			possDir+= 'S';
		if (left.getAllowMove())
			possDir+= 'O';
		if (right.getAllowMove())
			possDir+= 'E';
		switch(possDir.charAt(rn.nextInt(possDir.length()))){
		case 'N':
			moveUp();
			break;

		case 'S':
			moveDown();
			break;

		case 'O':
			moveLeft();
			break;

		case 'E':
			moveRight();
			break;

		default:
			break;
		}
	}

	/**
	 * Updates dragon state randomly from stay stopped, move, fall asleep or wake up
	 * @param up Space upside dragon
	 * @param down Space downside dragon
	 * @param left Space on the left side of the dragon
	 * @param right Space on the right side of the dragon
	 */
	public void moveOrSleep(space up, space down, space left, space right){
		Random rn = new Random();
		String possDir = "";
		if(sleeping){
			switch(rn.nextInt(2)){
			case 0:
				sleeping = false;
				atri = 'D';
				break;
			case 1:
				break;
			}
		}
		else {
			switch(rn.nextInt(3)){
			case 0:
				sleeping = true;
				atri = 'd';
				break;
			case 1:
				break;
			case 2:
				if (up.getAllowMove())
					possDir+= 'N';
				if (down.getAllowMove())
					possDir+= 'S';
				if (left.getAllowMove())
					possDir+= 'O';
				if (right.getAllowMove())
					possDir+= 'E';
				switch(possDir.charAt(rn.nextInt(possDir.length()))){
				case 'N':
					moveUp();
					break;

				case 'S':
					moveDown();
					break;

				case 'O':
					moveLeft();
					break;

				case 'E':
					moveRight();
					break;

				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	}
}