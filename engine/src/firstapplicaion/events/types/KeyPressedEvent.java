package firstapplicaion.events.types;

import firstapplicaion.events.Event;

public class KeyPressedEvent extends KeyEvent {
	public KeyPressedEvent(int keyCode) {
		super(Event.Type.KEY_PRESSED, keyCode);
	}
}
