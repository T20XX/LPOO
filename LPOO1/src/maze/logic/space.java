package maze.logic;

public class space {
	public enum spaceType {FREE,WALL,EXIT};
	spaceType type;
	private char atri;

	public space(char atri){
		this.atri = atri;
		switch(atri){
		case ' ':
			type = spaceType.FREE;
			break;

		case 'X':
			type = spaceType.WALL;
			break;

		case 'S':
			type = spaceType.EXIT;
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

	private boolean canMove(){
		switch (type){
		case WALL:
			return false;

		case FREE:
			return true;

		case EXIT:
			return true;

		default:
			return false;
		}
		/*if (this.type == spaceType.WALL){
			// == WALL)
		}
		if(this.atri == 'D' || this.atri == 'W'){
			return false;
		}
		return true;*/
	}
}
