package maze.cli;

import java.io.IOException;

public class game {

	public static void main(String[] args) throws IOException {
		maze m = new maze();
		m.read("C:\\Users\\Jorge\\workspace\\LPOO1\\Map");
		m.print();
	}
}
