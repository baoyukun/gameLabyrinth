package view.toolBarButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.InitMenuItem
 */
public class InitMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private final GameFrame gameWindow ;
	
	public InitMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
		this.gameWindow = gameWindow ;
        addKeyListener(gameController);
		addActionListener(this) ;
		setIcon(new ImageIcon("icon/initMenuItem.png"));
		setToolTipText("Initialize randomly the labyrinth");
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		gameWindow.initRandomly();
	}

}
