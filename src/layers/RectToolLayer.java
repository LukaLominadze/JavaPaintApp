package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.types.MouseMovedEvent;
import engine.events.types.MousePressedEvent;
import engine.events.types.MouseReleasedEvent;

public class RectToolLayer extends ToolLayer {

	private List<RectLayer> rectStack = new ArrayList<RectLayer>();
	
	private int x1, y1, x2, y2;
	
	private boolean drawing = false;
	
	public void onAttach() {
		
	}
	
	public void onEvent(Event event) {
		if (!drawing) {
			for (int i = rectStack.size() - 1; i >= 0; i--) {
				rectStack.get(i).onEvent(event);
			}
		}
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent)event));
	}
	
	public void onUpdate(double timestep) {
		
	}
	
	public void onRender(Graphics g) {
		for (int i = 0; i < rectStack.size(); i++) {
			rectStack.get(i).onRender(g);
		}
		if (!drawing) {
			return;
		}
		g.setColor(Color.gray);
		if (Math.abs(x2 - x1) > 0 && Math.abs(y2 - y1) > 0 && x1 >= 0 && x2 >= 0) {
			g.setColor(Color.gray);
			int width = x2 - x1; int height = y2 - y1;
			int startPosX = x1; int startPosY = y1;
			if (x2 < x1) {
				width = x1 - x2;
				startPosX = x2;
			}
			if (y2 < y1) {
				height = y1 - y2;
				startPosY = y2;
			}
			g.drawRect(startPosX, startPosY, width, height);
		}
	}
	
	public boolean onMousePressed(MousePressedEvent event) {
		drawing = true;
		x1 = event.getX();
		y1 = event.getY();
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent event) {
		x2 = event.getX();
		y2 = event.getY();
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		if (Math.abs(x2 - x1) > 0 && Math.abs(y2 - y1) > 0 && x1 >= 0 && x2 >= 0) {
			int width = x2 - x1; int height = y2 - y1;
			int startPosX = x1; int startPosY = y1;
			if (x2 < x1) {
				width = x1 - x2;
				startPosX = x2;
			}
			if (y2 < y1) {
				height = y1 - y2;
				startPosY = y2;
			}
			RectLayer rect = new RectLayer(startPosX, startPosY, width, height, drawColor);
			rectStack.add(rect);
			rect.onAttach();
			x1 = -1; y1 = -1;
			x2 = -1; y2 = -1;
		}
		drawing = false;
		return false;
	}

	public void erase() {
		rectStack.clear();
	}
	
}
