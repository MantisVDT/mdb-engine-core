package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when a Key is either pressed or released <br>
 * Stores the keyCode, mods of the Key and whether it was pressed
 * 
 * @author Mattis Boeckle
 *
 */
public class KeyEvent implements IEvent {
	
	//Private Fields
	private int keyCode;
	private int mods;
	private boolean pressed;
	
	/**
	 * Event Constructor <br>
	 * Stores the given values
	 * 
	 * @param keyCode The keyCode of the pressed/released Key
	 * @param mods The mods of the pressed Key for Example Shift
	 * @param pressed Whether the button was pressed or released, true if pressed
	 */
	public KeyEvent(int keyCode, int mods, boolean pressed)
	{
		this.keyCode = keyCode;
		this.mods = mods;
		this.pressed = pressed;
	}
	
	/**
	 * Getter for the KeyCode of the Event Key
	 * 
	 * @return The KeyCode
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	/**
	 * Getter for the Mods of the Event
	 * 
	 * @return The Mods
	 */
	public int getMods()
	{
		return mods;
	}
	
	/**
	 * Getter for pressed
	 * 
	 * @return Whether the button was pressed or released
	 */
	public boolean isPressed()
	{
		return pressed;
	}
	
}
