package events;

import java.awt.Color;

import firstapplicaion.events.Event;

public class ColorPickedEvent extends Event {
	
	private Color color;
	
	public ColorPickedEvent(Color color) {
		super(Type.COLOR_PICKED);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

}
