package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.types.MouseMovedEvent;
import engine.events.types.MousePressedEvent;
import engine.events.types.MouseReleasedEvent;
import engine.layers.Layer;

public class RectLayer extends Layer {
	
	private Rectangle box;
	private Color color;
	
	private int dx;
	private int dy;
	
	private boolean dragging = false;
	
	public RectLayer(int x, int y, int width, int height, Color color) {
		box = new Rectangle(x, y, width, height);
		this.color = color;
	}
	
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent)event));
	}
	
	public void onUpdate(double timestep) {
		
	}
	
	public void onRender(Graphics g) {
		g.setColor(color);
		g.fillRect(box.x, box.y, box.width, box.height);
	}
	
	public boolean onMousePressed(MousePressedEvent event) {
		if (box.contains(event.getX(), event.getY())) {
			dx = event.getX();
			dy = event.getY();
			dragging = true;
		}
		return dragging;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		dragging = false;
		return dragging;
	}
	
	public boolean onMouseMoved(MouseMovedEvent event) {
		if (event.getDragged() && dragging) {
			int x = event.getX();
			int y = event.getY();
			
			box.x += x - dx;
			box.y += y - dy;
			
			dx = x;
			dy = y;
		}
		return dragging;
	}
	
}
