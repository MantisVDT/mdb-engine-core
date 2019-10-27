package de.mdb.engine.core.display.event;

import de.mdb.engine.core.event.IEvent;

public class FramebufferSizeEvent implements IEvent{
	
	private int width;
	private int height;
	public FramebufferSizeEvent(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
