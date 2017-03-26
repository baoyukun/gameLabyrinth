package maze;

/**
 * Modeling departure box.
 * @see MBox
 */
public class DBox extends MBox{

	public DBox(Maze maze, int boxH, int boxW) {
		super(maze, boxH, boxW);
		this.setLabel("Depart");
	}

}
