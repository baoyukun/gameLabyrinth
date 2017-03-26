package maze;
import dijkstra.VertexInterface;

/**
 * Modeling a vertex in the graph. In maze, it is a box.
 * @see VertexInterface
 */
public abstract class MBox implements VertexInterface{
	
	private final int boxH ;
	private final int boxW ;
	
	private String label ;
	
	public MBox(Maze maze, int boxH, int boxW){
		this.boxH = boxH ;
		this.boxW = boxW ;
	}
    
	/**
	 * @return the boxH
	 */
	public final int getBoxH() {
		return boxH;
	}

	/**
	 * @return the boxW
	 */
	public final int getBoxW() {
		return boxW;
	}

	/**
	 * @param label the label to set
	 */
	public final void setLabel(String label) {
		this.label = label;
	}

	/* 
	 * @see dijkstra.VertexInterface#getLabel()
	 */
	public final String getLabel(){
		return label;
	}
}