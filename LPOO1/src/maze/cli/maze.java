package maze.cli;

import maze.logic.space;//.* para importar tudo
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class maze {
	private space[][] walls;
	
	public int read(String path) throws IOException{
		walls = new space[10][10];
		
		int j = 0;
		for (String line : Files.readAllLines(Paths.get(path))){
			
			for(int i = 0; i < line.length();i++){
				char temp = line.charAt(i);
				/*if(temp == 'D')
					temp = 'D';
				else if (temp == 'H')
					temp = 'H';
				else if (temp == 'E')
					temp = 'E';*/
				walls[j][i] = new space(temp);
				}
			j++;
		}
		
		return 0;
	}
	
	public int print(){
		for(int i = 0; i < walls.length; i++) {
			for(int j = 0; j < walls[i].length; j++) {
				System.out.print(walls[i][j].getAtri());
			}
		System.out.println();
		}
		return 0;
	}
}
