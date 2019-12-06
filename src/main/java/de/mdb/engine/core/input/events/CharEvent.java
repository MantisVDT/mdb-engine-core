package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

/**
 * Represents a char event from GLFW <br>
 * Contains the codepoint
 * 
 * @author Mattis Boeckle
 *
 */
public class CharEvent implements IEvent{
	
	//Private Fields
	private int codepoint;
	
	/**
	 * Event constructor <br>
	 * Stores the codepoint value
	 * 
	 * @param codepoint The codepoint to be stored
	 */
	public CharEvent(int codepoint)
	{
		this.codepoint = codepoint;
	}

	/**
	 * Getter for Codepoint
	 * 
	 * @return The stored codepoint
	 */
	public int getCodepoint() {
		return codepoint;
	}
	
}
