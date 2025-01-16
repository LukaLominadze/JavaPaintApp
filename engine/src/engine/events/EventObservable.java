package engine.events;

import java.util.ArrayList;
import java.util.List;

public class EventObservable {
	
	private List<EventListener> listeners = new ArrayList<EventListener>();
	
	public void Invoke(Event e) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).onEvent(e);
		}
	}
	
	public void AddListener(EventListener listener) {
		listeners.add(listener);
	}
	
	public void RemoveListener(EventListener listener) {
		listeners.remove(listener);
	}
	
}
