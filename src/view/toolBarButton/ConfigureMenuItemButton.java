package view.toolBarButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.ConfigureMenuItem
 */
public class ConfigureMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private final GameFrame gameWindow ;
	
	public ConfigureMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
		this.gameWindow = gameWindow ;
		
        addKeyListener(gameController);
		addActionListener(this) ;
		
		setIcon(new ImageIcon("icon/configureMenuItem.png"));
		setToolTipText("Configure the labyrinth");
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		
		JTextField width = new JTextField();
		JTextField height = new JTextField();
		Object[] inputRequire = {
		    "Set width:", width,
		    "Set height:", height
		};

		int response = JOptionPane.showConfirmDialog(gameWindow,
				                                   inputRequire,
				                                   "Configure the labyrinth",
				                                   JOptionPane.OK_CANCEL_OPTION);
		
		if (response == JOptionPane.OK_OPTION){
			try{
				int newWidth = Integer.parseInt(width.getText());
				int newHeight = Integer.parseInt(height.getText());
				isInputValid(newWidth, newHeight);
			}catch (NumberFormatException falseInput){
				wrongInputWarning("Input format: only accept integers!");
			}    
		}
	}

	private final void isInputValid(int width, int height) {
		if (width < 1 || height < 1){
			wrongInputWarning("The dimension should be at least 1 !");
			return;
		}
		
		if (gameWindow.getScreenWidth()/width <= 1 || gameWindow.getScreenHeight()/height <= 1)
		{
			wrongInputWarning("The dimension required is too big!");
			return;
		}
		
		gameWindow.resizeLabyrinth(width, height);
	}

	private final void wrongInputWarning(String cause) {
		JOptionPane.showMessageDialog( gameWindow,
                                       cause,
                                       "Invalid Input",
                                       JOptionPane.WARNING_MESSAGE );
	}
}