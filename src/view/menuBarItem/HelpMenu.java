package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Help menu inside the menu bar. */

public final class HelpMenu extends JMenu
{
	static final long serialVersionUID = 1L ;
	
	/** Constructor:
	 * @param gameFrame The current Java frame.
	 */
	public HelpMenu(GameFrame gameFrame)
	{
		super("Help") ;
		
		add(new InstructionMenuItem(gameFrame)) ;
        add(new DocumentMenuItem(gameFrame)) ;
	}
}