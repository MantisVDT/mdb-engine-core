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

public class EventExecutor {
	
	private final Map<Class<? extends IEvent>, Collection<EventHandler>> bindings;
	private final Set<EventListener> registeredListeners;
	
	public EventExecutor()
	{
		this.bindings = new HashMap<Class<? extends IEvent>, Collection<EventHandler>>();
		this.registeredListeners = new HashSet<EventListener>();
	}
	
	public List<EventHandler> getListenersFor(Class<? extends IEvent> clazz)
	{
		if(!this.bindings.containsKey(clazz)) return new ArrayList<EventHandler>();
		return new ArrayList<EventHandler>(this.bindings.get(clazz));
	}
	
	public <T extends IEvent> T executeEvent(T event)
	{
		Collection<EventHandler> handlers = this.bindings.get(event.getClass());
		if(handlers == null)
		{
			return event;
		}
		
		for(EventHandler handler : handlers)
		{
			handler.execute(event);
		}
		return event;
	}
	
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
			if(annotation == null) continue;
			
			Class<?>[] parameters = method.getParameterTypes();
			if(parameters.length != 1) continue;
			
			Class<?> param = parameters[0];
			
			if(!method.getReturnType().equals(void.class))
			{
				Debug.log(Level.WARNING, "Ignoring method due to non-void return");
				continue;
			}
			
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
				eventHandlersForEvent.add(createEventHandler(listener, method, annotation));
			}
		}
	}
	
	private EventHandler createEventHandler(final EventListener listener, final Method method, final Event annotation)
	{
		return new EventHandler(listener, method, annotation);
	}
	
	public void clearListeners()
	{
		this.bindings.clear();
		this.registeredListeners.clear();
	}
	
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
	
	public Map<Class<? extends IEvent>, Collection<EventHandler>> getBindings() 
	{
		return new HashMap<Class<? extends IEvent>, Collection<EventHandler>>(bindings);
	}
	
	public Set<EventListener> getRegisteredListeners()
	{
		return new HashSet<EventListener>(registeredListeners);
	}
	
}
