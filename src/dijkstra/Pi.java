package dijkstra;

import java.util.Hashtable;

/**
 * Modeling the length of shortest path for each vertex.
 * @see PiInterface
 */
public class Pi implements PiInterface{
	
	private Hashtable<VertexInterface, Integer> piTable ;
	
	/**
	 * The constructor.
	 */
	public Pi(){
		piTable = new Hashtable<VertexInterface, Integer>() ;
	}
	
    /* 
     * @see dijkstra.PiInterface#setValue(dijkstra.VertexInterface, int)
     */
    public final void setValue(VertexInterface vertex, int value){
    	piTable.put(vertex, value) ;
    }
	
	/* 
	 * @see dijkstra.PiInterface#getValue(dijkstra.VertexInterface)
	 */
	public final int getValue(VertexInterface vertex){
		return piTable.get(vertex) ;
	}
}
