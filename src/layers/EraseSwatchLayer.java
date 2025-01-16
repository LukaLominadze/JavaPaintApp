package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import events.EraseDrawingEvent;
import engine.events.EventListener;
import engine.events.types.MouseReleasedEvent;

public class EraseSwatchLayer extends SwatchLayer {
	
	public EraseSwatchLayer(int x, int y, int width, int height, EventListener listener) {
		super(x, y, width, height, Color.WHITE, listener);
	}
	
	public void onRender(Graphics g) {
		g.setColor(color);
		g.fillRect(box.x, box.y, box.width, box.height);
		g.setColor(Color.RED);
		g.drawLine(box.x, box.y, box.x + box.width, box.y + box.height);
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			if (box.contains(event.getX(), event.getY())) {
				eventObservable.Invoke(new EraseDrawingEvent());
				System.out.println("Erasing!");
				return true;				
			}
		}
		return false;
	}
	
}
