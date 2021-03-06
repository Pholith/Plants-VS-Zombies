package ui;

import java.awt.Color;
import java.util.function.Consumer;

import base.Sprite;
import base.Vector2;
import main.GameManager;




public class UI_Sun extends UI_Button {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6011136737444258832L;
	private Vector2 velocity;
	private float yPositionMin;
	private Vector2 startVel;

	public UI_Sun(Vector2 pos, Consumer simpleFunction, Sprite sprite) {
		super(pos, 1f, Color.white, sprite, simpleFunction);
	}
	public UI_Sun(Vector2 pos, Consumer simpleFunction) {
		this(pos, simpleFunction, GameManager.getResources().getAnimationByPath("particles/sun.png")[0]);
	}

	@Override
	public void start() {
		startVel =  new Vector2((float)Math.random()*0.5f - 0.25f, 1f + (float)Math.random()/2f);	
		velocity = startVel;

		yPositionMin = getPosition().getY() + (float)Math.random();
	}

	@Override
	protected int getLayer() { return 800; }

	@Override
	public String name() {return "UI_Sun";}


	@Override
	public void update() {

		translation(velocity.getX()*0.03f, -velocity.getY() * 0.03f);

		velocity = new Vector2(velocity.getX(), velocity.getY() - GameManager.getInstance().getDeltatime()*3f);

		if(getPosition().getY() >= yPositionMin) {
			startVel =  new Vector2(startVel.getX()*0.5f, startVel.getY()*0.75f);
			velocity = startVel;


			if(GameManager.getInstance().isDebugMode()) {
				click();
			}

		}



		super.update();
	}



	@Override
	protected void onClick() {
		destroy();
	}

}
