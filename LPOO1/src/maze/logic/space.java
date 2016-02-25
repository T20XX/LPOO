package maze.logic;

public class space {
	private char atri;
	
	public space(char atri){
		this.atri = atri;
	}
	public void setAtri(char atri){
		this.atri = atri;
	}
	
	public char getAtri(){
		return atri;
	}
	
	private boolean canMove(){
		if(this.atri == 'D' || this.atri == 'W'){
			return false;
		}
		return true;
	}
}
