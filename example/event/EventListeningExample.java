package event;

import de.mdb.engine.core.event.Event;
import de.mdb.engine.core.event.EventListener;
import de.mdb.engine.core.event.EventManager;
import de.mdb.engine.core.input.events.KeyReleasedEvent;
import de.mdb.engine.core.logger.Debug;

public class EventListeningExample implements EventListener{
	
	public EventListeningExample()
	{
		EventManager.registerListener(this);
	}
	
	@Event
	public void onKeyReleased(KeyReleasedEvent e)
	{
		Debug.info(e.getKeyCode());
	}
	
}
