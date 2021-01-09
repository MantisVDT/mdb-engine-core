package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when the Mousewheel is scrolled
 * 
 * @author Mattis Boeckle
 *
 */
public class ScrollEvent implements IEvent{
	
	//Private Fields
	private double xoffset;
	private double yoffset;
	
	/**
	 * Event Constructor <br>
	 * Stores the x and y offset of the Mousewheel
	 * 
	 * @param xoffset
	 * @param yoffset
	 */
	public ScrollEvent(double xoffset, double yoffset)
	{
		this.xoffset = xoffset;
		this.yoffset = yoffset;
	}
	
	/**
	 * Getter for the x Offset
	 * 
	 * @return The x offset of the Mousewheel
	 */
	public double getXOffset()
	{
		return xoffset;
	}
	
	/**
	 * Getter for the y Offset
	 * 
	 * @return The y offset of the Mousewheel
	 */
	public double getYOffset()
	{
		return yoffset;
	}
	
}
