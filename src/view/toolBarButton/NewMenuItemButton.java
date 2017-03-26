package view.toolBarButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.GameController;
import view.GameFrame;

/**
 * @see view.menuBarItem.NewMenuItem
 */
public class NewMenuItemButton extends JButton
                               implements ActionListener{

	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private final GameFrame gameWindow ;
	
	public NewMenuItemButton(GameFrame gameWindow, GameController gameController){
		super();
		
		this.gameWindow = gameWindow ;
        addKeyListener(gameController);
		addActionListener(this) ;
		setIcon(new ImageIcon("icon/newMenuItem.png"));
		setToolTipText("Create a new labyrinth");
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