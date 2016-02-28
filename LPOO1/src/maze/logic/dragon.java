package maze.logic;

import java.util.Random;

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
			int n = rn.nextInt(4)+1;
			char d_dir = ' ';
			if(n == 1) //N
				if (up.getAllowMove())
					move('N');
				else
					return;
			if(n == 2)
				if (down.getAllowMove())
				move('S');
			else
				return;
			if(n == 3)
				if (left.getAllowMove())
				move('O');
			else
				return;
			if(n == 4)
				if (right.getAllowMove())
					move('E');
				else
					return;

		
	}
}
