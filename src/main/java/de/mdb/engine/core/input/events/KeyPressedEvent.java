package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when a Key is Pressed <br>
 * Stores the KeyCode and Mods of the pressed Key
 * 
 * @author Mattis Boeckle
 *
 */
public class KeyPressedEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private int keyCode;
	private int mods;
	
	/**
	 * Event Constructor <br>
	 * Stores the keyCode and mods of the pressed Event Key
	 * 
	 * @param keyCode The keyCode of the pressed Key
	 */
	public KeyPressedEvent(int keyCode, int mods)
	{
		this.keyCode = keyCode;
		this.mods = mods;
	}
	
	/**
	 * Getter for the keyCode
	 * 
	 * @return The keyCode of the pressed Key
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	/**
	 * Getter for the Mods
	 * 
	 * @return The Mods for the pressed Key
	 */
	public int getMods()
	{
		return mods;
	}
	
}
