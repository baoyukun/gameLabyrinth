package maze;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

/**
 * Abstract format of the labyrinth, representing it in a two-dimension Array.
 * @see GraphInterface
 */
public class Maze implements GraphInterface{
	
	private final int width ;
	private final int height ;
	private final int infinity ;
	private final MBox [][] boxes ;
	
	private MBox startBox; 
	private MBox endBox;
	
	public Maze(String fileAdd, int width, int height){
		this.width = width ;
		this.height = height ;
		infinity = width * height + 1 ;
		boxes = new MBox [height][width] ;
		startBox = null;
		endBox = null;
	    initFromTextFile(fileAdd);
	}
	
	/* 
	 * @see dijkstra.GraphInterface#getAllVertices()
	 */
	public final ArrayList<VertexInterface> getAllVertices(){
		
		ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>() ;
		
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				vertices.add(boxes[i][j]) ;
			}
		}
		
		return vertices ;	
	}
	
	/* 
	 * @see dijkstra.GraphInterface#getSuccessors(dijkstra.VertexInterface)
	 */
	public final ArrayList<VertexInterface> getSuccessors(VertexInterface vertex){
		
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>() ;
		
		MBox box = (MBox) vertex ;
		int boxH = box.getBoxH();
		int boxW = box.getBoxW();
		
		int[] dH = {-1, 0, 1, 0} ;
		int[] dW = {0, -1, 0, 1} ;
		
		for (int i=0; i<4; i++){
			int neighborH = boxH + dH[i] ;
			if (neighborH < height && neighborH >=0){
				int neighborW = boxW + dW[i] ;
				if (neighborW < width && neighborW >=0){
					if (! boxes[neighborH][neighborW].getLabel().equals("Wall")){
						successors.add(boxes[neighborH][neighborW]);
					}
				}
			}
		}
		
		return successors ;
	}
	
	/* 
	 * @see dijkstra.GraphInterface#getWeight(dijkstra.VertexInterface, dijkstra.VertexInterface)
	 */
	public final int getWeight(VertexInterface src, VertexInterface dst){
		
		if (src == dst) return 0;
		
		String srcLabel = src.getLabel();
		String dstLabel = dst.getLabel();
		if (srcLabel.equals("Wall") || dstLabel.equals("Wall") ||
			! getSuccessors(src).contains(dst)){
			return infinity;
		}
		
		return 1;
	}
	
	/**
	 * Initialize labyrinth from given file
	 * @param fileAdd File name given.
	 */
	public final void initFromTextFile(String fileAdd){
		
		ArrayList<String> mazeInFile = new ArrayList<String>();
		
		try {
			BufferedReader in
			   = new BufferedReader(new FileReader(fileAdd));
			
			String s = in.readLine();
			int i = -1;
			while (s!=null){
				mazeInFile.add(s);
				i++;
				if (s.length() != width){
					in.close();
					throw new MazeReadingException(fileAdd, i , "The column number is wrong!");
				}
				s = in.readLine();
			}
			
			in.close();
			
			if (mazeInFile.size() != height){
				throw new MazeReadingException(fileAdd, -1 , "The line number is wrong!");
			}
		} catch (IOException | MazeReadingException e) {
			e.printStackTrace();
		}
		
		newBoxes(mazeInFile);
	}
	
	private final void newBoxes(ArrayList<String> mazeInFile) {
		try{
			for (int i=0; i<height; i++){
				for (int j=0; j<width; j++){
					char ch = mazeInFile.get(i).charAt(j);
					switch (ch){
					case 'E':
						boxes[i][j] = new EBox(this, i, j);
						break;
					case 'W':
						boxes[i][j] = new WBox(this, i, j);
						break;
					case 'A':
						boxes[i][j] = new ABox(this, i, j);
						endBox = boxes[i][j];
						break;
					case 'D':
						boxes[i][j] = new DBox(this, i, j);
						startBox = boxes[i][j];
						break;
					default:
						throw new MazeWrongSymbolException(i, j, ch);
					}
				}
			}
		} catch (MazeWrongSymbolException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Save the abstract format into a file.
	 * @param fileAdd File name given.
	 * @return Result of saving. True if successful, false if failed.
	 */
	public final boolean saveToTextFile(String fileAdd) {
		try {
			PrintWriter pw = new PrintWriter(fileAdd);
			for (int i=0; i<height; i++){
				for (int j=0; j<width; j++){
					switch (boxes[i][j].getLabel()){
					case "Arrival":
						pw.print('A');
						break;
					case "Depart":
						pw.print('D');
						break;
					case "Empty":
						pw.print('E');
						break;
					case "Wall":
						pw.print('W');
						break;
					}
				}
				pw.println();
			}
			pw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * @return the infinity
	 */
	public final int getInfinity() {
		return infinity;
	}

	/**
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}

	/**
	 * @return the boxes
	 */
	public final MBox[][] getBoxes() {
		return boxes;
	}

	/**
	 * @return the startBox
	 */
	public final MBox getStartBox() {
		return startBox;
	}
	
	/**
	 * @return the endBox
	 */
	public final MBox getEndBox() {
		return endBox;
	}

	/**
	 * @param startBox the startBox to set
	 */
	public final void setStartBox(MBox startBox) {
		this.startBox = startBox;
	}
	
	/**
	 * @param endBox the endBox to set
	 */
	public final void setEndBox(MBox endBox) {
		this.endBox = endBox;
	}
}