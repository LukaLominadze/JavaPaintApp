package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import events.ColorPickedEvent;
import engine.events.EventListener;
import engine.events.types.MouseReleasedEvent;

public class ColorSwatchLayer extends SwatchLayer {
	
	public ColorSwatchLayer(int x, int y, int width, int height, Color color, EventListener listener) {
		super(x, y, width, height, color, listener);
	}
	
	public void onRender(Graphics g) {
		g.setColor(color);
		g.fillRect(box.x, box.y, box.width, box.height);
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			if (box.contains(event.getX(), event.getY())) {
				eventObservable.Invoke(new ColorPickedEvent(color));
				System.out.println("Sent color! -> " + color);
				return true;				
			}
		}
		return false;
	}
	
}
