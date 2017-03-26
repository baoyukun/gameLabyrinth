package maze;

/**
 * Modeling arrival box.
 * @see MBox
 */
public class ABox extends MBox{

	public ABox(Maze maze, int boxH, int boxW) {
		super(maze, boxH, boxW);
		this.setLabel("Arrival");
	}
	
}
