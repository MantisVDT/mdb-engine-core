package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * 
 * <h1>Basic Event</h1>
 * <p>
 * Usually called when the Mouse is dragged
 * 
 * 
 * @version 1.1
 * 
 * @author Mattis Boeckle
 *
 */
public class MouseDraggedEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private int x,y, keyCode;
	
	/**
	 * Default Constructor
	 * 
	 * @param x			The x Position of the Mouse
	 * @param y			The y Position of the Mouse
	 * @param keyCode	The keyCode of the pressed Button
	 */
	public MouseDraggedEvent(int x, int y, int keyCode)
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
	public int getX()
	{
		return x;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The y value of the Event
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The keyCode of the pressed Button
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
}
