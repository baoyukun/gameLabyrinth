package dijkstra;

import java.util.ArrayList;

/** Interface of the "père" function occurring in the Dijkstra algorithm
 *  as presented in the INF 101 SDA course at Télécom ParisTech
 */

public interface PreviousInterface {
	
	/**
	 * Set the ancestor on the shortest path of a given vertex.
	 * @param vertex The given vertex.
	 * @param value The ancestor.
	 */
	public void setValue(VertexInterface vertex, VertexInterface value);
	
	/**
	 * Get the ancestor on the shortest path of a given vertex.
	 * @param vertex The given vertex.
	 * @return The ancestor.
	 */
	public VertexInterface getValue(VertexInterface vertex);
	
	/**
	 * Get the linked vertexes in order on the shortest path to a given vertex.
	 * @param vertex The given vertex.
	 * @return The linked vertexes in order of ArrayList format.
	 */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
