package view;

import java.awt.* ;

/** A class for modeling the game surface with equal-sized blocks. */

public class GameModel 
{
	/** A color. */
	public static final byte lightGray = (byte)  0 ;

	/** A color. */
	public static final byte black     = (byte)  1 ;

	/** A color. */
	public static final byte blue      = (byte)  2 ;

	/** A color. */
	public static final byte cyan      = (byte)  3 ;

	/** A color. */
	public static final byte darkGray  = (byte)  4 ;

	/** A color. */
	public static final byte gray      = (byte)  5 ;

	/** A color. */
	public static final byte green     = (byte)  6 ;

	/** A color. */
	public static final byte magenta   = (byte)  7 ;

	/** A color. */
	public static final byte orange    = (byte)  8 ;

	/** A color. */
	public static final byte pink      = (byte)  9 ;

	/** A color. */
	public static final byte red       = (byte) 10 ;

	/** A color. */
	public static final byte white     = (byte) 11 ;

	/** A color. */
	public static final byte yellow    = (byte) 12 ;
	
	/** The width of the game in blocks. */
	private final int gameWidth ;

	/** The height of the game in blocks. */
	private final int gameHeight ;

	/** The width in pixel of a screen block. */
	private final int blockWidth ;

	/** The height in pixel of a screen block. */
	private final int blockHeight ;

	/** The modelisation of the game surface. */
	private final byte[][] box ;

	/** Constructor: records the parameters and allocate the box array of byte.
	 * @param gameWidth The width of the game in blocks.
	 * @param gameHeight The height of the game in blocks.
	 * @param blockWidth The width in pixel of a screen block.
	 * @param blockHeight The height in pixel of a screen block.
	 */
	public GameModel(int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		this.gameWidth   = gameWidth ;
		this.gameHeight  = gameHeight ;
		this.blockWidth  = blockWidth ;
		this.blockHeight = blockHeight ;		
		this.box         = new byte[gameHeight][gameWidth] ;
	}
	
	/** Paints the model on the graphics g.
	 * @param g The Graphics object.
	 */
	public final void paintGame(Graphics g)
	{
		for (int h=0 ; h < gameHeight ; h++) {
			int yHeight = blockHeight * h ;
			for (int w=0 ; w < gameWidth ; w++) {
				int xWidth  = blockWidth  * w ;
				Color color = null ;
				switch (box[h][w]) {
				case lightGray : 
					color = Color.lightGray ; 
					break ;
				case black : 
					color = Color.black ; 
					break ;				
				case blue : 
					color = Color.blue ; 
					break ;
				case cyan : 
					color = Color.cyan ; 
					break ;
				case darkGray : 
					color = Color.darkGray ; 
					break ;
				case gray : 
					color = Color.gray ; 
					break ;
				case green : 
					color = Color.green ; 
					break ;
				case magenta : 
					color = Color.magenta ; 
					break ;
				case orange : 
					color = Color.orange ; 
					break ;
				case pink : 
					color = Color.pink ; 
					break ;
				case red : 
					color = Color.red ; 
					break ;
				case white : 
					color = Color.white ; 
					break ;
				case yellow : 
					color = Color.yellow ; 
					break ;
				default  : 
					color = Color.lightGray  ; 
					break ;
				}
				g.setColor(color);
				g.fillRect(xWidth, yHeight, blockWidth, blockHeight);
			}
		}
	}

	/** Gets the color of a block of the model.
	 * @param w X coordinate of the block.
	 * @param h Y coordinate of the block.
	 * @return color of the block.
	 */
	public final byte get(int w, int h)
	{
		return box[h][w] ;
	}
	
	/** Sets the color of a block of the model.
	 * @param w X coordinate of the block.
	 * @param h Y coordinate of the block.
	 * @param b color of the block.
	 */
	public final void set(int w, int h, byte b)
	{
		box[h][w] = b ;
	}
	
	/** Sets the color of an horizontal line in the model.
	 * @param w X coordinate of the first block.
	 * @param h Y coordinate of the first block.
	 * @param width width of the line.
	 * @param b color of the line.
	 */
	public final void setHLine(int w, int h, int width, byte b)
	{
		int w2 = w + width ;
		for (int w1 = w ; w1 < w2 ; w1++)
			box[h][w1] = b ;
	}
	
	/** Sets the color of a vertical line in the model.
	 * @param w X coordinate of the first block.
	 * @param h Y coordinate of the first block.
	 * @param height height of the line.
	 * @param b color of the line.
	 */
	public final void setVLine(int w, int h, int height, byte b)
	{
		int h2 = h + height ;
		for (int h1 = h ; h1 < h2 ; h1++)
			box[h1][w] = b ;
	}
	
	/** Sets the color of the circumference of a rectangle in the model.
	 * @param w X coordinate of the upper left-most block.
	 * @param h Y coordinate of the upper left-most block.
	 * @param width width of the rectangle.
	 * @param height height of the rectangle.
	 * @param b color of the circumference of the rectangle.
	 */
	public final void drawRectangle(int w, int h, int width, int height, byte b)
	{
		setHLine(w,h,width,b) ;
		setHLine(w,h+height-1,width,b) ;
		setVLine(w,h,height,b) ;
		setVLine(w+width-1,h,height,b) ;
	}

	
	/** Sets the color of a filled rectangle in the model.
	 * @param w X coordinate of the upper left-most block.
	 * @param h Y coordinate of the upper left-most block.
	 * @param width width of the rectangle.
	 * @param height height of the rectangle.
	 * @param b color of the filled rectangle.
	 */
	public final void fillRectangle(int w, int h, int width, int height, byte b)
	{
		int w2 = w + width ;
		int h2 = h + height ;
		for (int h1 = h ; h1 < h2 ; h1++)
			for (int w1 = w ; w1 < w2 ; w1++)
				box[h1][w1] = b ;
	}

	/** Copies another model on this model.
	 * @param gameModel To be copied.
	 */
	public final void copy(GameModel gameModel)
	{
		for (int h=0 ; h < gameHeight ; h++)
			for (int w=0 ; w < gameWidth ; w++)
				box[h][w] = gameModel.box[h][w] ;
	}
}