package layers;

import java.awt.Color;

import firstapplicaion.layers.Layer;

public class ToolLayer extends Layer {
	
	@SuppressWarnings("unused")
	protected Color drawColor;
	
	public ToolLayer() {
		drawColor = Color.BLACK;
	}
	
	public void setDrawColor(Color color) {
        drawColor = color;
    }
    
    public void erase() {
	}
}
