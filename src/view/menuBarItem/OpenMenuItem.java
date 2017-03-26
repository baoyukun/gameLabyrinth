package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;
import java.io.File;

/**
 * Open a labyrinth from file.
 * Realize the same function as OpenMenuItemButton.
 * @see view.toolBarButton.OpenMenuItemButton
 */
public final class OpenMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public OpenMenuItem(GameFrame gameWindow)
	{
		super("Open") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/openMenuItem.png"));
		setToolTipText("Open an existing labyrinth from file");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser("data/");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fc.showOpenDialog(gameWindow) == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			if (file != null)
				gameWindow.openFile(file.getAbsolutePath());
		}
	}
}