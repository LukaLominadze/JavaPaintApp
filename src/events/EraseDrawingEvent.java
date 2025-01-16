package events;

import engine.events.Event;

public class EraseDrawingEvent extends Event {

	public EraseDrawingEvent() {
		super(Type.ERASE_DRAWING);
	}

}
