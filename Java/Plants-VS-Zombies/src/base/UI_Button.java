package base;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

import main.GameManager;

public class UI_Button extends UI_Element {
	
	//Sprite du boutton
	private Sprite sprite;
	
	//Taille du boutton à l'ecran, en pixels
	private final float rectWidth;
	private final float rectHeight;
	
	private final Consumer simpleFunction;
	
	private boolean pressed; 
	private boolean slected; 
	private Vector2 screenPosition;
    private RoundRectangle2D drawRect;
	
	
		public UI_Button(Vector2 pos, float renderScale, Color renderColor, float rectWidth, float rectHeight, Vector2 offset, Consumer simpleFunction) {
		super(pos, renderScale, renderColor, RenderMode.Both );
	
		this.simpleFunction = simpleFunction;
		
		this.rectHeight = rectWidth;
		this.rectWidth = rectWidth;
		this.sprite = null;
		///Calcul de positions :
		CalcPosition(offset);
		}
		
		
		
		
		public UI_Button(Vector2 pos, float renderScale, Color renderColor, Sprite sprite, Consumer simpleFunction) {
			super(pos, renderScale, renderColor, RenderMode.Both );
			
			this.simpleFunction = simpleFunction;
			
			
			if(sprite == null)
				sprite = GameManager.getResources().getErrorSprite();
			this.sprite = sprite;			
			this.rectWidth = Constant.screenPixelPerUnit * sprite.getWidth()/sprite.getPixelPerUnit();
			this.rectHeight =  Constant.screenPixelPerUnit * sprite.getHeight()/sprite.getPixelPerUnit();
	 		
			///Calcul de positions :
	 		CalcPosition(sprite.getAnchor());
		}
		
		void CalcPosition(Vector2 offset) {			

	    	Vector2 objPos = getPosition();   	

	    	offset = new Vector2( rectWidth*offset.getX() ,rectHeight*offset.getY());
			
	    	 screenPosition = new Vector2(
					objPos.getX()  * Constant.screenPixelPerUnit  - offset.getX(),
					objPos.getY()   * Constant.screenPixelPerUnit  - offset.getY());

	    	 drawRect = new RoundRectangle2D.Float((int)screenPosition.getX(), (int)screenPosition.getY(),
	                    rectWidth,
	                    rectHeight,
	                    10, 10);
		}

	    @Override
	    public Sprite display() {  
	    	return sprite;    	
	    }
		
	    @Override
	    public void update() {
	    	Point2D.Float mousePos = GameManager.getInstance().getClickLocation();
	    	
	    	if(mousePos != null && checkInside(mousePos.x, mousePos.y)) {
	    		if(!pressed) {    	
	    			simpleFunction.accept(null);	
	    			pressed = true;
	    			GameManager.getResources().setSelectedUi(this);
	    		}
	    	}else
	    		pressed = false;
	    	
	    	
	    	slected = GameManager.getResources().isSelectedUi(this);
	    }
	    	
	   
	    public boolean checkInside(float x, float y) {
	    	if(x >= screenPosition.getX() && x <= screenPosition.getX()+rectWidth && y >= screenPosition.getY() && y <= screenPosition.getY()+rectHeight)	    	
	    		return true;	    	
	    	return false;
	    }
	    
	    

		
		@Override
		public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
	
			if(sprite == null) {
				graphics.setColor(getRenderColor());
				graphics.draw(drawRect);
				graphics.fill(drawRect);
				//graphics.fillRect((int)screenPosition.getX(), (int)screenPosition.getY(),(int) rectWidth, (int)rectHeight);
			}
			
			
		if(slected) {
			graphics.setColor(new Color(127, 127, 127, 127));
			graphics.draw(drawRect);
			graphics.fill(drawRect);
		}
		}
		
		

	}