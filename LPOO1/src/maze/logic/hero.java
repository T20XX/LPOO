package maze.logic;

public class hero extends character{

	sword s;

	public hero(int x, int y, char atri){
		super(x,y,atri);
		s = null;
	}

	public sword getSword(){
		return s;
	}

	public void setSword(sword s){
		this.s = s;
		atri = 'A';
	}

	public void moveUp(){
		super.moveUp();
		moveSwordAlong();
	}
	
	public void moveDown(){
		super.moveDown();
		moveSwordAlong();
	}
	
	public void moveLeft(){
		super.moveLeft();
		moveSwordAlong();
	}
	
	public void moveRight(){
		super.moveRight();
		moveSwordAlong();
	}
	
	public void moveSwordAlong(){
		if (s != null){
			s.setX(position.x);
			s.setY(position.y);
		}
	}
}
