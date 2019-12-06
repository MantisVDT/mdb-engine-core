package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when the Mouse moved
 * 
 * @author Mattis Boeckle
 *
 */
public class MouseMovedEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private float x,y;
	
	private float dx, dy;
	
	/**
	 * Event Constructor <br>
	 * Stores the new x and y Coordinate of the Mouse
	 * 
	 * @param x	The new x Coordinate of the Mouse
	 * @param y	The new y Coordinate of the Mouse
	 * @param dx The difference of the new x Coordinate to the last one
	 * @param dy The difference of the new y Coordinate to the last one
	 */
	public MouseMovedEvent(float x, float y, float dx, float dy)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Getter for the x Coordinate
	 * 
	 * @return The x Coordinate
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Getter for the y Coordinate
	 * 
	 * @return The y Coordinate
	 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * Getter for the x Difference
	 * 
	 * @return The amount the Mouse moved on the x-Axis since the last Event
	 */
	public float getDX()
	{
		return dx;
	}
	
	/**
	 * Getter for the y Difference
	 * 
	 * @return The amount the Mouse moved on the y-Axis since the last Event
	 */
	public float getDY()
	{
		return dy;
	}
	
	
}
