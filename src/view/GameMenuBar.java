package view;

import javax.swing.* ;

import view.menuBarItem.FileMenu;
import view.menuBarItem.HelpMenu;
import view.menuBarItem.ToolMenu;

/** The Menu Bar of the game frame. */

public final class GameMenuBar extends JMenuBar
{
	static final long serialVersionUID = 1L ;
	
	public GameMenuBar(GameFrame gameWindow)
	{
		super() ;	
		
		add(new FileMenu(gameWindow)) ;
		add(new ToolMenu(gameWindow)) ;
		add(new HelpMenu(gameWindow)) ;
	}
}
