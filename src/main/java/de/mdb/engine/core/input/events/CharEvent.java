package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

public class CharEvent implements IEvent{
	
	private int codepoint;
	
	public CharEvent(int codepoint)
	{
		this.codepoint = codepoint;
	}

	public int getCodepoint() {
		return codepoint;
	}
	
}
