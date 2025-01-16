package engine.events.types;

import engine.events.Event;

public class KeyReleasedEvent extends KeyEvent {
	public KeyReleasedEvent(int keyCode) {
		super(Event.Type.KEY_RELEASED, keyCode);
	}
}
