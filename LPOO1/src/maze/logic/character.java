package maze.logic;

public class character {
	protected int x;
	protected int y;
	protected char atri;

	public character(int x, int y, char atri){
		this.x = x;
		this.y = y;
		this.atri = atri;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public char getAtri(){
		return atri;
	}
	
	public void setAtri(char a){
		atri = a;
	}

	public void move(char dir){
		switch (dir){
		case 'N':
			y--;
			break;

		case 'S':
			y++;
			break;

		case 'O':
			x--;
			break;

		case 'E':
			x++;
			break;

		default:
			break;
		}
	}

}
