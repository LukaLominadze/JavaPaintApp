package firstapplicaion.core;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import firstapplicaion.events.Event;
import firstapplicaion.events.types.KeyDownEvent;
import firstapplicaion.events.types.KeyPressedEvent;
import firstapplicaion.events.types.KeyReleasedEvent;
import firstapplicaion.events.types.MouseMovedEvent;
import firstapplicaion.events.types.MousePressedEvent;
import firstapplicaion.events.types.MouseReleasedEvent;
import firstapplicaion.layers.Layer;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Screen screen;
	private List<Layer> layerStack = new ArrayList<Layer>();
	
	private int lastKey = KeyEvent.VK_UNDEFINED;
	
	private double timestep = 0.0;
	
	public Window(String name, int width, int height) {
		screen = new Screen(width, height);
		
		setTitle(name);
		add(screen);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		screen.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
			
			public void mousePressed(MouseEvent e) {
				MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
			
		});
		
		screen.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseMoved(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
				onEvent(event);
			}
			
			public void mouseDragged(MouseEvent e) {
				MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
				onEvent(event);
			}
		});
		
		screen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == lastKey) {
					KeyDownEvent event = new KeyDownEvent(keyCode);
					onEvent(event);
				}
				else {
					KeyPressedEvent event = new KeyPressedEvent(keyCode);
					onEvent(event);
					lastKey = keyCode;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				KeyReleasedEvent event = new KeyReleasedEvent(keyCode);
				onEvent(event);
				if (keyCode == lastKey) {
					lastKey = KeyEvent.VK_UNDEFINED;
				}
			}
			
		});
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            	int width = getWidth();
                int height = getHeight();
                onWindowResize(width, height);
            }
        });
		
		screen.init();
	}
	
	public void run() {
		double beginTime = System.nanoTime() / 1e9;
		
		onUpdate(timestep);
		screen.beginRendering();
		screen.clear();
		onRender(screen.getGraphicsObject());
		screen.endRendering();
		try {
			Thread.sleep(16);
		}
		catch (InterruptedException e) {
			
		}
		
		double endTime=System.nanoTime()/1e9;
		
		timestep = (float) (endTime - beginTime);
		
		SwingUtilities.invokeLater(() -> run());
	}
	
	public void onWindowResize(int width, int height) {
		
	}
	
	public void onEvent(Event event) {
		for (int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).onEvent(event);
		}
	}
	
	private void onUpdate(double timestep) {
		for (int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.get(i).onUpdate(timestep);
		}
	}
	
	private void onRender(Graphics g) {
		for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).onRender(g);
		}
	}
	
	public void addLayer(Layer layer) {
		System.out.println(layer);
		layerStack.add(layer);
		layer.onAttach();
	}
	
	public Layer getLayer(int index) {
		return layerStack.get(index);
	}
	
	public <T extends Layer> T getLayer(Class<T> layerType) {
		for (int i = 0; i < layerStack.size(); i++) {
			Layer currentLayer = layerStack.get(i);
			if (layerType.isInstance(currentLayer)) {
				return layerType.cast(currentLayer);
			}
		}
		return null;
	}
	
	@SuppressWarnings("exports")
	public Graphics getGraphicsObject() {
		return screen.getGraphicsObject();
	}
	
}
