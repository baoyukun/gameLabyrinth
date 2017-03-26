package view.toolBarButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.OpenMenuItem
 */
public class OpenMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private final GameFrame gameWindow ;
	
	public OpenMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
		this.gameWindow = gameWindow ;
        addKeyListener(gameController);
		addActionListener(this) ;
		setIcon(new ImageIcon("icon/openMenuItem.png"));
		setToolTipText("Open an existing labyrinth from file");
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