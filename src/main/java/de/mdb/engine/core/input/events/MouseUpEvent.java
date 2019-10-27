package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * 
 * <h1>Basic Event</h1>
 * <p>
 * Usually called when a Mouse Button is released
 * 
 * 
 * @version 1.1
 * 
 * @author Mattis Boeckle
 *
 */
public class MouseUpEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private float x, y;
	private int keyCode;
	
	/**
	 * Default Constructor
	 * 
	 * @param x			The x position where the Mouse is released
	 * @param y			The y position where the Mouse is released
	 * @param keyCode	The keyCode of the pressed Mouse Button
	 */
	public MouseUpEvent(float x, float y, int keyCode)
	{
		this.x = x;
		this.y = y;
		this.keyCode = keyCode;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The x value of the Event
	 */
	public float getX()
	{
		return x;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The y value of the Event
	 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The keyCode of the released Button
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
}
