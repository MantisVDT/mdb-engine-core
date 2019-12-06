package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when the Mouse is dragged (moving while a button is pressed) <br>
 * Stores the new x and y coordinates of the Mouse
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
	 * Event Constructor
	 * 
	 * @param x			The x Coordinate of the Mouse
	 * @param y			The y Coordinate of the Mouse
	 * @param keyCode	The keyCode of the Button that is currently pressed
	 */
	public MouseDraggedEvent(int x, int y, int keyCode)
	{
		this.x = x;
		this.y = y;
		this.keyCode = keyCode;
	}
	
	/**
	 * Getter for the x Coordinate
	 * 
	 * @return The x Coordinate of the Mouse
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Getter for the y Coordinate
	 * 
	 * @return The y Coordinate of the Mouse
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Getter for the KeyCode
	 * 
	 * @return The keyCode of the currently pressed Button
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
}
