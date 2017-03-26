package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

/**
 * Give help dialogue about how to play this game.
 * Realize the same function as InstructionMenuItemButton.
 * @see view.toolBarButton.InstructionMenuItemButton
 */
public final class InstructionMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public InstructionMenuItem(GameFrame gameWindow)
	{
		super("Instruction") ;
		setAccelerator(KeyStroke.getKeyStroke("F1"));
		setIcon(new ImageIcon("icon/instructionMenuItem.png"));
		setToolTipText("Instructions on how to play the game");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		gameWindow.instructionNotify();
	}
	
}
