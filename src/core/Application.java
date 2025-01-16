package core;

import java.awt.Color;

import events.ColorPickedEvent;
import firstapplicaion.core.Window;
import firstapplicaion.events.Event;
import firstapplicaion.layers.Layer;
import layers.ColorSwatchLayer;
import layers.DrawLayer;
import layers.EraseSwatchLayer;
import layers.StaticRectLayer;

public class Application {
	
	private Window window;
	
	public Application(String title, int width, int height) {
		window = new Window(title, width, height);
		Color[] colors = {
				Color.BLACK,
				Color.DARK_GRAY,
				Color.BLUE,
				Color.CYAN,
				Color.GREEN,
				Color.MAGENTA,
				Color.RED,
				Color.YELLOW
		};
		int startPos = (width - (colors.length + 1) * 60) / 2;
		window.addLayer(new StaticRectLayer(0, 0, width, 60, new Color(200, 200, 200)));
		window.addLayer(new DrawLayer());
		window.addLayer(new EraseSwatchLayer(startPos - 30, 0, 60, 60, (Event e) -> onEraseDrawingEvent(e)));
		startPos += 30;
		for (int i = 0; i < colors.length; i++) {
			window.addLayer(new ColorSwatchLayer(startPos + i * 60, 0, 60, 60, colors[i], (Event e) -> onColorPickEvent(e)));
		}
	}
	
	public void run() {
		window.run();
	}
	
	public void onColorPickEvent(Event e) {
		window.getLayer(DrawLayer.class).setDrawColor(((ColorPickedEvent)e).getColor());
		System.out.println("Set Color! -> " + ((ColorPickedEvent)e).getColor());
	}
	
	public void onEraseDrawingEvent(Event e) {
		window.getLayer(DrawLayer.class).erase();
	}
	
	public void addLayer(Layer layer) {
		window.addLayer(layer);
	}
	
}
