package firstapplicaion.events.types;

import firstapplicaion.events.Event;

public class KeyDownEvent extends KeyEvent {

	public KeyDownEvent(int keyCode) {
		super(Event.Type.KEY_DOWN, keyCode);
	}
	
}
