package dijkstra;

import java.util.HashSet;

/**
 * The vertex set in Dijkstra algorithm model.
 * @see ASetInterface
 */
public class ASet implements ASetInterface{
	
	private HashSet<VertexInterface> set ;
	
	/**
	 * The constructor.
	 */
	public ASet(){
		set = new HashSet<VertexInterface>();
	}
	
	/* 
	 * Add another vertex considered into the set.
	 * @see dijkstra.ASetInterface#add(dijkstra.VertexInterface)
	 */
	public final void add(VertexInterface vertex){
		set.add(vertex);
	}
	
	/* 
	 * See if a vertex is contained in the set.
	 * @see dijkstra.ASetInterface#contains(dijkstra.VertexInterface)
	 */
	public final boolean contains(VertexInterface vertex){
		return set.contains(vertex);
	}

}
