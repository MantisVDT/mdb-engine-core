package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Created when a Key is released <br>
 * Stores the KeyCode and Mods of the released Key
 * 
 * @author Mattis Boeckle
 *
 */
public class KeyReleasedEvent implements IEvent {
	
	/*
	 * --------------
	 * PRIVATE FIELDS
	 * --------------
	 */
	private int keyCode;
	private int mods;
	
	/**
	 * Event Constructor <br>
	 * Stores the keyCode and the mods of the released Key
	 * 
	 * @param keyCode The keyCode of the released Key
	 * @param mods The mods of the Key
	 */
	public KeyReleasedEvent(int keyCode, int mods)
	{
		this.keyCode = keyCode;
		this.mods = mods;
	}
	
	/**
	 * Getter for the keyCode
	 * 
	 * @return The keyCode of the released Key
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	/**
	 * Getter for the Mods
	 * 
	 * @return The mods of the released Key
	 */
	public int getMods()
	{
		return mods;
	}
	
}
