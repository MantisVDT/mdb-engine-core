package de.mdb.engine.core.display.event;

import de.mdb.engine.core.event.IEvent;

/**
 * Represents a change in size of the Framebuffer in the Event System
 * Is called if the Display size is changed
 * 
 * @author Mattis Boeckle
 *
 */
public class FramebufferSizeEvent implements IEvent{
	
	
	//Private Fields
	private int width;
	private int height;
	
	/**
	 * The constructor for the FramebufferSizeEvent
	 * Initializes this class as an Event
	 * and stores the new width and height of the Framebuffer
	 * 
	 * @param width
	 * @param height
	 */
	public FramebufferSizeEvent(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Getter for width
	 * 
	 * @return The width of the Framebuffer in pixels
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter for height
	 * 
	 * @return The height of the Framebuffer in pixels
	 */
	public int getHeight() {
		return height;
	}
	
}
