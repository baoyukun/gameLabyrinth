package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

import java.awt.Desktop;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;
import java.io.File;
import java.io.IOException;

/**
 * Look up the documentation of this application in JavaDoc format.
 * Realize the same function as DocumentMenuItemButton.
 * @see view.toolBarButton.DocumentMenuItemButton
 */
public final class DocumentMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public DocumentMenuItem(GameFrame gameWindow)
	{
		super("Document") ;
		setAccelerator(KeyStroke.getKeyStroke("F9"));
		setIcon(new ImageIcon("icon/documentMenuItem.png"));
		setToolTipText("Documentation about this game");
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		try {
			Desktop.getDesktop().open(new File("doc/index.html"));
		} catch (IOException openException) {
			openException.printStackTrace();
		}
	}
	
}
