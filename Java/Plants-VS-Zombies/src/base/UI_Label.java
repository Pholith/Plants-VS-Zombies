package base;

import java.awt.Color;
import java.awt.Graphics2D;

public class UI_Label extends UI_Element {

	private String objectText;
	
	
	public UI_Label(Vector2 pos, String objectText, Color renderColor ,float renderScale) {
		super(pos, renderScale, renderColor);
		this.objectText = objectText;
	}
	
	public UI_Label(Vector2 pos, String objectText,  Color renderColor) {
		this(pos, objectText, renderColor, 1.0f);
	}

	public void setText(String text) {
		objectText = text;
	}
	
	@Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
	

		int screenUnit = Constant.screenPixelPerUnit;
		
		graphics.scale(getRenderScale(), getRenderScale());
		graphics.setColor(getRenderColor());
		graphics.drawString(objectText, getPosition().getX() * screenUnit ,getPosition().getY() *  screenUnit);
		graphics.scale(1f/getRenderScale(), 1f/getRenderScale());
	}
	
	
}
