package maze.logic;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{
	public char[][] buildMaze(int size) throws IllegalArgumentException{
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
		 * Places guide starting position randomly near borders
		 */
		Random r = new Random();

		switch (r.nextInt(size/2)){
		case 0:
			guide.setLocation(0, r.nextInt(size/2));
			break;

		case 1:
			guide.setLocation(r.nextInt(size/2), 0);
			break;

		case 2:
			guide.setLocation(size/2 - 1, r.nextInt(size/2));
			break;

		case 3:
			guide.setLocation(r.nextInt(size/2),size/2 - 1);
			break;
		}

		visited[(int)guide.getY()][(int)guide.getX()] = 'X';
		history.push(guide);

		while (!history.empty()){


			history.pop();
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
