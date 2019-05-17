package base;

import java.awt.Color;

import enums.RenderMode;

public abstract class UI_Element extends GameObject {


	
	private Color renderColor;
	
	  private final float renderScale;
	  
	    public float getRenderScale() {
			return renderScale;
		}
	    

	 public void setRenderColor(Color renderColor) {
		this.renderColor = renderColor;
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
    @Override
    public String name() {return "UI_Element";}

	

}
