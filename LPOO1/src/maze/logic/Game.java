package maze.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import maze.logic.Space.spaceType;

/**
 * Represents the game itself containing the maze, hero, dragons, sword, game states, etc.
 */
public class Game {

	/**
	 *	Game states
	 */
	public enum gameState{
		/**
		 * Game is running and the hero doesn't have a sword
		 */
		HERO_UNARMED,
		/**
		 * Game is running and the hero have a sword
		 */
		HERO_ARMED,
		/**
		 * Game ended with the hero lose (Hero died from a dragon)
		 */
		GAMEOVER,
		/**
		 * Game ended with the hero win (Hero killed all the dragons and found the exit)
		 */
		WIN
	};
	//public enum gameMode{EASY,MEDIUM,HARD};

	//private int MAX_DRAGON_NUM = 10;
	//private int MAX_SWORD_NUM = 10;

	private Hero h;
	//private gameMode gamemode;
	private ArrayList<Dragon> d;
	private ArrayList<Sword> s;
	private Space[][] maze;
	//private int dragon_num = 0;
	//private int sword_num = 0;
	private gameState state;
	private boolean exitClosed = true;

	//	public static void main(String[] args) throws IOException {
	//		MazeBuilder mb = new MazeBuilder();
	//		mb.buildMazetoTXT("1.txt",27, 3);
	//
	//	}

	/**
	 * Creates a new game based on a maze
	 * @param path Path to .txt file that contains the maze
	 */
	public Game(String path) throws IOException{
		d = new ArrayList<Dragon>();
		s = new ArrayList<Sword>();
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
			maze = new Space[line.length()][line.length()];

			//fills first array with first line
			for(int i = 0; i < line.length();i++){
				temp = line.charAt(i);
				maze[0][i] = new Space(temp);
			}

			while ((line = br.readLine()) != null) {
				for(int i = 0; i < line.length();i++){
					temp = line.charAt(i);
					switch (temp){
					case 'D':
						d.add(new Dragon(i,j,'D'));
						temp = ' ';
						break;

					case 'H':
						h = new Hero(i,j, 'H');
						temp = ' ';
						break;

					case 'E':
						s.add(new Sword(i,j));
						temp = ' ';
						break;

					default:
						break;
					}
					maze[j][i] = new Space(temp);
				}
				j++;
			}

			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * Moves hero up in the maze
	 * @return True if the hero could move and false if the hero stayed in the same position
	 */
	public boolean moveHeroUp(){
		if (maze[h.getPosition().y-1][h.getPosition().x].getAllowMove()){
			h.moveUp();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Moves hero down in the maze
	 * @return True if the hero could move and false if the hero stayed in the same position
	 */
	public boolean moveHeroDown(){
		if (maze[h.getPosition().y+1][h.getPosition().x].getAllowMove()){
			h.moveDown();		
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves hero left in the maze
	 * @return True if the hero could move and false if the hero stayed in the same position
	 */
	public boolean moveHeroLeft(){
		if (maze[h.getPosition().y][h.getPosition().x-1].getAllowMove()){
			h.moveLeft();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves hero right in the maze
	 * @return True if the hero could move and false if the hero stayed in the same position
	 */
	public boolean moveHeroRight(){
		if (maze[h.getPosition().y][h.getPosition().x+1].getAllowMove()){
			h.moveRight();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Move all dragons randomly
	 */
	public void moveDragons(){
		for(int i = 0; i < d.size(); i++){
			d.get(i).move(
					maze[d.get(i).getPosition().y-1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y+1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x-1],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x+1]);
		}
	}

	/**
	 * Updates all dragons state randomly from stay stopped, move, fall asleep or wake up
	 */
	public void moveOrSleepDragons(){
		for(int i = 0; i < d.size(); i++){
			d.get(i).moveOrSleep(
					maze[d.get(i).getPosition().y-1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y+1][d.get(i).getPosition().x],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x-1],
					maze[d.get(i).getPosition().y][d.get(i).getPosition().x+1]);
		}
	}

	/**
	 * Updates game state
	 * Checks if the hero picks the sword and if he get killed or kills any dragon
	 */
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
			//opens exit after killing all dragons
			if (exitClosed){
			for(int y = 0; y < maze.length; y++) {
				for(int x = 0; x < maze[y].length; x++) {
					if (maze[y][x].getType() == spaceType.EXIT){
						maze[y][x].setAllowMove(true);
						exitClosed = false;
					}
				}
			}
			}
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

	/**
	 * Returns game state
	 * @return Game state
	 */
	public gameState getState(){
		return state;
	}

	/**
	 * Returns maze
	 * @return 2D-Array that represents the maze
	 */
	public Space[][] getMaze(){
		return maze;
	}

	/**
	 * Returns hero
	 * @return Hero
	 */
	public Hero getHero(){
		return h;
	}

	/**
	 * Returns all dragons in game
	 * @return All dragons in game
	 */
	public ArrayList<Dragon> getDragons(){
		return d;
	}

	/**
	 * Returns all swords in game, hero's and in the floor to be picked up
	 * @return All swords in game
	 */
	public ArrayList<Sword> getSwords(){
		return s;
	}
	
	/**
	 * Returns if the exit is open or close
	 * @return True if the exit is closed and false if the exit is opened
	 */
	public boolean getExitClosed(){
		return exitClosed;
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
