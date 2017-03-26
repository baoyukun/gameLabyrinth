package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

/**
 * Configure the labyrinth size.
 * Realize the same function as ConfigureMenuItemButton.
 * @see view.toolBarButton.ConfigureMenuItemButton
 */
public final class ConfigureMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public ConfigureMenuItem(GameFrame gameWindow)
	{
		super("Configure") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/configureMenuItem.png"));
		setToolTipText("Configure the labyrinth");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
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