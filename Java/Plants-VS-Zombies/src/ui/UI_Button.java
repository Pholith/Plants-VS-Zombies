package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

import base.Constant;
import base.Sprite;
import base.UI_Element;
import base.Vector2;
import enums.RenderMode;
import main.GameManager;

public class UI_Button extends UI_Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5576154544359578301L;

	//Sprite du boutton
	private final Sprite sprite;

	//Taille du boutton à l'ecran, en pixels
	private final float rectWidth;
	private final float rectHeight;

	@SuppressWarnings("rawtypes")
	private final Consumer simpleFunction;

	private boolean pressed; 
	private boolean selected; 
	private boolean disabled; 

	private boolean readyToUse;


	private Vector2 screenPosition;
	private Vector2 lastPosition;
	private RoundRectangle2D drawRect;
	protected RoundRectangle2D getDrawRect() {
		return drawRect;
	}
	private final Vector2 offset;


	public UI_Button(Vector2 pos, float renderScale, Color renderColor, float rectWidth, float rectHeight, Vector2 offset, Consumer simpleFunction, int layer) {
		super(pos, renderScale, renderColor, RenderMode.Both, layer );

		this.simpleFunction = simpleFunction;		
		this.rectHeight = rectHeight;
		this.rectWidth = rectWidth;
		this.sprite = null;		
		this.offset = offset = new Vector2( rectWidth*offset.getX() ,rectHeight*offset.getY());


		///Calcul de positions :
		CalcPosition();
	}




	public UI_Button(Vector2 pos, float renderScale, Color renderColor, float rectWidth, float rectHeight, Vector2 offset, Consumer simpleFunction) {
		this(pos, renderScale, renderColor,rectWidth,  rectHeight,offset,   simpleFunction, 93 );
	}

	public UI_Button(Vector2 pos, float renderScale, Color renderColor, Sprite sprite, Consumer simpleFunction) {
		super(pos, renderScale, renderColor, RenderMode.Both, 93 );

		this.simpleFunction = simpleFunction;


		if(sprite == null) {
			sprite = GameManager.getResources().getErrorSprite();
		}
		this.sprite = sprite;			
		this.rectWidth = Constant.screenPixelPerUnit * sprite.getWidth()/sprite.getPixelPerUnit();
		this.rectHeight =  Constant.screenPixelPerUnit * sprite.getHeight()/sprite.getPixelPerUnit();

		offset = new Vector2( rectWidth*sprite.getAnchor().getX() ,rectHeight*sprite.getAnchor().getY());

		///Calcul de positions :
		CalcPosition();
	}

	@Override
	public String name() {return "UI_Button";}


	void CalcPosition() {			
		lastPosition = getPosition();



		screenPosition = new Vector2(
				lastPosition.getX()   * Constant.screenPixelPerUnit  - offset.getX(),
				lastPosition.getY()   * Constant.screenPixelPerUnit  - offset.getY());

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

		if(disabled) {
			return;
		}

		if(!readyToUse) {
			readyToUse = GameManager.getResources().isSelectedUi(null);
			return;
		}
		if (!isVisible()) { return; }

		Point2D.Float mousePos = GameManager.getInstance().getClickLocation();


		if(!lastPosition.equals(getPosition())) {
			CalcPosition();
		}

		if(mousePos != null && checkInside(mousePos.x, mousePos.y)) {
			if(!pressed) {    	
				click();
			}
		} else {
			pressed = false;
		}


		selected = GameManager.getResources().isSelectedUi(this);



	}

	public void click() {
		simpleFunction.accept(null);	
		pressed = true;
		onClick();
		GameManager.getResources().setSelectedUi(this);
	}

	public boolean checkInside(float x, float y) {
		if(x >= screenPosition.getX() && x <= screenPosition.getX()+rectWidth && y >= screenPosition.getY() && y <= screenPosition.getY()+rectHeight) {
			return true;
		}	    	
		return false;
	}

	public void setDisable(boolean disabled) {
		this.disabled = disabled;
	}


	void onClick() {}

	@Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {

		if (!isVisible()) {
			return;
		}
		if(sprite == null) {
			graphics.setColor(getRenderColor());
			graphics.draw(drawRect);
			graphics.fill(drawRect);
			//graphics.fillRect((int)screenPosition.getX(), (int)screenPosition.getY(),(int) rectWidth, (int)rectHeight);
		}


		if(selected) {
			graphics.setColor(new Color(127, 127, 127, 127));
			graphics.draw(drawRect);
			graphics.fill(drawRect);			
		}		
		if(disabled) {
			graphics.setColor(new Color(100, 100, 100, 127));
			graphics.draw(drawRect);
			graphics.fill(drawRect);			
		}

	}



}