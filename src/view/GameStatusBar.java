package view;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** The Status Bar of the game frame. */
public class GameStatusBar extends JLabel{

	static final long serialVersionUID = 1L;
	private final String fileAdd;
	
	public GameStatusBar(String fileAdd){
		super();
		this.fileAdd = fileAdd;
		setText(fileAdd + ":    Welcome to the labyrinth game! Press F1 for help. ");
		setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public final void reSetText(String status){
		setText(fileAdd+ ":    " + status + " Press F9 for help. ");
	}
}