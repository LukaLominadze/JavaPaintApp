package firstapplicaion.events.types;

import firstapplicaion.events.Event;

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
