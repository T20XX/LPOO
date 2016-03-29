package maze.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import maze.logic.space.spaceType;

public class game {


	public enum gameState{
		HERO_UNARMED,
		HERO_ARMED,
		GAMEOVER,
		WIN
	};
	//public enum gameMode{EASY,MEDIUM,HARD};

	//private int MAX_DRAGON_NUM = 10;
	//private int MAX_SWORD_NUM = 10;

	private hero h;
	//private gameMode gamemode;
	private ArrayList<dragon> d;
	private ArrayList<sword> s;
	private space[][] maze;
	//private int dragon_num = 0;
	//private int sword_num = 0;
	private gameState state;

	public static void main(String[] args) throws IOException {
		MazeBuilder mb = new MazeBuilder();
		mb.buildMazetoTXT("1.txt",27, 3);

	}

	public game(String path) throws IOException{
		d = new ArrayList<dragon>();
		s = new ArrayList<sword>();
		//d = new dragon[MAX_DRAGON_NUM];
		//s = new sword[MAX_SWORD_NUM];
		state = gameState.HERO_UNARMED;
		//this.gamemode = gamemode;

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

	public boolean moveHeroUp(){
		if (maze[h.getPosition().y-1][h.getPosition().x].getAllowMove()){
			h.moveUp();
			return true;
		} else {
			return false;
		}

	}

	public boolean moveHeroDown(){
		if (maze[h.getPosition().y+1][h.getPosition().x].getAllowMove()){
			h.moveDown();		
			return true;
		} else {
			return false;
		}
	}

	public boolean moveHeroLeft(){
		if (maze[h.getPosition().y][h.getPosition().x-1].getAllowMove()){
			h.moveLeft();
			return true;
		} else {
			return false;
		}
	}

	public boolean moveHeroRight(){
		if (maze[h.getPosition().y][h.getPosition().x+1].getAllowMove()){
			h.moveRight();
			return true;
		} else {
			return false;
		}
	}

	public void moveDragons(){
		for(int i = 0; i < d.size(); i++){
			d.get(i).move(
					maze[d.get(i).getPosition().y-1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y+1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x-1],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x+1]);
		}
	}

	public void moveOrSleepDragons(){
		for(int i = 0; i < d.size(); i++){
			d.get(i).moveOrSleep(
					maze[d.get(i).getPosition().y-1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y+1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x-1],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x+1]);
		}
	}


	public void updateGameState(){

		//Arm the hero when it founds a sword
		if (state == gameState.HERO_UNARMED){
			for(int i = 0; i < s.size(); i++){
				if (h.getPosition().x == s.get(i).getPosition().x && h.getPosition().y == s.get(i).getPosition().y){
					h.setSword(s.get(i));
					state = gameState.HERO_ARMED;
				}
			}
		}

		if(d.size() == 0)
		{
			//Checks if hero can exit
			if(maze[h.getPosition().y][h.getPosition().x].getType() == spaceType.EXIT){
				state = gameState.WIN;
			}
		} else {
			//Checks if hero battle or die with all dragons
			for(int i = 0; i < d.size(); i++){
				if(((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x)-1 == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x)+1 == h.getPosition().x && d.get(i).getPosition().y == h.getPosition().y) ||
						((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y-1 == h.getPosition().y) ||
						((d.get(i).getPosition().x) == h.getPosition().x && d.get(i).getPosition().y+1 == h.getPosition().y)){
					if(state == gameState.HERO_UNARMED){
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
