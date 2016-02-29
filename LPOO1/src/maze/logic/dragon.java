package maze.logic;

import java.util.Random;
import java.util.ArrayList;

public class dragon extends character{
	private boolean isdead;
	
	public dragon(int x, int y, char atri){
		super(x,y,atri);
	}
	
	public boolean isDead(){
		return isdead;
	}
	
	//Updates dragon state randomly from stay stopped, move, fall asleep or wake up
	public void update(space up, space down, space left, space right){
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
			
			move(possDir.charAt(rn.nextInt(possDir.length())));		
	}
}
