package view;

import javax.swing.JToolBar;

import view.toolBarButton.*;

/** The Tool Bar of the game frame. */
public class GameToolBar extends JToolBar{

	static final long serialVersionUID = 1L;
	
	public GameToolBar(GameFrame gameWindow, GameController gameController){
		super();
		
		add(new NewMenuItemButton(gameWindow, gameController));
		add(new OpenMenuItemButton(gameWindow, gameController));
		add(new SaveMenuItemButton(gameWindow, gameController));
		
		addSeparator();
		
		add(new ConfigureMenuItemButton(gameWindow, gameController));
		add(new InitMenuItemButton(gameWindow, gameController));
		
		addSeparator();
		
		add(new InstructionMenuItemButton(gameWindow, gameController));
		add(new DocumentMenuItemButton(gameWindow, gameController));
	}
}