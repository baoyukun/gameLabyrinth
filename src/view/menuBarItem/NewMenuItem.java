package view.menuBarItem;

import javax.swing.* ;

import view.GameFrame;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create a totally new labyrinth.
 * Realize the same function as NewMenuItemButton.
 * @see view.toolBarButton.NewMenuItemButton
 */
public final class NewMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 1L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public NewMenuItem(GameFrame gameWindow)
	{
		super("New") ;
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		setIcon(new ImageIcon("icon/newMenuItem.png"));
		setToolTipText("Create a new labyrinth");
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		
		JTextField name, width, height;
		Object[] inputRequire = {
			"Give it a name:", name = new JTextField(),
		    "Set width:", width = new JTextField(),
		    "Set height:", height = new JTextField(),
		};

		int response = JOptionPane.showConfirmDialog(gameWindow,
				                                   inputRequire, 
				                                   "Create new labyrinth",
				                                   JOptionPane.OK_CANCEL_OPTION);
		
		if (response == JOptionPane.OK_OPTION){
			try{
				createNewLabyrinth(name.getText(),
						           Integer.parseInt(width.getText()),
						           Integer.parseInt(height.getText()));
			}catch (NumberFormatException notInteger){
				wrongInputWarning("Input format: only accept integers!");
			}    
		}
	}
	
	private final void createNewLabyrinth(String fileName, int labyrinthWidth, int labyrinthHeight)
	{
		if (isInputValid(labyrinthWidth, labyrinthHeight)){
			JFileChooser fc = new JFileChooser("data/");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fc.showSaveDialog(gameWindow) == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				if (file != null){
					String fileAdd = file.getAbsolutePath() + "\\" + fileName + ".txt";
					try{
						PrintWriter pw = new PrintWriter(fileAdd);
						pw.close();
						gameWindow.newFile(fileAdd, labyrinthWidth, labyrinthHeight);
					}catch (IOException e) {
						wrongInputWarning("Input format: file name is not legal!");
					}
				}
			}
		}
	}
	
	private final boolean isInputValid(int width, int height) {
		if (width < 1 || height < 1){
			wrongInputWarning("The dimension should be at least 1 !");
			return false;
		}
		
		if (gameWindow.getScreenWidth()/width <= 1 || gameWindow.getScreenHeight()/height <= 1)
		{
			wrongInputWarning("The dimension required is too big!");
			return false;
		}
		
		return true;
	}
	
	private final void wrongInputWarning(String cause) {
		JOptionPane.showMessageDialog( gameWindow,
                                       cause,
                                       "Invalid Input",
                                       JOptionPane.WARNING_MESSAGE );
	}

}