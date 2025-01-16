package layers;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import firstapplicaion.events.Event;
import firstapplicaion.events.EventDispatcher;
import firstapplicaion.events.EventListener;
import firstapplicaion.events.EventObservable;
import firstapplicaion.events.types.MousePressedEvent;
import firstapplicaion.events.types.MouseReleasedEvent;
import firstapplicaion.layers.Layer;

public class ToolSwatchLayer extends Layer {
	
	private BufferedImage textureImage;
	private Rectangle textureRect;
	private EventObservable eventObservable;
	
	public ToolSwatchLayer(String textureFilePath, int x, int y, int width, int height, EventListener listener) {
		textureRect = new Rectangle(x, y, width, height);
		eventObservable = new EventObservable();
		eventObservable.AddListener(listener);
		try {
            textureImage = ImageIO.read(new File(textureFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent)event));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent)event));
	}
	
	public void onRender(Graphics g) {
		Rectangle rect = textureRect;
		g.drawImage(textureImage, rect.x, rect.y, rect.width, rect.height, null);
	}
	
	public boolean onMousePressed(MousePressedEvent event) {
		if (textureRect.contains(event.getX(), event.getY())) {
			return true;
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			if (textureRect.contains(event.getX(), event.getY())) {
				eventObservable.Invoke(event);
				System.out.println("Set tool! -> ");
				return true;				
			}
		}
		return false;
	}
}
