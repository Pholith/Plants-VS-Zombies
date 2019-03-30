package base;

import java.awt.Color;
import java.awt.Graphics2D;

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
		this( pos,  renderScale,  renderColor, RenderMode.Self );
	}
	
	public UI_Element(Vector2 pos, float renderScale, Color renderColor, RenderMode rendMode) {
		super(pos, rendMode);
		this.renderColor = renderColor;
		this.renderScale = renderScale;
	}
	
	

}
