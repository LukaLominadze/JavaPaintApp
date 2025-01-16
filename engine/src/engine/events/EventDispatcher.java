package engine.events;

public class EventDispatcher {

	private Event event;
	
	public EventDispatcher(Event event) {
		this.event = event;
	}
	
	public void dispatch(Event.Type type, EventHandler eventHandler) {
		if (event.handled)
			return;
		
		if (event.getType() == type) {
			event.handled = eventHandler.onEvent(event);
		}
	}
	
}
