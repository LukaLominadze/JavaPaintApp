package engine.events.types;

import engine.events.Event;

public class KeyPressedEvent extends KeyEvent {
	public KeyPressedEvent(int keyCode) {
		super(Event.Type.KEY_PRESSED, keyCode);
	}
}
