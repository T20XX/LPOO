package maze.logic;

public class dragon extends character{
	private boolean isdead;
	
	public dragon(int x, int y, char atri){
		super(x,y,atri);
	}
	
	public boolean isDead(){
		return isdead;
	}
	
}
