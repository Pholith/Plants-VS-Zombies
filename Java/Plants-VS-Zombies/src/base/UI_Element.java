package base;

import java.awt.Color;
import java.awt.Graphics2D;

import main.RenderMode;

public abstract class UI_Element extends GameObject {


	
	private final Color renderColor;
	
	  private final float renderScale;
	  
	    public float getRenderScale() {
			return renderScale;
		}
	    

		
	protected Color getRenderColor() {
		return renderColor;
	}

	public UI_Element(Vector2 pos, float renderScale, Color renderColor) {
		super(pos, RenderMode.Self);
		this.renderColor = renderColor;
		this.renderScale = renderScale;
	}
	
	

}
