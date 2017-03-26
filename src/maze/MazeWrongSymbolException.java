package maze;

/**
 * Exception occurs when there are unrecognizable symbols in the file.
 */
public class MazeWrongSymbolException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public MazeWrongSymbolException(int errLine, int errColumn, char errSymbol){
		super("Line "+ errLine + " Column "+ errColumn + " : " +
	          "The symbol " + errSymbol + " is not recognizable!");
	}

}
