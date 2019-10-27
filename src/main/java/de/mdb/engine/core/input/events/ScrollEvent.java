package de.mdb.engine.core.input.events;

import de.mdb.engine.core.event.IEvent;

public class ScrollEvent implements IEvent{
	
	private double xoffset;
	private double yoffset;
	
	public ScrollEvent(double xoffset, double yoffset)
	{
		this.xoffset = xoffset;
		this.yoffset = yoffset;
	}
	
	public double getXOffset()
	{
		return xoffset;
	}
	
	public double getYOffset()
	{
		return yoffset;
	}
	
}
