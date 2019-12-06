package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when a Mouse Button is pressed <br>
 * Stores the x and y Coordinates of the Mouse and the keyCode of the pressed Button
 * 
 * @author Mattis Boeckle
 *
 */
public class MouseDownEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private float x, y;
	private int keyCode;
	
	/**
	 * Event Constructor
	 * 
	 * @param x			The x position where the Mouse is pressed
	 * @param y			The y position where the Mouse is pressed
	 * @param keyCode	The keyCode of the pressed Button
	 */
	public MouseDownEvent(float x, float y, int keyCode)
	{
		this.x = x;
		this.y = y;
		this.keyCode = keyCode;
	}
	
	/**
	 * Getter for the x Coordinate
	 * 
	 * @return	The x value of the position of the Mouse
	 */
	public float getX()
	{
		return x;
	}
	
	/**
	 * Getter for the y Coordinate
	 * 
	 * @return The y value of the position of the Mouse
	 */
	public float getY()
	{
		return y;
	}
	
	/**
	 * Getter for the KeyCode of the Button
	 * 
	 * @return The keyCode of the pressed Button
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
}
