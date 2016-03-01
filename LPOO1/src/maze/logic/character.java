package maze.logic;

import java.awt.Point;

public class character {
	protected Point position;
	protected char atri;

	public character(int x, int y, char atri){
		position = new Point(x,y);
		this.atri = atri;
	}

	public Point getPosition(){
		return position;
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
			position.y--;
			break;

		case 'S':
			position.y++;
			break;

		case 'O':
			position.x--;
			break;

		case 'E':
			position.x++;
			break;

		default:
			break;
		}
	}

}
