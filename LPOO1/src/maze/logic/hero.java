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

		//moves sword with heroe
		if (s != null){
			s.setX(x);
			s.setY(y);
		}
	}
}
