package maze.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import maze.logic.space.spaceType;

public class game {

	public enum gameState{RUNNING,GAMEOVER,WIN};

	//private int MAX_DRAGON_NUM = 10;
	//private int MAX_SWORD_NUM = 10;

	private hero h;
	private int gamemode;
	private ArrayList<dragon> d;
	private ArrayList<sword> s;
	private space[][] maze;
	//private int dragon_num = 0;
	//private int sword_num = 0;
	private gameState state;

	public static void main(String[] args) throws IOException {
		MazeBuilder mb = new MazeBuilder();
		mb.buildMazetoTXT("1.txt",27);

	}

	public game(String path, int gamemode) throws IOException{

		d = new ArrayList<dragon>();
		s = new ArrayList<sword>();
		//d = new dragon[MAX_DRAGON_NUM];
		//s = new sword[MAX_SWORD_NUM];
		state = gameState.RUNNING;
		this.gamemode = gamemode;

		String  line = null;
		int j = 1;
		char temp;
		try{
			
			// open input stream for reading purpose.
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			//reads first line to get maze square size
			line = br.readLine();
			
			//initializes maze array with size of first line
			maze = new space[line.length()][line.length()];
			
			//fills first array with first line
			for(int i = 0; i < line.length();i++){
				temp = line.charAt(i);
				maze[0][i] = new space(temp);
			}
			
			while ((line = br.readLine()) != null) {
				for(int i = 0; i < line.length();i++){
					temp = line.charAt(i);
					switch (temp){
					case 'D':
						d.add(new dragon(i,j,'D'));
						temp = ' ';
						break;

					case 'H':
						h = new hero(i,j, 'H');
						temp = ' ';
						break;

					case 'E':
						s.add(new sword(i,j));
						temp = ' ';
						break;

					default:
						break;
					}
					maze[j][i] = new space(temp);
				}
				j++;
			}

			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void update(char kbd_input){
		/*if (heroe_dir == 'W' || heroe_dir == 'A' ||heroe_dir == 'S' || heroe_dir == 'D'){
			h.move(heroe_dir);
		}*/
		switch (kbd_input){
		case 'W':
			if (maze[h.getPosition().y-1][h.getPosition().x].getAllowMove())
				h.move('N');
			else
				return;
			break;

		case 'S':
			if (maze[h.getPosition().y+1][h.getPosition().x].getAllowMove())
				h.move('S');
			else
				return;
			break;

		case 'A':
			if (maze[h.getPosition().y][h.getPosition().x-1].getAllowMove())
				h.move('O');
			else
				return;
			break;

		case 'D':
			if (maze[h.getPosition().y][h.getPosition().x+1].getAllowMove())
				h.move('E');
			else
				return;
			break;

		default:
			return;
		}

		if (h.getSword() == null){
			for(int i = 0; i < s.size(); i++){
				if (h.getPosition().x == s.get(i).getPosition().x && h.getPosition().y == s.get(i).getPosition().y){
					h.setSword(s.get(i));
				}
			}
		}
		else{

		}

		if(d.size() != 0)
		{
			for(int i = 0; i < d.size(); i++){
				d.get(i).update(gamemode,
						maze[d.get(i).getPosition().y-1][d.get(i).getPosition().x],
						maze[d.get(i).getPosition().y+1][d.get(i).getPosition().x],
						maze[d.get(i).getPosition().y][d.get(i).getPosition().x-1],
						maze[d.get(i).getPosition().y][d.get(i).getPosition().x+1]);

				if(((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x)-1 == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x)+1 == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y-1 == h.getPosition().y) ||
						((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y+1 == h.getPosition().y)){
					if(h.getSword() == null){
						if(!d.get(i).getSleeping())
							state = gameState.GAMEOVER;
					}
					else {
						d.remove(i);
						i--;
					}
				}
			}
		}
		else{
			if(maze[h.getPosition().y][h.getPosition().x].getType() == spaceType.EXIT){
				state = gameState.WIN;
			}
		}
	}


	public void print(){
		char tmp[][] = new char[maze.length][maze[0].length];

		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				tmp[i][j] = maze[i][j].getAtri();
			}
		}

		for(int i = 0; i < d.size(); i++){
			tmp[d.get(i).getPosition().y][d.get(i).getPosition().x] = d.get(i).getAtri();
			//tempa.get(d.get(i).getPosition().y).get(d.get(i).getPosition().x).equals('D');
		}

		for(int i = 0; i < s.size(); i++){
			if(tmp[s.get(i).getPosition().y][s.get(i).getPosition().x] == 'D')
				tmp[s.get(i).getPosition().y][s.get(i).getPosition().x] = 'F';
			else 
				tmp[s.get(i).getPosition().y][s.get(i).getPosition().x] = 'E';
			//tempa.get(s.get(i).getPosition().y).get(s.get(i).getPosition().x).equals('S');
		}


		tmp[h.getPosition().y][h.getPosition().x] = h.getAtri();
		//tempa.get(h.getPosition().y).get(h.getPosition().x).equals('H');

		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
	}

	public gameState getState(){
		return state;
	}

	public space[][] getMaze(){
		return maze;
	}

	public hero getHero(){
		return h;
	}

	public ArrayList<dragon> getDragons(){
		return d;
	}

	public ArrayList<sword> getSwords(){
		return s;
	}

	/*public void DequalsS(){
		for(int i = 0; i < d.size(); i++){
			for(int j = 0; j < s.size();j++){
				if(d.get(i).getPosition().x == s.get(j).getPosition().x && d.get(i).getPosition().y == s.get(j).getPosition().y){
					maze[d.get(i).getPosition().x][d.get(i).getPosition().y].setAtri('F');
				}
				else {
					maze[d.get(i).getPosition().x][d.get(i).getPosition().y].setAtri('D');	
					maze[s.get(i).getPosition().x][s.get(i).getPosition().y].setAtri('S');
				}
			}
		}
	}*/

}
