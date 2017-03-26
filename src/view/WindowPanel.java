package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;


/**
 * The content pane of the game frame.
 * It contains, from north to south, tool bar, game panel and status bar. 
 */
public class WindowPanel extends JPanel{

	static final long serialVersionUID = 1L;
	private final GameStatusBar gameStatusBar;
	
	public WindowPanel(GameFrame gameWindow, String fileAdd, GamePanel gamePanel, GameController gameController)
	{
		super();
		
		addKeyListener(gameController);

		setLayout(new BorderLayout());
		
		add(gamePanel, BorderLayout.CENTER) ;
		
		gameStatusBar = new GameStatusBar(fileAdd);
		add(gameStatusBar, BorderLayout.SOUTH);
	
		add(new GameToolBar(gameWindow, gameController), BorderLayout.NORTH) ;
	}
	
	public final void setStatusBar(String status){
		gameStatusBar.reSetText(status);
	}
}