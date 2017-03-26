package main;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Managing different labyrinths on the top-level.
 * @author BAO Yukun
 * @version Final
 */
public class MainApp {
	
	/**
	 * The beginning of the application, create an instance of MainApp.
	 * @param args default input parameter
	 */
	public static void main(String[] args) {
		new MainApp();
	}
	
	/**
	 * The appropriate width for displaying the labyrinth.
	 */
	private final int windowWidth;
	/**
	 * The appropriate height for displaying the labyrinth.
	 */
	private final int windowHeight;
	/**
	 * The labyrinth width in blocks.
	 */
	private int gameWidth;
	/**
	 * The labyrinth height in blocks.
	 */
	private int gameHeight;
	
	/**
	 * The file address in which labyrinth is saved.
	 */
	private String fileAdd;
	/**
	 * The cache file address in which labyrinth is saved.
	 */
	private String fileTempAdd;
	
	/**
	 * The constructor.
	 */
	public MainApp(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		windowWidth = (int) (screenWidth * 0.70);
		windowHeight = (int) (screenHeight * 0.80);
		
		fileAdd = "data/defaultLabyrinth.txt";
		fileTempAdd = fileAdd;
		gameWidth = 60;
		gameHeight = 40;
		
		new GameLabyrinth( this, "Labyrinth",
				           fileAdd, fileTempAdd,
				           gameWidth, gameHeight,
				           windowWidth/gameWidth, windowHeight/gameHeight) ;
	}
	
	
	/**
	 * Display a new labyrinth.
	 * @param fileAdd  The file address in which new labyrinth is saved.
	 * @param fileTempAdd  The cache file address in which new labyrinth is saved.
	 * @param gameWidth The width in blocks of the new labyrinth.
	 * @param gameHeight The height in blocks of the new labyrinth.
	 */
	public final void newLabyrinth(String fileAdd, String fileTempAdd,
			                       int gameWidth, int gameHeight) {
		this.fileAdd = fileAdd;
		this.fileTempAdd = fileTempAdd;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		
		new GameLabyrinth( this, "Labyrinth (byukun-dmayau)",
		                   fileAdd, fileTempAdd,
		                   gameWidth, gameHeight,
		                   windowWidth/gameWidth, windowHeight/gameHeight) ;
	}

	/**
	 * @return the windowWidth
	 */
	public final int getWindowWidth() {
		return windowWidth;
	}

	/**
	 * @return the windowHeight
	 */
	public final int getWindowHeight() {
		return windowHeight;
	}
}