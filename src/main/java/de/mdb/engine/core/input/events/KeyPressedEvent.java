package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * 
 * <h1>Basic Event</h1>
 * <p>
 * Usually called when a key is pressed
 * 
 * 
 * @version 1.1
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
	 * Default Constructor
	 * 
	 * @param keyCode The keyCode of the pressed Key
	 */
	public KeyPressedEvent(int keyCode, int mods)
	{
		this.keyCode = keyCode;
		this.mods = mods;
	}
	
	/**
	 * Basic Getter
	 * 
	 * @return The keyCode of the pressed Key
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	public int getMods()
	{
		return mods;
	}
	
}
