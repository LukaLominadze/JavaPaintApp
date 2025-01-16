package engine.events;

public class Event {
	
	public enum Type {
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_MOVED,
		KEY_PRESSED,
		KEY_DOWN,
		KEY_RELEASED,
		COLOR_PICKED,
		ERASE_DRAWING
	}
	
	private Type type;
	public boolean handled;
	
	protected Event(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
}
