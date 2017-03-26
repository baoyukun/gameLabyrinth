package maze;

/**
 * Exception occurs when the labyrinth size is not correct.
 */
public class MazeReadingException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MazeReadingException(String fileName, int errLineNum, String errMsg) {
		
		super("Line "+ errLineNum + " in file " + fileName + " : " + errMsg);
		
	}

}
