package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when any Mouse Event is fired (move, drag, press, release)
 * 
 * @author Mattis Boeckle
 *
 */
public class MouseEvent implements IEvent{
	
	//Private Fields
	private int keyCode;
	private int mods;
	private boolean pressed;
	private float x;
	private float y;
	
	/**
	 * Event Constructor <br>
	 * Handles and stores Information about any MouseEvent
	 * 
	 * @param x The x Coordinate of the Mouse
	 * @param y The y Coordinate of the Mouse
	 * @param keyCode The keyCode of the Event Key
	 * @param mods The Mods of the Event Key
	 * @param pressed Whether the Event Key was pressed
	 */
	public MouseEvent(float x, float y, int keyCode, int mods, boolean pressed) {
		super();
		this.keyCode = keyCode;
		this.mods = mods;
		this.pressed = pressed;
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for the x Coordinate
	 * 
	 * @return The current x Coordinate of the Mouse
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Getter for the y Coordinate
	 * 
	 * @return The current y Coordinate of the Mouse
	 */
	public float getY() {
		return y;
	}

	/**
	 * Getter for the KeyCode
	 * 
	 * @return The KeyCode for the Button that is released or pressed
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * Getter for the Mods
	 * 
	 * @return The Mods of the Event Key
	 */
	public int getMods() {
		return mods;
	}

	/**
	 * Getter for whether the Button is Pressed
	 * 
	 * @return true, if the Button is pressed; false, if the Button is released
	 */
	public boolean isPressed() {
		return pressed;
	}
}
