package view;

import java.awt.event.*;

/**
 * Indispensable element in GUI design which takes charge of reception of all input by users.
 */
public abstract class GameController
	implements MouseListener, MouseMotionListener, KeyListener
{
	/** Game block width. */
	protected final int gameWidth ;

	/** Game height. */
	protected final int gameHeight ;
	
	/** Game block width. */
	protected final int blockWidth ;

	/** Game block height. */
	protected final int blockHeight ;
	
	/** The game window. */
	protected GameFrame gameFrame ;
	
	/** Constructor:
	 * @param name The game name to be displayed at the top of the window.
	 * @param gameWidth The width of the game in blocks.
	 * @param gameHeight The height of the game in blocks.
	 * @param blockWidth The width in pixel of a screen block.
	 * @param blockHeight The height in pixel of a screen block.
	 */
	public GameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		this.gameWidth   = gameWidth   ;
		this.gameHeight  = gameHeight  ;
		this.blockWidth  = blockWidth  ;
		this.blockHeight = blockHeight ;
		this.gameFrame   = null;
	}
		
	/** To notify the game frame that we have a modified gameModel:
	 * @param gameModel The modified gameModel.
	 */
	public final void notify(GameModel gameModel)
	{
		gameFrame.set(gameModel);
	}
	
	/** Gets the X block rank according to the X screen coordinate:
	 * @param x The X screen coordinate.
	 * @return The X block rank of the clicked block.
	 */
	public final int getGameX(int x)
	{
		return x / blockWidth ;
	}
	
	/** Gets the Y block rank according to the Y screen coordinate:
	 * @param y The Y screen coordinate.
	 * @return The Y block rank of the clicked block.
	 */
	public final int getGameY(int y)
	{
		return y / blockHeight ;
	}
	
	/** Gets the X block rank according to the MouseEvent:
	 * @param e The MouseEvent.
	 * @return The X block rank of the clicked block.
	 */
	public final int getGameX(MouseEvent e)
	{
		return getGameX(e.getX()) ;
	}
	
	/** Gets the Y block rank according to the MouseEvent:
	 * @param e The MouseEvent.
	 * @return The Y block rank of the clicked block.
	 */
	public final int getGameY(MouseEvent e)
	{
		return getGameY(e.getY()) ;
	}
}