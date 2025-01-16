package tools;

import engine.events.EventListener;
import layers.ToolSwatchLayer;

public class PencilToolSwatch extends ToolSwatchLayer {
	
	public PencilToolSwatch(EventListener listener) {
		super("src/resources/pencil.png", 60, 0, 60, 60, listener);
	}
	
}
