package firstapplicaion.events.types;

import firstapplicaion.events.Event;

public class KeyReleasedEvent extends KeyEvent {
	public KeyReleasedEvent(int keyCode) {
		super(Event.Type.KEY_RELEASED, keyCode);
	}
}
