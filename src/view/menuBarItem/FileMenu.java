package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The File menu inside the menu bar. */

public final class FileMenu extends JMenu
{
	static final long serialVersionUID = 1L ;
	
	/** Constructor:
	 * @param gameFrame The current Java frame.
	 */
	public FileMenu(GameFrame gameFrame)
	{
		super("File") ;
		
        add(new NewMenuItem(gameFrame)) ;
		
		addSeparator();
		
		add(new OpenMenuItem(gameFrame)) ;
		add(new SaveMenuItem(gameFrame)) ;
		
		addSeparator();
		
		add(new QuitMenuItem(gameFrame)) ;
		
	}
}
