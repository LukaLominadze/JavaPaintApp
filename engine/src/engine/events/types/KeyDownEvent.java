package engine.events.types;

import engine.events.Event;

public class KeyDownEvent extends KeyEvent {

	public KeyDownEvent(int keyCode) {
		super(Event.Type.KEY_DOWN, keyCode);
	}
	
}
