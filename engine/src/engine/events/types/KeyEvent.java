package engine.events.types;

import engine.events.Event;

public class KeyEvent extends Event {
	private int keyCode;
	
	public KeyEvent(Event.Type type, int keyCode) {
		super(type);
		this.keyCode = keyCode;
	}
	
	public int getKeyCode() {
		return keyCode;
	}
}
