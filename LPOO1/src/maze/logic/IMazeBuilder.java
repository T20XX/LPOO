package maze.logic;

/**
 * Interface given to work with the JUnit test file given
 */
public interface IMazeBuilder {
	/**
	 * Interface given to work with the JUnit test file given
	 * @param size Dimension of the squared maze (even numbers will turn into the next existing odd number since is required for the algorithm works properly)
	 * @return 2D-Array of chars containing the symbols of the maze
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
