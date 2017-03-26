package maze;

/**
 * Modeling wall box.
 * @see MBox
 */
public class WBox extends MBox{

	public WBox(Maze maze, int boxH, int boxW) {
		super(maze, boxH, boxW);
		this.setLabel("Wall");
	}

}