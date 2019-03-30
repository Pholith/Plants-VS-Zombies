package base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.Consumer;

public class UI_Button extends UI_Element {
	
	private final Sprite sprite;
	final Consumer simpleFunction;
	
		public UI_Button(Vector2 pos, float renderScale, Color renderColor, Sprite sprite, Consumer simpleFunction) {
		super(pos, renderScale, renderColor, RenderMode.Sprite );
		this.sprite = sprite;
		this.simpleFunction = simpleFunction;
		}
		

	    @Override
	    public Sprite display() {  
	    	return sprite;    	
	    }
		
	    @Override
	    public void update() {
	    	simpleFunction.accept(null);
	    }
		
		/*
		@Override
		public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
		
			int screenUnit = Constant.screenPixelPerUnit;	
			
			graphics.scale(getRenderScale(), getRenderScale());
			graphics.setColor(getRenderColor());
			graphics.drawString(objectText, getPosition().getX() * screenUnit ,getPosition().getY() *  screenUnit);
			graphics.scale(1f/getRenderScale(), 1f/getRenderScale());
		}*/
		
		

	}