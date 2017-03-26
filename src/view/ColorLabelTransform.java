package view;
import java.util.Hashtable;

/**
 * Facilitate the transformation between color and box label.
 */
public final class ColorLabelTransform {
	
	private final Hashtable<Integer, String> colorLabel;
	private final Hashtable<String, Integer> labelColor;
	
	private final byte emptyColor = 0;
	private final byte wallColor = 1;
	private final byte startColor = 2;
	private final byte endColor = 10;
	
	public ColorLabelTransform()
	{
		colorLabel = new Hashtable<Integer, String>();
		
		colorLabel.put((int) emptyColor, "Empty");
		colorLabel.put((int) wallColor, "Wall");
		colorLabel.put((int) startColor, "Depart");
		colorLabel.put((int) endColor, "Arrival");
		
		labelColor = new Hashtable<String, Integer>();
		
		labelColor.put("Empty", (int) emptyColor);
		labelColor.put("Wall", (int) wallColor);
		labelColor.put("Depart", (int) startColor);
		labelColor.put("Arrival", (int) endColor);
	}
	
	/**
	 * change from color to label.
	 * @param color The given color, to be transformed to corresponding label.
	 * @return The corresponding label.
	 */
	public final String toLabel(Integer color)
	{
		return colorLabel.get(color);
	}
	
	/**
	 * change from label to color.
	 * @param label The given label, to be transformed to corresponding color.
	 * @return The corresponding color coding.
	 */
	public final Integer toColor(String label)
	{
		return labelColor.get(label);
	}
}