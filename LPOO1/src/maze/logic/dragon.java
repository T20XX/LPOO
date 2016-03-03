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
	public void update(int gamemode, space up, space down, space left, space right){
		Random rn = new Random();
		String possDir = "";
		switch(gamemode){
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
			move(possDir.charAt(rn.nextInt(possDir.length())));
			break;
		case 3:
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
					move(possDir.charAt(rn.nextInt(possDir.length())));
					break;	
				}
			}
			break;
		}
	}
}