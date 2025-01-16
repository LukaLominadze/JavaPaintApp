package tools;

import engine.events.EventListener;
import layers.ToolSwatchLayer;

public class RectToolSwatch extends ToolSwatchLayer {
	
	public RectToolSwatch(EventListener listener) {
		super("src/resources/rect.png", 0, 0, 60, 60, listener);
	}
	
}
