package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import base.Constant;
import base.UI_Element;
import base.Vector2;

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
		float sc = getRenderScale();
		
		graphics.scale(sc, sc);
		graphics.setColor(getRenderColor());
		graphics.drawString(objectText, getPosition().getX() * screenUnit/sc ,getPosition().getY() *  screenUnit/sc);
		graphics.scale(1f/sc, 1f/sc);
	}
    @Override
    public String name() {return "UI_Label";}

	
}
