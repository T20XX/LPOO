package maze.logic;

import java.util.Random;

public class dragon extends character{
	private boolean sleeping;

	public dragon(int x, int y, char atri){
		super(x,y,atri);
		sleeping = false;
	}

	public boolean getSleeping(){
		return sleeping;
	}

	//Updates dragon state randomly from stay stopped, move, fall asleep or wake up
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