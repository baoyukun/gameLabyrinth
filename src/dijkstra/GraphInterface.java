package dijkstra;

import java.util.ArrayList;

/**
 * Modeling the graph.
 */
public interface GraphInterface {
	
	/**
	 * Get all the vertexes of the maze.
	 * @return An ArrayList that contains all vertexes.
	 */
	public ArrayList<VertexInterface> getAllVertices();
	
	/**
	 * Get the shortest path from a given source to a vertex. 
	 * @param vertex The destination vertex.
	 * @return The shortest path containing the vertexes one by one in ArrayList format.
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
	
	/**
	 * Get the length from the given source point to a destination vertex. Potentially infinity
	 * if there is not a way.
	 * @param src The given source point.
	 * @param dst The destination point.
	 * @return The length of the way between them. If infinity, there is no way linking them
	 * for the moment.
	 */
	public int getWeight(VertexInterface src, VertexInterface dst);
}
