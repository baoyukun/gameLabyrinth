package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

/**
 * Initialize the labyrinth randomly.
 * Realize the same function as InitMenuItemButton.
 * @see view.toolBarButton.InitMenuItemButton
 */
public final class InitMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public InitMenuItem(GameFrame gameWindow)
	{
		super("Init") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/initMenuItem.png"));
		setToolTipText("Initialize randomly the labyrinth");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		gameWindow.initRandomly();
	}
	
}
