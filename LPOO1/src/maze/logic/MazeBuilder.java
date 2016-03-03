package maze.logic;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{
	public char[][] buildMaze(int size) throws IllegalArgumentException{

		/**
		 * transform size in odd number by adding 1
		 */
		if (size%2 == 0)
			size++;

		char[][] maze = new char[size][size];
		char[][] visited = new char[size/2][size/2];
		Point guide = new Point();
		Stack<Point> history = new Stack<Point>();

		/**
		 * Initialize base maze "wall blocked"
		 */
		Arrays.fill(maze[0], 'X');
		for (int y = 1; y < size; y++){
			for (int x = 0; x < size; x++){
				if (x%2 == 0)
					maze[y][x] = 'X';
				else
					maze[y][x] = ' ';
			}
			y++;
			Arrays.fill(maze[y], 'X');
		}

		/**
		 * Initialize base visited 2Darray
		 */
		for (int y = 0; y < visited.length; y++){
			Arrays.fill(visited[y], '.');
		}

		/**
		 * Places guide starting position randomly near borders
		 * Places exit in border
		 */
		Random r = new Random();
		int tmp;
		tmp = r.nextInt(4);
		switch (tmp){
		case 0:
			tmp = r.nextInt(size/2);
			guide.setLocation(0, tmp);
			maze[tmp*2+1][0] = 'S';
			break;

		case 1:
			tmp = r.nextInt(size/2);
			guide.setLocation(tmp, 0);
			maze[0][tmp*2+1] = 'S';
			break;

		case 2:
			tmp = r.nextInt(size/2);
			guide.setLocation(size/2 - 1, tmp);
			maze[tmp*2+1][size - 1] = 'S';
			break;

		case 3:
			tmp = r.nextInt(size/2);
			guide.setLocation(tmp,size/2 - 1);
			maze[size - 1][tmp*2+1] = 'S';
			break;
		}

		visited[(int)guide.getY()][(int)guide.getX()] = 'X';
		history.push(guide);



		String possDir = "";
		while (!history.empty()){
			possDir = "";
			try{
				if (visited[guide.y-1][guide.x] == '.')
					possDir+= 'N';
			}catch (IndexOutOfBoundsException e){}
			try{
				if (visited[guide.y+1][guide.x] == '.')
					possDir+= 'S';
			}catch (IndexOutOfBoundsException e){}
			try{
				if (visited[guide.y][guide.x-1] == '.')
					possDir+= 'O';
			}catch (IndexOutOfBoundsException e){}
			try{
				if (visited[guide.y][guide.x+1] == '.')
					possDir+= 'E';
			}catch (IndexOutOfBoundsException e){}
			
			
			if (!possDir.isEmpty()){
				switch(possDir.charAt(r.nextInt(possDir.length()))){
				case 'N':
					guide.y--;
					break;
				case 'S':
					guide.y++;
					break;
				case 'O':
					guide.x--;
					break;
				case 'E':
					guide.x++;
					break;
				default:
					break;
				}
				visited[guide.y][guide.x] = 'X';
				history.push(guide);
			}else{
				guide = history.pop();
			}
		}


		for (int y = 0; y < size; y++){
			for (int x = 0; x < size; x++){
				System.out.print(maze[y][x]);
			}
			System.out.println();
		}
		for (int y = 0; y < size/2; y++){
			for (int x = 0; x < size/2; x++){
				System.out.print(visited[y][x]);
			}
			System.out.println();
		}

		return  maze;
	}


}
