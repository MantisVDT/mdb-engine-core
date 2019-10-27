package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * 
 * <h1>Basic Event</h1>
 * <p>
 * Usually called when the Mouse is moved
 * 
 * 
 * @version 1.1
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
	 * Default Constructor
	 * 
	 * @param x	The new x position of the Mouse
	 * @param y	The new y position of the Mouse
	 */
	public MouseMovedEvent(float x, float y, float dx, float dy)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * @return The x value of the Event
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * @return The y value of the Event
	 */
	public float getY()
	{
		return y;
	}
	
	public float getDX()
	{
		return dx;
	}
	
	public float getDY()
	{
		return dy;
	}
	
	
}
