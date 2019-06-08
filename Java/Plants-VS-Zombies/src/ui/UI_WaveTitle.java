package ui;

import java.awt.Color;

import base.Constant;
import base.Vector2;
import main.GameManager;

public class UI_WaveTitle extends UI_Label {

	
	private float counter = 0;
	private float centerY;
	private final float coefSpeed;
	
	
	public UI_WaveTitle(String text) {
		super(new Vector2(-0.25f*text.length() + (GameManager.getInstance().getResolutionX() /Constant.screenPixelPerUnit)/2f, -2f)
				, text, Color.white, 10f);

		coefSpeed = 18f;
		
		Vector2 pos = getPosition();
		
		new UI_WaveTitle(text, pos);//permet d'instancier une ombre privée
	}
	
	
	private  UI_WaveTitle(String text, Vector2 basePos) {
		super(basePos.add(Vector2.one().multiply(0.1f))
				, text, Color.black, 10f, 93);	
		
		coefSpeed = 16f;
		
	}
	
	@Override
	public void start() {
	centerY = (GameManager.getInstance().getResolutionY() /Constant.screenPixelPerUnit)/2f;
	}
	
	@Override
	public void update() {
		
		
		counter += GameManager.getInstance().getDeltatime();
		
		
		if(counter < 3f) {
			translation(new Vector2(0, Math.abs(centerY - getPosition().getY())/coefSpeed) );
				}else {

					translation(new Vector2(0, Math.abs( (centerY-0.1f) - getPosition().getY())/coefSpeed) );
			
		if(counter > 5f) {
			destroy();			
		}
		}
		
		
		super.update();
	}
	

}
