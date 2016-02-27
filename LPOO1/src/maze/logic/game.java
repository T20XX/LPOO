package maze.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.*;

public class game {

	public enum gameState{RUNNING,GAMEOVER,WIN};

	//private int MAX_DRAGON_NUM = 10;
	//private int MAX_SWORD_NUM = 10;

	private heroe h;
	private ArrayList<dragon> d;
	private ArrayList<sword> s;
	private space[][] maze;
	//private int dragon_num = 0;
	//private int sword_num = 0;
	private gameState state;

	public static void main(String[] args) throws IOException {
		//maze m = new maze();
		//m.read("C:\\Users\\Jorge\\git\\lpoo1\\LPOO1\\Map");
		//m.print();

	}

	public game(String path) throws IOException{
		maze = new space[10][10];
		d = new ArrayList<dragon>();
		s = new ArrayList<sword>();
		//d = new dragon[MAX_DRAGON_NUM];
		//s = new sword[MAX_SWORD_NUM];
		state = gameState.RUNNING;

		int j = 0;
		for (String line : Files.readAllLines(Paths.get(path))){

			for(int i = 0; i < line.length();i++){
				char temp = line.charAt(i);
				switch (temp){
				case 'D':
					//d[dragon_num] = new dragon(i,j);
					d.add(new dragon(i,j,'D'));
					temp = ' ';
					break;

				case 'H':
					h = new heroe(i,j, 'H');
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
	}

	public void update(char kbd_input){
		/*if (heroe_dir == 'W' || heroe_dir == 'A' ||heroe_dir == 'S' || heroe_dir == 'D'){
			h.move(heroe_dir);
		}*/
		switch (kbd_input){
		case 'W':
			if (maze[h.getY()-1][h.getX()].getAllowMove())
				h.move('N');
			break;

		case 'S':
			if (maze[h.getY()+1][h.getX()].getAllowMove())
				h.move('S');
			break;

		case 'A':
			if (maze[h.getY()][h.getX()-1].getAllowMove())
				h.move('O');
			break;

		case 'D':
			if (maze[h.getY()][h.getX()+1].getAllowMove())
				h.move('E');
			break;

		default:
			return;
		}
		Random rn = new Random();
		for(int i = 0; i < d.size(); i++){
			int n = rn.nextInt(4)+1;
			char d_dir = ' ';
			if(n == 1)
				d_dir = 'N';
			if(n == 2)
				d_dir = 'S';
			if(n == 3)
				d_dir = 'O';
			if(n == 4)
				d_dir = 'E';
			d.get(i).move(d_dir);
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
		//tempa.get(h.getY()).get(h.getX()).equals('H');

		for(int i = 0; i < d.size(); i++){
			tmp[d.get(i).getY()][d.get(i).getX()] = 'D';
			//tempa.get(d.get(i).getY()).get(d.get(i).getX()).equals('D');
		}

		for(int i = 0; i < s.size(); i++){
			tmp[s.get(i).getY()][s.get(i).getX()] = 'E';
			//tempa.get(s.get(i).getY()).get(s.get(i).getX()).equals('S');
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
