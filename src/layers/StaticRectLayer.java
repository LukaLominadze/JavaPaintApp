package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.layers.Layer;

public class StaticRectLayer extends Layer {
	
	private Rectangle box;
	private Color color;

	public StaticRectLayer(int x, int y, int width, int height, Color color) {
		box = new Rectangle(x, y, width, height);
		this.color = color;
	}
	
	public void onUpdate(double timestep) {
		
	}
	
	public void onRender(Graphics g) {
		g.setColor(color);
		g.fillRect(box.x, box.y, box.width, box.height);
	}
	
}
