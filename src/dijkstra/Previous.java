package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Modeling the ancestor of each vertex.
 * @see PreviousInterface
 */
public class Previous implements PreviousInterface {
	
	private Hashtable<VertexInterface, VertexInterface> preTable ;
	
	/**
	 * The constructor.
	 */
	public Previous(){
		preTable = new Hashtable<VertexInterface, VertexInterface>() ;
	}
	
	/* 
	 * @see dijkstra.PreviousInterface#setValue(dijkstra.VertexInterface, dijkstra.VertexInterface)
	 */
	public final void setValue(VertexInterface vertex, VertexInterface value){
		preTable.put(vertex, value) ;
	}
	
	/* 
	 * @see dijkstra.PreviousInterface#getValue(dijkstra.VertexInterface)
	 */
	public final VertexInterface getValue(VertexInterface vertex){
		return preTable.get(vertex) ;
	}
	
	/* 
	 * @see dijkstra.PreviousInterface#getShortestPathTo(dijkstra.VertexInterface)
	 */
	public final ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex){
		
		ArrayList<VertexInterface> path = new ArrayList<VertexInterface>() ;
		path.add(vertex) ;
		
		while (preTable.get(vertex) != vertex){
			vertex = preTable.get(vertex) ;
			path.add(0, vertex);
		}
		
		return path ;
	}
}