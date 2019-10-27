package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

public class KeyEvent implements IEvent {
	
	private int keyCode;
	private int mods;
	private boolean pressed;
	
	/**
	 * 
	 * Use this event if you want to listen to both Key presses and releases
	 * 
	 * @param keyCode
	 * @param mods
	 * @param pressed
	 */
	public KeyEvent(int keyCode, int mods, boolean pressed)
	{
		this.keyCode = keyCode;
		this.mods = mods;
		this.pressed = pressed;
	}
	
	public int getKeyCode()
	{
		return keyCode;
	}
	
	public int getMods()
	{
		return mods;
	}
	
	public boolean isPressed()
	{
		return pressed;
	}
	
}
