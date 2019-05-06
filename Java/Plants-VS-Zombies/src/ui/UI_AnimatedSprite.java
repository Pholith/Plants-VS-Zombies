package ui;

import base.GameObject;
import base.Sprite;
import base.Vector2;
import main.GameManager;

public class UI_AnimatedSprite extends GameObject {
	
	private Sprite[] animationSprite;
	private long lastFrameUpdate;
	private long timeLoopAnimation;
	private boolean destroyAfter;
	
	public UI_AnimatedSprite(Vector2 pos, String animation, float speed, boolean destroyAfter) {
		super(pos);
		this.destroyAfter = destroyAfter;
		animationSprite = GameManager.getResources().getAnimationByPath(animation);
		lastFrameUpdate = GameManager.getInstance().getClockMillis();
		
		if(speed < 0.01)
			speed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/speed*5f);
		
	}
	

    @Override
    public Sprite display() {  
    	int finalId = 0;
    	
    	if(animationSprite.length == 1) {
    		return animationSprite[0];
    		
    	} else if (animationSprite.length > 1) {
    		long delta = GameManager.getInstance().getClockMillis() - lastFrameUpdate;

    		float realTimeLoop =  timeLoopAnimation /GameManager.getInstance().getTimeScale();
    		
    		if (delta >=  realTimeLoop) {
    			lastFrameUpdate = GameManager.getInstance().getClockMillis();
    		}
    		
    		finalId = (int)(((float) delta/realTimeLoop)*animationSprite.length);
  
    		if(destroyAfter && finalId >= animationSprite.length ) {
    			destroy();
    		return null;	
    		}
    			
    		

    		return animationSprite [finalId %animationSprite.length];
    	}
    	 	
    	
  	   return null;
    }
    
	
}
