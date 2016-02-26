package maze.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import maze.logic.*;

public class game {

	private heroe h;
	private dragon d[];
	private sword s[];
	private space[][] maze;

	public static void main(String[] args) throws IOException {
		//maze m = new maze();
		//m.read("C:\\Users\\Jorge\\git\\lpoo1\\LPOO1\\Map");
		//m.print();
		game g = new game();
		g.initReader("C:\\Users\\Jorge\\git\\lpoo1\\LPOO1\\Map");
		g.print();

	}

	private void initReader(String path) throws IOException{
		maze = new space[10][10];

		int j = 0;
		for (String line : Files.readAllLines(Paths.get(path))){

			for(int i = 0; i < line.length();i++){
				char temp = line.charAt(i);
				switch (temp){
				case 'D':
					//d[d.length] = new dragon(j,i);
					temp = ' ';
					break;

				case 'H':
					h = new heroe(j,i);
					temp = ' ';
					break;

				case 'E':
					//s[s.length] = new sword(j,i);
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

	private void print(){
		char tmp[][] = new char[maze.length][maze[0].length];

		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				tmp[i][j] = maze[i][j].getAtri();
			}
		}
		
		tmp[h.getX()][h.getY()] = 'H';
		
		/*for(int i = 0; i < d.length; i++){
			tmp[d[i].getX()][d[i].getY()] = 'D';
		}
		
		for(int i = 0; i < s.length; i++){
			tmp[s[i].getX()][s[i].getY()] = 'S';
		}*/

		for(int i = 0; i < tmp.length; i++) {
			for(int j = 0; j < tmp[i].length; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
		//System.out.
	}
}
