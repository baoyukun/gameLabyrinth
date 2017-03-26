package dijkstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import maze.Maze;

/**
 * By application of Dijkstra algorithm, find the shortest path from the given source
 * to all the vertexes in the graph.
 */
public class Dijkstra {
    /**
     * By application of Dijkstra algorithm, find the shortest path from the given source
     * to all the vertexes in the graph.
     * @param g The graph.
     * @param r The given source vertex.
     * @param a The set that contains all the vertexes already calculated.
     * @param pi The array representing the length of shortest path from the given source vertex
     *           for each vertex in the labyrinth.
     * @param previous The array representing the ancestor vertex calculated on the shortest path
     *                 for each vertex.
     * @return The updated previous.
     */
    private static final PreviousInterface dijkstra(GraphInterface    g,
    		                                        VertexInterface   r,
    		                                        ASetInterface     a,
    		                                        PiInterface       pi,
    		                                        PreviousInterface previous){
    	
    	Maze maze = (Maze) g;
    	int infinity = maze.getInfinity();
    	ArrayList<VertexInterface> vertices = maze.getAllVertices();
    	Iterator<VertexInterface> iterator = vertices.iterator();
    	while (iterator.hasNext()){
    		VertexInterface vertex = iterator.next();
    		pi.setValue(vertex, infinity);
    		previous.setValue(vertex, vertex);
    	}                                               //initialization
    	
    	a.add(r);
    	pi.setValue(r, 0);
    	VertexInterface pivot = r;
    	HashSet<VertexInterface> modifiedVertices = new HashSet<VertexInterface>();
    	
    	while (pivot != null){
    		
    		int pivotDistance = pi.getValue(pivot);
    		iterator = maze.getSuccessors(pivot).iterator();
    		while (iterator.hasNext()){
    			VertexInterface vertex = iterator.next();
    			if (! a.contains(vertex)){
    				if (pivotDistance + 1 < pi.getValue(vertex)){
    					pi.setValue(vertex, pivotDistance + 1);
    					previous.setValue(vertex, pivot);
    					modifiedVertices.add(vertex);
    				}
    			}	
    		}                                                 //update the result based on last step.
    		
    		int min = infinity;
    		VertexInterface argMin = null;
    		iterator = modifiedVertices.iterator();
    		while (iterator.hasNext()){
    			VertexInterface vertex = iterator.next();
    			int value = pi.getValue(vertex);
    			if (value < min){
    				min = value;
    				argMin = vertex;
    			}
    		}            //Find the vertex not in ASet whose path from the source point is the shortest.
    		
    		pivot = argMin;
    		if (pivot != null){
    			a.add(pivot);
    			modifiedVertices.remove(pivot);
    		}                                    //Reset the pivot.
    	}
    	
    	return previous;
    }
    
    /**
     * A static method susceptible to be called for applying Dijkstra algorithm.
     * @param g The graph considered.
     * @param r The source vertex.
     * @return An array calculated representing the ancestor vertex on the shortest path
     *         for each vertex.
     */
    public static final PreviousInterface dijkstra(GraphInterface  g,
    		                                       VertexInterface r){
    	return dijkstra(g, r, new ASet(), new Pi(), new Previous());
    }
}