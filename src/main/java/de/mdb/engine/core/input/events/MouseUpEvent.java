package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when a Mouse Button is released
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
	 * Event Constructor <br>
	 * Stores the x and y Coordinate of where the Button was pressed and the Buttons KeyCode
	 * 
	 * @param x			The x Coordinate
	 * @param y			The y Coordinate
	 * @param keyCode	The keyCode of the released Mouse Button
	 */
	public MouseUpEvent(float x, float y, int keyCode)
	{
		this.x = x;
		this.y = y;
		this.keyCode = keyCode;
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
	 * Getter for the keyCode of the released Button
	 * 
	 * @return The keyCode of the released Button
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
}
