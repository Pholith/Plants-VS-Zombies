package base;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class UI_Element extends GameObject {


	private final Color renderColor;
	
	

		
	protected Color getRenderColor() {
		return renderColor;
	}

	public UI_Element(Vector2 pos, float renderScale, Color renderColor) {
		super(pos,  renderScale, "neutral");
		this.renderColor = renderColor;
	}
	
	
	public abstract void selfDisplay(Vector2 CamPos, Graphics2D graphics);
	
}
