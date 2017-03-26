package main;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import dijkstra.*;
import maze.*;
import view.*;

/**
 * Characterizing an individual labyrinth.
 * @author BAO Yukun
 * @version Final
 */
public class GameLabyrinth extends GameController{
	
	
	/**
	 * Whether debug the application.
	 */
	private final boolean debug = false;
	
	/**
	 * Keep the reference of its generator.
	 * @see MainApp
	 */
	private final MainApp mainApp;
	/**
	 * The abstract format of the labyrinth.
	 * @see maze.Maze
	 */
	private final Maze maze;
	/**
	 * The model of the labyrinth.
	 * @see view.GameModel
	 */
	private final GameModel gameModel;
	/**
	 * Keep the file address in which this labyrinth is stored.
	 */
	private final String fileAdd;

	/**
	 * Facilitate the transformation between color and box label.
	 * @see view.ColorLabelTransform
	 */
	private final ColorLabelTransform convert;
	/**
	 * Default empty color.
	 */
	private final byte emptyColor = 0;
	/**
	 * Default wall color.
	 */
	private final byte wallColor = 1;
	/**
	 * Default start color.
	 */
	private final byte startColor = 2;
	/**
	 * Default end color.
	 */
	private final byte endColor = 10;
	/**
	 * Default path color.
	 */
	private final byte pathColor = 6;
	
	/**
	 * Record which key is typed.
	 */
	private KeyEvent key = null;
	/**
	 * Record the position when beginning to drag the mouse.
	 */
	private MouseEvent mouseDragPress = null;
	/**
	 * Record the position when ending dragging the mouse.
	 */
	private MouseEvent mouseDragRelease = null;
	/**
	 * Record continuously the mouse position.
	 */
	private MouseEvent mousePosition = null;
	
	
	/**
	 * The shortest path from start to end if there is.
	 */
	private ArrayList<VertexInterface> path;
	
	/**
	 * The constructor.
	 * @param mainApp Keep the reference of its generator.
	 * @param name The name of application.
	 * @param fileAdd The file address in which labyrinth is saved.
	 * @param fileTempAdd The cache file address in which labyrinth is saved.
	 * @param gameWidth The labyrinth width in blocks.
	 * @param gameHeight The labyrinth height in blocks.
	 * @param blockWidth The width in pixels of a block.
	 * @param blockHeight The height in pixels of a block.
	 */
	public GameLabyrinth(MainApp mainApp, String name,
			             String fileAdd, String fileTempAdd,
			             int gameWidth, int gameHeight,
			             int blockWidth, int blockHeight)
	{
		super(name, gameWidth, gameHeight, blockWidth, blockHeight);
		this.mainApp = mainApp;
		this.fileAdd = fileAdd;
		
		gameFrame = new GameFrame(this, name, fileAdd, gameWidth, gameHeight, blockWidth, blockHeight) ;
		maze = new Maze(fileTempAdd, gameWidth, gameHeight) ;
		gameModel = new GameModel(gameWidth, gameHeight, blockWidth, blockHeight) ;
		
		convert = new ColorLabelTransform();
		path = null;
		
		initGameView(maze.getBoxes());
	}

	/**
	 * Initialize the labyrinth view at the first start.
	 * @param boxes The abstract format of the labyrinth.
	 */
	private final void initGameView(MBox [][] boxes){
		for (int i=0; i<gameHeight; i++){
			for (int j=0; j<gameWidth; j++){
				setBoxColor(boxes[i][j], convert.toColor(boxes[i][j].getLabel())
		                                        .byteValue());
			}
		}
		drawNewPath();
	}
	
	/**
	 * Change the attribution of a certain box.
	 * @param objBox The reference of the box to be changed.
	 * @param objcolor The desiring color for the box.
	 */
	private final synchronized void setBoxColor(MBox objBox, byte objcolor){
    	if (objBox != null){
    		synchronized (gameModel){
    			gameModel.set(objBox.getBoxW(), objBox.getBoxH(), objcolor);
    			notify(gameModel) ;
			}
    	}
    }
	
	/**
	 * Display the shortest path on the screen.
	 */
	private final void drawNewPath()
    {
    	MBox startBox = maze.getStartBox();
    	MBox endBox = maze.getEndBox();
    	
    	if (startBox != null && endBox != null)
    	{
    		path = Dijkstra.dijkstra(maze, startBox)
				           .getShortestPathTo(endBox);
		  
    		Iterator<VertexInterface> iterator = path.iterator();
    		while (iterator.hasNext()){
    			MBox vertex = (MBox) iterator.next();
    			setBoxColor(vertex, pathColor);
    		}
    	}
    	
    	setBoxColor(startBox, startColor);
    	setBoxColor(endBox, endColor);
    }
	
    /**
     * Clear the displaying of old path.
     */
    private final void clearOldPath(){
    	if (path != null){
    		Iterator<VertexInterface> iterator = path.iterator();
        	while (iterator.hasNext()){
        		MBox vertex = (MBox) iterator.next();
        		setBoxColor(vertex, emptyColor);
        	}
    		path = null;
    	}
    }
    
    /**
     * Verify the legacy of modifying the attribution of a box.
     * @param boxH The height of the box.
     * @param boxW The width of the box.
     * @param color The desiring color for this box.
     * @return true if legal, false if not.
     */
    private final boolean modifyBoxColor(int boxH, int boxW, byte color){
		
		MBox box = maze.getBoxes() [boxH][boxW];
		String label = box.getLabel();
		String newLabel = convert.toLabel((int) color);
		
		if (label.equals(newLabel)) return false;
		if (label.equals("Wall") && newLabel.compareTo("Empty")!=0) return false;
		
		clearOldPath();
		
		if (label.equals("Depart")) maze.setStartBox(null);
		if (label.equals("Arrival")) maze.setEndBox(null);
		if (newLabel.equals("Depart")){
			MBox box0 = maze.getStartBox();
			if (box0 != null) box0.setLabel("Empty");
			setBoxColor(box0, emptyColor);
			maze.setStartBox(box);
		}
		if (newLabel.equals("Arrival")){
			MBox box0 = maze.getEndBox();
			if (box0 != null) box0.setLabel("Empty");
			setBoxColor(box0, emptyColor);
			maze.setEndBox(box);
		}
		
		box.setLabel(newLabel);
		setBoxColor(box, color);
		return true;
	}
    
    /**
     * Check if the mouse focus inside the labyrinth.
     * @param e MouseEvent
     * @return true if inside the labyrinth, false if not.
     */
    private final boolean isFocusInsideLabyrinth(MouseEvent e)
    {
    	if (getGameX(e)<gameWidth && getGameX(e)>=0 &&
    		getGameY(e)<gameHeight && getGameY(e)>=0){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Method for drawing a block of boxes by dragging the mouse.
     * @param start MouseEvent which records the start point of dragging.
     * @param end MouseEvent which records the end point of dragging.
     * @param color Which color to be set for this block.
     */
    private final void fillRectangle(MouseEvent start, MouseEvent end, byte color)
    {
    	boolean result = false;
    	for (int i = getGameY(start); i <= getGameY(end); i++)
    		for (int j = getGameX(start); j <= getGameX(end); j++){
    			modifyBoxColor(i, j, color);
    			result = true;
    		}
    	if (result){
    		drawNewPath();
    		gameFrame.setStatusBar("Successfully set the block!");
    	}
    }
    
	/**
	 * Initialize the whole labyrinth by setting walls randomly.
	 */
	public final void initRandomly(){
		for (int i=0; i<gameHeight; i++){
			for (int j=0; j<gameWidth; j++){
				if (Math.random() >= 0.35)
					modifyBoxColor(i, j, emptyColor);
				else
					modifyBoxColor(i, j, wallColor);
			}
		}
	}
	
	/**
	 * Resize the labyrinth.
	 * @param width The new width of the labyrinth.
	 * @param height The new height of the labyrinth.
	 */
	public final void resizeLabyrinth(int width, int height) {
		String tempFileName = new SimpleDateFormat("MM-dd-HH-mm-ss").format(new Date());
		String fileTempAdd = "data/" + "temp-" + tempFileName + ".txt";
		saveEmptyLabyrinthToFile(fileTempAdd, width, height);
		gameFrame.dispose();
		mainApp.newLabyrinth(fileAdd, fileTempAdd, width, height);
	}
	
	/**
	 * Write abstract format of an empty labyrinth into file.
	 * @param fileAdd The file to be found.
	 * @param width The width of the labyrinth.
	 * @param height The height of the labyrinth.
	 */
	private final void saveEmptyLabyrinthToFile(String fileAdd, int width, int height) {
		try {
			PrintWriter pw = new PrintWriter(fileAdd);
			for (int i=0; i<height; i++){
				for (int j=0; j<width; j++){
					pw.print('E');
				}
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open an existing labyrinth from a file.
	 * @param fileAdd The file address where the labyrinth is stored.
	 * @return The open result: 1 if the format is not a labyrinth; 2 if the labyrinth is too
	 *         big to display for current screen.
	 */
	public final byte openFile(String fileAdd) {
		ArrayList<Integer> coordinate = isFileFormatLegal(fileAdd);
		if (coordinate == null) return 1;
		
		int width = coordinate.get(0);
		int height = coordinate.get(1);
		if (mainApp.getWindowWidth()/width <=1 || mainApp.getWindowHeight()/height <=1 ){
			return 2;
		}else{
			gameFrame.dispose();
			mainApp.newLabyrinth(fileAdd, fileAdd, width, height);
			return 0;
		}
	}
	
	/**
	 * Verify if the file is a labyrinth format
	 * @param fileAdd The file to be verified.
	 * @return The coordinate in ArrayList format. If the file cannot be recognized as a labyrinth,
	 *         return null.
	 */
	private final ArrayList<Integer> isFileFormatLegal(String fileAdd){
		
		ArrayList<Integer> coordinate = new ArrayList<Integer>();
		ArrayList<String> mazeInFile = new ArrayList<String>();
		
		int labyrinthWidth;
		try {
			BufferedReader in
			   = new BufferedReader(new FileReader(fileAdd));
			
			String s = in.readLine();
			labyrinthWidth = s.length();
			while (s!=null){
				mazeInFile.add(s);
				if (s.length() != labyrinthWidth){
					in.close();
					return null;
				}
				s = in.readLine();
			}
			
			in.close();
		} catch (IOException e) { return null; }       // Verify the size is legal.
		
		int labyrinthHeight = mazeInFile.size();
		for (int i=0; i<labyrinthHeight; i++){
			for (int j=0; j<labyrinthWidth; j++){
				char ch = mazeInFile.get(i).charAt(j);
				if (ch!='E' && ch!='W' && ch!='A' && ch!='D'){
					return null;
				}
			}
		}                                              // Verify the symbol is legal.
		
		if (labyrinthWidth>0 && labyrinthHeight>0){
			coordinate.add(labyrinthWidth);
			coordinate.add(labyrinthHeight);
			return coordinate;
		}else
			return null;
	}
	
	/**
	 * Save labyrinth to file.
	 * @return True if successful, false if not.
	 */
	public final boolean saveFile() {
		if (maze.saveToTextFile(fileAdd)) return true;
		return false;
	}
	
	/**
	 * Create a totally different labyrinth and save it in file.
	 * @param fileAdd The file to be created for saving the labyrinth.
	 * @param width Width of the new labyrinth.
	 * @param height Height of the new labyrinth.
	 */
	public final void newFile(String fileAdd, int width, int height) {
		
		saveEmptyLabyrinthToFile(fileAdd, width, height);
		gameFrame.dispose();
		mainApp.newLabyrinth(fileAdd, fileAdd, width, height);
	}
    
    /** Invoked when the mouse button has been clicked (pressed and released) on a component. */
	@Override
	public final synchronized void mouseClicked(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse clicked") ;
		
		key = null ;
		mouseDragPress = null;
		mouseDragRelease = null;
		
		if (isFocusInsideLabyrinth(e)){
			int pointW = getGameX(e);
			int pointH = getGameY(e);
		
			boolean legalModify;
			if (maze.getBoxes()[pointH][pointW].getLabel().equals("Wall")){
				legalModify = modifyBoxColor(pointH, pointW, emptyColor);
			}else{
				legalModify = modifyBoxColor(pointH, pointW, wallColor);
			}
			
			if (legalModify) {
				drawNewPath();
				gameFrame.setStatusBar("Successfully changed the box!");
			}
			else gameFrame.setStatusBar("Illegal modification!");
			
		}else gameFrame.setStatusBar("Out of the labyrinth range!");
	}
	
	/** Invoked when a mouse button has been pressed on a component. */
	@Override
	public final synchronized void mousePressed(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse pressed") ;
		mouseDragPress = e;
	}
	
	/** Invoked when a mouse button has been released on a component. */
	@Override
	public final synchronized void mouseReleased(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse released") ;
	
		KeyEvent keyTemp = key;
		if (mouseDragPress != null && mouseDragRelease != null
			&& isFocusInsideLabyrinth(mouseDragPress) && isFocusInsideLabyrinth(mouseDragRelease))
		{
			if (keyTemp == null)
				fillRectangle(mouseDragPress, mouseDragRelease, wallColor);
			else if (keyTemp.getKeyCode() == 16)
				fillRectangle(mouseDragPress, mouseDragRelease, emptyColor);
		}
		
		key = null;
		mouseDragPress = null;
		mouseDragRelease = null;
	}
	
	/** Invoked when the mouse enters a component. */
	@Override
	public final synchronized void mouseEntered(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse entered") ;
	}
	
	/** Invoked when the mouse exits a component. */
	@Override
	public final synchronized void mouseExited(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse exited") ;
	}
	
	/** Invoked when a mouse button is pressed on a component and then dragged. 
	 *  MOUSE_DRAGGED events will continue to be delivered to the component 
	 *  where the drag originated until the mouse button is 
	 *  released regardless of whether the mouse position is within the 
	 *  bounds of the component. 
	 */
	@Override
	public final synchronized void mouseDragged(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse dragged") ;
		mouseDragRelease = e;
	}

	/** Invoked when the mouse cursor has been moved onto a 
	 *  component but no buttons have been pushed.
	 */
	@Override
	public final synchronized void mouseMoved(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse moved") ;	
		mousePosition = e;
		if (isFocusInsideLabyrinth(e)) gameFrame.showCoordinate(getGameY(e), getGameX(e));
	}

	/** Invoked when a key has been typed. 
	 *  See the class description for KeyEvent for a definition of a key typed event.
	 */
	@Override
	public final synchronized void keyTyped(KeyEvent e)
	{
		if (debug)
			System.err.println("Key typed: " + e.getKeyCode()) ;
		
		key = null;
		
		MouseEvent temp = mousePosition;
		if (isFocusInsideLabyrinth(temp)){
			char key = e.getKeyChar() ;
			int pointH = getGameY(temp);
			int pointW = getGameX(temp);
			
			boolean legalModify = false;
			
			if (key == 'a' || key == 'A'){
				legalModify = modifyBoxColor(pointH, pointW, endColor);
				if (! legalModify) gameFrame.setStatusBar("Illegal modification!");
			}else if (key == 'd' || key == 'D'){
				legalModify = modifyBoxColor(pointH, pointW, startColor);
				if (! legalModify) gameFrame.setStatusBar("Illegal modification!");
			}
			
			if (legalModify){
				drawNewPath();
				gameFrame.setStatusBar("Successfully set the extremity box!");
			}	
		}
	}

	/** Invoked when a key has been pressed. 
	 *  See the class description for KeyEvent for a definition of a key pressed event.
	 */
	@Override
	public final synchronized void keyPressed(KeyEvent e)
	{
		if (debug)
			System.err.println("Key pressed: " + e.getKeyCode()) ;	
		key = e;
	}

	/** Invoked when a key has been released. 
	 *  See the class description for KeyEvent for a definition of a key released event. 
	 */
	@Override
	public final synchronized void keyReleased(KeyEvent e)
	{
		if (debug)
			System.err.println("Key released: " + e.getKeyCode()) ;
		key = null;
	}
}