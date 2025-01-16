package layers;

import java.awt.Color;
import java.awt.Rectangle;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.EventListener;
import engine.events.EventObservable;
import engine.events.types.MousePressedEvent;
import engine.events.types.MouseReleasedEvent;
import engine.layers.Layer;

public class SwatchLayer extends Layer {
	
	protected Rectangle box;
	protected EventObservable eventObservable;
	protected Color color;
	
	protected SwatchLayer(int x, int y, int width, int height, Color color, EventListener listener) {
		box = new Rectangle(x, y, width, height);
		eventObservable = new EventObservable();
		eventObservable.AddListener(listener);
		this.color = color;
	}
	
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)event));
	}
	
	public boolean onMousePressed(MousePressedEvent event) {
		if (box.contains(event.getX(), event.getY())) {
			return true;
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		return false;
	}
	
}
