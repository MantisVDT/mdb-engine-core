package de.mdb.engine.core.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import de.mdb.engine.core.logger.Debug;

/**
 * Keeps track of all the EventHandlers and which specific Events they listen to
 * 
 * Changes and Documentation by Mattis Boeckle
 * @author Simon Forsberg
 *
 */
public class EventExecutor {
	
	// Private Fields
	private final Map<Class<? extends IEvent>, Collection<EventHandler>> bindings;
	private final Set<EventListener> registeredListeners;
	
	/**
	 * Constructor for EventExecutor <br>
	 * Initializes the bindings Map and List of registered Listeners
	 */
	public EventExecutor()
	{
		this.bindings = new HashMap<Class<? extends IEvent>, Collection<EventHandler>>();
		this.registeredListeners = new HashSet<EventListener>();
	}
	
	/**
	 * Finds and returns all the EventListeners that listen for a specific Event
	 * 
	 * @param clazz The Event to find all Listeners for
	 * @return A List of EventListeners that listen to the specified Event
	 */
	public List<EventHandler> getListenersFor(Class<? extends IEvent> clazz)
	{
		if(!this.bindings.containsKey(clazz)) return new ArrayList<EventHandler>();
		return new ArrayList<EventHandler>(this.bindings.get(clazz));
	}
	
	/**
	 * Executes an Event for all of its registered Listeners
	 * 
	 * @param event The Event to be executed
	 */
	public void executeEvent(IEvent event)
	{
		Collection<EventHandler> handlers = this.bindings.get(event.getClass());
		
		if(handlers != null)
		{
			for(EventHandler handler : handlers)
			{
				handler.execute(event);
			}
		}
	}
	
	/**
	 * Registers an EventListener <br><br>
	 * Valid Methods need to: <ul>
	 *   <li>be public</li>
	 *   <li>return void</li>
	 *   <li>have only one argument, which is of type IEvent</li>
	 * </ul>
	 * 
	 * @param listener The EventListener to register
	 */
	public void registerListener(final EventListener listener)
	{
		if(registeredListeners.contains(listener))
		{
			Debug.log(Level.WARNING, "Listener already registered!");
		}
		
		Method[] methods = listener.getClass().getDeclaredMethods();
		this.registeredListeners.add(listener);
		
		for(final Method method : methods)
		{
			Event annotation = method.getAnnotation(Event.class);
			Class<?>[] parameters = method.getParameterTypes();
			
			if(parameters.length != 1 || annotation == null || !method.getReturnType().equals(void.class)) continue;
			
			Class<?> param = parameters[0];
			
			if(IEvent.class.isAssignableFrom(param))
			{
				@SuppressWarnings("unchecked")
				Class<? extends IEvent> realParam = (Class<? extends IEvent>) param;
				
				if(!this.bindings.containsKey(realParam))
				{
					this.bindings.put(realParam, new TreeSet<EventHandler>());
				}
				Collection<EventHandler> eventHandlersForEvent = this.bindings.get(realParam);
				Debug.log(Level.INFO, "Add listener method: " + method.getName() + " for event " + realParam.getSimpleName());
				eventHandlersForEvent.add(new EventHandler(listener, method, annotation));
			}
		}
	}
	
	/**
	 * Clears the list of registered Listeners and their Event methods
	 */
	public void clearListeners()
	{
		this.bindings.clear();
		this.registeredListeners.clear();
	}
	
	/**
	 * Removes an EventListener from the List
	 * 
	 * @param listener The EventListener to be removed
	 */
	public void removeListener(EventListener listener)
	{
		for(Entry<Class<? extends IEvent>, Collection<EventHandler>> ee : bindings.entrySet())
		{
			Iterator<EventHandler> it = ee.getValue().iterator();
			while(it.hasNext())
			{
				EventHandler curr = it.next();
				if(curr.getListener() == listener) it.remove();
			}
		}
		this.registeredListeners.remove(listener);
	}
	
	/**
	 * Getter for bindings
	 * 
	 * @return The binding Method Map
	 */
	public Map<Class<? extends IEvent>, Collection<EventHandler>> getBindings() 
	{
		return new HashMap<Class<? extends IEvent>, Collection<EventHandler>>(bindings);
	}
	
	/**
	 * Getter for the Set of registered Listeners
	 * 
	 * @return The Set of registered Listeners as HashSet
	 */
	public Set<EventListener> getRegisteredListeners()
	{
		return new HashSet<EventListener>(registeredListeners);
	}
	
}
