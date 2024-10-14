package layers;

import firstapplicaion.events.Event;
import firstapplicaion.events.EventDispatcher;
import firstapplicaion.events.types.KeyDownEvent;
import firstapplicaion.events.types.KeyPressedEvent;
import firstapplicaion.events.types.KeyReleasedEvent;
import firstapplicaion.events.types.MouseMovedEvent;
import firstapplicaion.events.types.MousePressedEvent;
import firstapplicaion.events.types.MouseReleasedEvent;
import firstapplicaion.layers.Layer;

public class ExampleLayer extends Layer {
	
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.KEY_PRESSED, (Event e) -> onKeyPressed((KeyPressedEvent)event));
		dispatcher.dispatch(Event.Type.KEY_DOWN, (Event e) -> onKeyDown((KeyDownEvent)event));
		dispatcher.dispatch(Event.Type.KEY_RELEASED, (Event e) -> onKeyReleased((KeyReleasedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent)event));
	}
	
	public boolean onKeyPressed(KeyPressedEvent event) {
		System.out.println("Key Pressed -> " + event.getKeyCode());
		return false;
	}
	
	public boolean onKeyDown(KeyDownEvent event) {
		System.out.println("Key Down -> " + event.getKeyCode());
		return false;
	}

	public boolean onKeyReleased(KeyReleasedEvent event) {
		System.out.println("Key Released -> " + event.getKeyCode());
		return false;
	}
	
	public boolean onMousePressed(MousePressedEvent event) {
		System.out.println("Mouse Pressed -> " + event.getButton());
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		System.out.println("Mouse Released -> " + event.getButton());
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent event) {
		System.out.println("Mouse Moved -> " + event.getX() + ", " + event.getY() + " Dragged -> " + event.getDragged());
		return false;
	}
	
}
