package maze;

/**
 * Modeling empty box.
 * @see MBox
 */
public class EBox extends MBox{

	public EBox(Maze maze, int boxH, int boxW) {
		super(maze, boxH, boxW);
		this.setLabel("Empty");
	}

}
