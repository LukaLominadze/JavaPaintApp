package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.types.MouseMovedEvent;
import engine.events.types.MousePressedEvent;
import engine.events.types.MouseReleasedEvent;

public class PencilToolLayer extends ToolLayer {
    
    private List<List<Point>> lineSegments = new ArrayList<>(); 
    private List<Color> lineColors = new ArrayList<Color>();
    private List<Point> currentLine; 
    
    private boolean drawing = false;
    
    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) event));
        dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) event));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) event));
    }
    
    public void onRender(Graphics g) {
        g.setColor(drawColor);
        int j = 0;
        for (List<Point> line : lineSegments) {
        	g.setColor(lineColors.get(j));
            for (int i = 1; i < line.size(); i++) {
                Point p1 = line.get(i - 1);
                Point p2 = line.get(i);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            j++;
        }
    }
    
    private boolean onMousePressed(MousePressedEvent event) {
        drawing = true;
        currentLine = new ArrayList<>();
        currentLine.add(new Point(event.getX(), event.getY()));
        return false;
    }
    
    private boolean onMouseMoved(MouseMovedEvent event) {
        if (drawing) {
            currentLine.add(new Point(event.getX(), event.getY()));
            return true;
        }
        return false;
    }
    
    private boolean onMouseReleased(MouseReleasedEvent event) {
        if (drawing) {
            currentLine.add(new Point(event.getX(), event.getY()));
            lineSegments.add(currentLine);
            lineColors.add(drawColor);
            currentLine = null; 
            drawing = false;
            return true;
        }
        return false;
    }
    
    public void erase() {
        lineSegments.clear();
        lineColors.clear();
    }
}