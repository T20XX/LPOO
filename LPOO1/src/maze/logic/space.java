package maze.logic;

public class space {
	public enum spaceType {FREE,WALL,EXIT};
	spaceType type;
	private char atri;
	private boolean allowMove;

	public space(char atri){
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
	public void setAtri(char atri){
		this.atri = atri;
	}

	public char getAtri(){
		return atri;
	}
	
	public boolean getAllowMove(){
		return allowMove;
	}
	public spaceType getType() {
		return type;
	}
}
