package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

import base.Sprite;
import base.Vector2;
import main.GameManager;

public class UI_PlantButton extends UI_Button{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7204627577293192451L;
	private final float reloadTime;
	private float reloadCounter;
	private boolean inReload;

	public UI_PlantButton(Vector2 pos, Sprite sprite, Consumer simpleFunction, float reloadTime) {
		super(pos, 1f, Color.white, sprite, simpleFunction);
		this.reloadTime = reloadTime;
		reloadCounter = 0;
		inReload = false;
	}



	public void selectPlant() {
		reloadCounter = 0;
		inReload = true;
	}

	@Override
	public void update() {

		if(!inReload) {

			super.update();
		}else {
			reloadCounter += GameManager.getInstance().getDeltatime();

			if(reloadCounter >= reloadTime) {
				inReload = false;
				reloadCounter = 0;
			}
		}
	}


	@Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
		RoundRectangle2D rectEffect;


		if (inReload) {
			rectEffect = new RoundRectangle2D.Float((float)getDrawRect().getX(), (float)getDrawRect().getY(),
					(float)getDrawRect().getWidth(),
					(float)getDrawRect().getHeight()* (1f - reloadCounter / reloadTime),
					10, 10);

			graphics.setColor(new Color(0.3f,0.3f,0.3f,0.6f) );
			graphics.draw(rectEffect);
			graphics.fill(rectEffect);

		}


		super.selfDisplay(CamPos, graphics);
	}

}
