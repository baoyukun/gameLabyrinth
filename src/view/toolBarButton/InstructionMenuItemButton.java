package view.toolBarButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.InstructionMenuItem
 */
public class InstructionMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private final GameFrame gameWindow ;
	
	public InstructionMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
		this.gameWindow = gameWindow ;
        addKeyListener(gameController);
		addActionListener(this) ;
		setIcon(new ImageIcon("icon/instructionMenuItem.png"));
		setToolTipText("Instructions on how to play the game");
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		gameWindow.instructionNotify();
	}

}
