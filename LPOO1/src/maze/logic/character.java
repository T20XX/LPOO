package maze.logic;

public class character {
	private int x;
	private int y;

	public character(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
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
