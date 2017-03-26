package dijkstra;

/** Interface of the pi function occuring in the Dijkstra algorithm
 *  as presented in the INF 101 SDA course at Telecom ParisTech
 */

public interface PiInterface {
	
	/**
	 * Set the length of shortest path of a given vertex.
	 * @param vertex The vertex to be set.
	 * @param value The shortest length.
	 */
	public void setValue(VertexInterface vertex, int value);
	
	/**
	 * Get the length of shortest path of a given vertex.
	 * @param vertex The vertex involved.
	 * @return The shortest length.
	 */
	public int getValue(VertexInterface vertex);
}
