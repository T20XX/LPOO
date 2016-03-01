package maze.logic;

import java.awt.Point;

public class sword {
	private Point position;

	public sword(int x, int y){
		position = new Point(x,y);
	}

	public Point getPosition(){
		return position;
	}

	public void setX(int x) {
		position.x = x;
	}

	public void setY(int y) {
		position.y=y;

	}
}
