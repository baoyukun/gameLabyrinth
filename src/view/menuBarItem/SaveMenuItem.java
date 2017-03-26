package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

/**
 * Save the labyrinth to file.
 * Realize the same function as SaveMenuItemButton.
 * @see view.toolBarButton.SaveMenuItemButton
 */
public final class SaveMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public SaveMenuItem(GameFrame gameWindow)
	{
		super("Save") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/saveMenuItem.png"));
		setToolTipText("Save current labyrinth to file");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		gameWindow.saveFile();
	}
	
}
