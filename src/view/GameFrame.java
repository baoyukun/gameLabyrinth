package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.* ;

import main.GameLabyrinth;

/** This is the JFrame class for the main game window. */

public final class GameFrame extends JFrame
{
	static final long serialVersionUID = 1L ;
	
	/** The game panel, i.e. the content of the window. */
	private final GamePanel gamePanel ;
	private final WindowPanel windowPanel ;
	private final GameLabyrinth gameController;
	
	private final int ScreenWidth;
	private final int ScreenHeight;
	
	/** The constructor :
	 * @param gameController The game controller.
	 * @param name The game name to be displayed at the top of the window.
	 * @param fileAdd The file in which the currently displaying labyrinth is stored.
	 * @param gameWidth The width of the game in blocks.
	 * @param gameHeight The height of the game in blocks.
	 * @param blockWidth The width in pixel of a screen block.
	 * @param blockHeight The height in pixel of a screen block.
	 */
	public GameFrame(GameLabyrinth gameController, String name, String fileAdd,
			         int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name) ;
		
		// Widgets
		setIconImage(new ImageIcon("icon/labyrinth.png").getImage());
		setJMenuBar(new GameMenuBar(this)) ;
		
		this.gameController = gameController;
		gamePanel = new GamePanel(gameController,gameWidth,gameHeight,blockWidth,blockHeight);
		windowPanel = new WindowPanel(this, fileAdd, gamePanel, gameController);
		setContentPane(windowPanel) ;
		
		// Applique
		addKeyListener(gameController) ;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
		addWindowListener(new WindowAdapter() {
			public final void windowClosing(WindowEvent e) { quit(); }
		});
		
		pack();
		setResizable(false) ;
		setLocationRelativeTo(null);
		setVisible(true) ;
		
		ScreenWidth = gameWidth*blockWidth;
		ScreenHeight = gameHeight*blockHeight;
	}
	
	public final void set(GameModel gameModel)
	{
		gamePanel.set(gameModel);
		repaint() ;
	}
	
	public final void quit()
	{
		int response = JOptionPane.showConfirmDialog(windowPanel,
				                                     "Are you sure to quit?",
				                                     "Quit this game",
				                                     JOptionPane.YES_NO_OPTION);
		switch (response) {
		   case JOptionPane.YES_OPTION:
			   System.exit(0) ;
		   case JOptionPane.NO_OPTION:
			   break ;
		   }
	}
	
	public final void instructionNotify()
	{
		JOptionPane
		.showMessageDialog(windowPanel,
				           "*****Go through the labyrinth via the shortest path*****"
				         + "\n"
				         + "\n"
				         + "Set departure point:  Type \"D\" or \"d\" on the box pointed by the mouse.\n"
				         + "Set arrival point:    Type \"A\" or \"a\" on the box pointed by the mouse.\n"
				         + "\n"
				         + "Change a box into \"wall\":  Just click on a it!\n"
				         + "Change a box into \"empty\": Just click on a it!\n"
				         + "\n"
				         + "Set a \"wall\" block:    Drag the mouse from up-left corner to down-right corner.\n"
				         + "Set an \"empty\" block:  Shift-drag the mouse from up-left corner to down-right corner.\n"
				         + "\n"
				         + "Hope you an enjoyful journey O(∩_∩)O\n"
				         + "Any questions or suggestions, please contact the author:\n"
				         + "BAO Yukun      yukun.bao@telecom-paristech.fr\n",
				         "How to play the game",
				         JOptionPane.INFORMATION_MESSAGE);
	}
	
	public final void showCoordinate(int x, int y){
		gamePanel.setToolTipText("(" + x + " , " + y +")");
	}
	
	public final void setStatusBar(String status){
		windowPanel.setStatusBar(status);
	}
	
	public final void initRandomly(){
		gameController.initRandomly();
		windowPanel.setStatusBar("Successfully initialize randomly the whole labyrinth!");
	}
	
	public final void resizeLabyrinth(int width, int height) {
		gameController.resizeLabyrinth(width, height);
	}

	public final void openFile(String fileAdd) {
		byte result = gameController.openFile(fileAdd);
		if (result == 1){
			openFileFailed("This file cannot be recognized as a labyrinth!");
		}else if (result == 2){
			openFileFailed("This labyrinth is too big to display!");
		}
	}

	private final void openFileFailed(String wrongMsg) {
		JOptionPane.showMessageDialog( windowPanel,
				                       wrongMsg,
                                       "Failed to open the file",
                                       JOptionPane.WARNING_MESSAGE );
	}

	public final void saveFile() {
		if (gameController.saveFile())
			windowPanel.setStatusBar("Successfully saved the file!");
		else{
			windowPanel.setStatusBar("Saving file failed!");
			JOptionPane.showMessageDialog( windowPanel,
                                           "Unexpected: failed to save this labyrinth!",
                                           "Failed to save the file",
                                           JOptionPane.WARNING_MESSAGE );
		}
	}

	public final void newFile(String fileName, int width, int height) {
		gameController.newFile(fileName, width, height);
	}

	/**
	 * @return the screenWidth
	 */
	public final int getScreenWidth() {
		return ScreenWidth;
	}

	/**
	 * @return the screenHeight
	 */
	public final int getScreenHeight() {
		return ScreenHeight;
	}
}