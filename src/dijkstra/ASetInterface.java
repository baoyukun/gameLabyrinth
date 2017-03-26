package dijkstra;

/** Interface of the set A occurring in the Dijkstra algorithm
 *  as presented in the INF 101 SDA course at Telecom ParisTech
 *  The set A is a set of vertices characterized by the VertexInterface interface.
 */
public interface ASetInterface {
	
	/**
	 * Add another vertex considered into the set.
	 * @param vertex The vertex to be added.
	 */
	public void add(VertexInterface vertex);
	
	/**
	 * See if a vertex is contained in the set.
	 * @param vertex The vertex to be considered.
	 * @return True if contained, false if not.
	 */
	public boolean contains(VertexInterface vertex);
}
