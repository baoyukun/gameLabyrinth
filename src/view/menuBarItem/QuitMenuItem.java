package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

/**
 * Quit this game.
 */
public final class QuitMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public QuitMenuItem(GameFrame gameWindow)
	{
		super("Quit") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/exitMenuItem.png"));
		setToolTipText("Quit this game");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt)
	{
		gameWindow.quit() ;
	}
	
}
