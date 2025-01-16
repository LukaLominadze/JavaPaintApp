package layers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import engine.events.*;
import engine.layers.Layer;
import tools.PencilToolSwatch;
import tools.RectToolSwatch;

public class DrawLayer extends Layer {
	
	private List<ToolSwatchLayer> toolSwatches = new ArrayList<ToolSwatchLayer>();
	private List<ToolLayer> tools = new ArrayList<ToolLayer>();
	
	private ToolLayer currentTool = null;
	
	public DrawLayer() {
		toolSwatches.add(new RectToolSwatch((Event e) -> onRectPicked(e)));
		toolSwatches.add(new PencilToolSwatch((Event e) -> onPencilPicked(e)));
		tools.add(new RectToolLayer());
		tools.add(new PencilToolLayer());
		currentTool = tools.get(1);
	}
	
	public void onEvent(Event event) {
		for (int i = toolSwatches.size() - 1; i >= 0; i--) {
			toolSwatches.get(i).onEvent(event);
		}
		currentTool.onEvent(event);
	}
	
	public void onRender(Graphics g) {
		for (Layer toolSwatch : toolSwatches) {
			toolSwatch.onRender(g);
		}
		for (ToolLayer tool : tools) {
			tool.onRender(g);
		}
	}
	
	public void onPencilPicked(Event e) {
		currentTool = tools.get(1);
	}
	
	public void onRectPicked(Event e) {
		currentTool = tools.get(0);
	}
	
	public void setDrawColor(Color color) {
		for (ToolLayer tool : tools) {
			tool.setDrawColor(color);
			System.out.println("SETTING COLOR!");
		}
	}
	
	public void erase() {
		for (ToolLayer tool : tools) {
			tool.erase();
		}
	}
}
