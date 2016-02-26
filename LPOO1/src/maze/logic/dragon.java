package maze.logic;

public class dragon extends character{
	private boolean isdead;
	
	public dragon(int x, int y){
		super(x,y);
	}
	
	public boolean isDead(){
		return isdead;
	}
	
}
