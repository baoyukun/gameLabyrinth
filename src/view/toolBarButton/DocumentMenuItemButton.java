package view.toolBarButton;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.DocumentMenuItem
 */
public class DocumentMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	
	public DocumentMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
        addKeyListener(gameController);
		addActionListener(this) ;
		setIcon(new ImageIcon("icon/documentMenuItem.png"));
		setToolTipText("Documentation about this game");
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
