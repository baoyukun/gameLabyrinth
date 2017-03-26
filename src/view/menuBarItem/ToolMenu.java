package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Tool menu inside the menu bar. */

public final class ToolMenu extends JMenu
{
	static final long serialVersionUID = 1L ;
	
	/** Constructor:
	 * @param gameFrame The current Java frame.
	 */
	public ToolMenu(GameFrame gameFrame)
	{
		super("Tool") ;
		
        add(new ConfigureMenuItem(gameFrame)) ;
		add(new InitMenuItem(gameFrame)) ;
	}
}