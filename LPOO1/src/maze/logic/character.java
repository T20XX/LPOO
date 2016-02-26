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
		case 'W':
			y--;
			break;

		case 'S':
			y++;
			break;

		case 'A':
			x--;
			break;

		case 'D':
			x++;
			break;

		default:
			break;
		}
	}

}
