package engine.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
public class Screen extends Canvas {

	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	
	public Screen(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void init() {
		createBufferStrategy(3);
	}
	
	public void beginRendering() {
		bufferStrategy = getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
	}
	
	public void clear() {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Graphics getGraphicsObject() {
		return graphics;
	}
	
	public void endRendering() {
		graphics.dispose();
		bufferStrategy.show();
	}
}
