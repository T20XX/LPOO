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
	
	public void moveUp(){
		position.y--;
	}
	
	public void moveDown(){
		position.y++;
	}
	
	public void moveLeft(){
		position.x--;
	}
	
	public void moveRight(){
		position.x++;
	}
}
