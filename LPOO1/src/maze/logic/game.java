package maze.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.*;
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
		//maze m = new maze();
		//m.read("C:\\Users\\Jorge\\git\\lpoo1\\LPOO1\\Map");
		//m.print();

	}

	public game(String path, int gamemode) throws IOException{
		maze = new space[10][10];
		d = new ArrayList<dragon>();
		s = new ArrayList<sword>();
		//d = new dragon[MAX_DRAGON_NUM];
		//s = new sword[MAX_SWORD_NUM];
		state = gameState.RUNNING;
		this.gamemode = gamemode;
		
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
	}

	public void update(char kbd_input){
		/*if (heroe_dir == 'W' || heroe_dir == 'A' ||heroe_dir == 'S' || heroe_dir == 'D'){
			h.move(heroe_dir);
		}*/
		switch (kbd_input){
		case 'W':
			if (maze[h.getY()-1][h.getX()].getAllowMove())
				h.move('N');
			else
				return;
			break;

		case 'S':
			if (maze[h.getY()+1][h.getX()].getAllowMove())
				h.move('S');
			else
				return;
			break;

		case 'A':
			if (maze[h.getY()][h.getX()-1].getAllowMove())
				h.move('O');
			else
				return;
			break;

		case 'D':
			if (maze[h.getY()][h.getX()+1].getAllowMove())
				h.move('E');
			else
				return;
			break;

		default:
			return;
		}

		if (h.getSword() == null){
			for(int i = 0; i < s.size(); i++){
				if (h.getX() == s.get(i).getX() && h.getY() == s.get(i).getY()){
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
						maze[d.get(i).getY()-1][d.get(i).getX()],
						maze[d.get(i).getY()+1][d.get(i).getX()],
						maze[d.get(i).getY()][d.get(i).getX()-1],
						maze[d.get(i).getY()][d.get(i).getX()+1]);

				if((d.get(i).getX()) == h.getX() && d.get(i).getY() == h.getY() ||
						(d.get(i).getX())-1 == h.getX() && d.get(i).getY() == h.getY() ||
						(d.get(i).getX())+1 == h.getX() && d.get(i).getY() == h.getY() ||
						(d.get(i).getX()) == h.getX() && d.get(i).getY()-1 == h.getY() ||
						(d.get(i).getX()) == h.getX() && d.get(i).getY()+1 == h.getY()){
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
			if(maze[h.getY()][h.getX()].getType() == spaceType.EXIT){
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
			tmp[d.get(i).getY()][d.get(i).getX()] = d.get(i).getAtri();
			//tempa.get(d.get(i).getY()).get(d.get(i).getX()).equals('D');
		}

		for(int i = 0; i < s.size(); i++){
			if(tmp[s.get(i).getY()][s.get(i).getX()] == 'D')
				tmp[s.get(i).getY()][s.get(i).getX()] = 'F';
			else 
				tmp[s.get(i).getY()][s.get(i).getX()] = 'E';
			//tempa.get(s.get(i).getY()).get(s.get(i).getX()).equals('S');
		}


		tmp[h.getY()][h.getX()] = h.getAtri();
		//tempa.get(h.getY()).get(h.getX()).equals('H');

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

	public void DequalsS(){
		for(int i = 0; i < d.size(); i++){
			for(int j = 0; j < s.size();j++){
				if(d.get(i).getX() == s.get(j).getX() && d.get(i).getY() == s.get(j).getY()){
					maze[d.get(i).getX()][d.get(i).getY()].setAtri('F');
				}
				else {
					maze[d.get(i).getX()][d.get(i).getY()].setAtri('D');	
					maze[s.get(i).getX()][s.get(i).getY()].setAtri('S');
				}
			}
		}
	}

}
