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

	public void move(char dir){
		super.move(dir);

		//moves sword with heroe
		if (s != null){
			s.setX(position.x);
			s.setY(position.y);
		}
	}
}
