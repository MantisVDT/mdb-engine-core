package de.mdb.engine.core.event;

public class EventManager {
	
	public static EventExecutor events = new EventExecutor();
	
	public static void registerListener(EventListener eventListener)
	{
		events.registerListener(eventListener);
	}
	
	public static void removeListener(EventListener listener)
	{
		events.removeListener(listener);
	}
	
	public static void executeEvent(IEvent event)
	{
		events.executeEvent(event);
	}
}
