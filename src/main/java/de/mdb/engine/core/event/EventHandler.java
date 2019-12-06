package de.mdb.engine.core.event;

import java.lang.reflect.Method;
import java.util.logging.Level;

import de.mdb.engine.core.logger.Debug;

/**
 * Represents one EventHandler to be stored in the EventExecutor
 * 
 * @author Simon Forsberg
 * Changes by Mattis Boeckle
 *
 */
public class EventHandler{
	
	//Private Fields
	private final EventListener listener;
	private final Method method;
	private final Event annotation;
	
	/**
	 * Constructor for one EventHandler
	 * 
	 * @param listener The stored EventListener
	 * @param method The 
	 * @param annotation
	 */
	public EventHandler(EventListener listener, Method method, Event annotation)
	{
		this.listener = listener;
		this.method = method;
		this.annotation = annotation;
	}
	
	public Event getAnnotation() 
	{
		return annotation;
	}
	
	public Method getMethod()
	{
		return method;
	}
	
	public EventListener getListener()
	{
		return listener;
	}
	
	public void execute(IEvent event)
	{
		try {
			method.invoke(listener, event);
		}catch(Exception e1) {
			Debug.log(Level.SEVERE, "Exception when performing EventHandler " + this.listener + " for event " + event.toString());
			Debug.log(Level.SEVERE, e1.getMessage());
		}
	}
	
	@Override
	public String toString()
	{
		return "(EventHandler" + this.listener + ": " + method.getName() + ")";
	}
}
