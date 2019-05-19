package base;

import java.awt.Color;
import enums.RenderMode;

public abstract class UI_Element extends GameObject {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2886098452385486133L;

	private Color renderColor;
	
	private final float renderScale;
	
	private boolean visible = true;
	public boolean isVisible() {
		return visible;
	}
	public void hide() {
		visible = false;
	}
	public void show() {
		visible = true;
	}
	
	
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
		super(pos, rendMode, 90);
		this.renderColor = renderColor;
		this.renderScale = renderScale;
	}
	
    @Override
    public String name() {return "UI_Element";}

	

}
