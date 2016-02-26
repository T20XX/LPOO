package maze.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import maze.logic.*;

public class game {
	
	public enum gameState{RUNNING,GAMEOVER,WIN};
	
	private int MAX_DRAGON_NUM = 10;
	private int MAX_SWORD_NUM = 10;

	private heroe h;
	private dragon d[];
	private sword s[];
	private space[][] maze;
	private int dragon_num = 0;
	private int sword_num = 0;
	private gameState state;

	public static void main(String[] args) throws IOException {
		//maze m = new maze();
		//m.read("C:\\Users\\Jorge\\git\\lpoo1\\LPOO1\\Map");
		//m.print();

	}

	public game(String path) throws IOException{
		maze = new space[10][10];
		d = new dragon[MAX_DRAGON_NUM];
		s = new sword[MAX_SWORD_NUM];
		state = gameState.RUNNING;

		int j = 0;
		for (String line : Files.readAllLines(Paths.get(path))){

			for(int i = 0; i < line.length();i++){
				char temp = line.charAt(i);
				switch (temp){
				case 'D':
					d[dragon_num] = new dragon(i,j);
					dragon_num++;
					temp = ' ';
					break;

				case 'H':
					h = new heroe(i,j);
					temp = ' ';
					break;

				case 'E':
					s[sword_num] = new sword(i,j);
					sword_num++;
					temp = ' ';
					break;

				default:
					break;
				}
				maze[j][i] = new space(temp);
			}
			j++;
		}
	}
	
	public void update(char heroe_dir){
		if (heroe_dir == 'W' || heroe_dir == 'A' ||heroe_dir == 'S' || heroe_dir == 'D'){
			h.move(heroe_dir);

		}
	}

	public void print(){
		char tmp[][] = new char[maze.length][maze[0].length];

		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				tmp[i][j] = maze[i][j].getAtri();
			}
		}

		tmp[h.getY()][h.getX()] = 'H';

		for(int i = 0; i < dragon_num; i++){
			tmp[d[i].getY()][d[i].getX()] = 'D';
		}

		for(int i = 0; i < sword_num; i++){
			tmp[s[i].getY()][s[i].getX()] = 'E';
		}

		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
		//System.out.
	}
	
	public gameState getState(){
		return state;
	}
}
