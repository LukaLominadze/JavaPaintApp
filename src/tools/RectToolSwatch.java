package tools;

import firstapplicaion.events.EventListener;
import layers.ToolSwatchLayer;

public class RectToolSwatch extends ToolSwatchLayer {
	
	public RectToolSwatch(EventListener listener) {
		super("src/resources/rect.png", 0, 0, 60, 60, listener);
	}
	
}
