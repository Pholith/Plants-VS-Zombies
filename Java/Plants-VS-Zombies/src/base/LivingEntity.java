package base;

import java.util.ArrayList;

import main.GameManager;

/**
 * 
 */
public abstract class LivingEntity extends GameObject {

  
	private final Sprite[] animationSprite;
	private int actFrame;
	private final long timeLoopAnimation;
    private long lastFrameUpdate;
    

	public LivingEntity(int health, Vector2 position, Sprite[] animationSprite, float animSpeed, String tag) {
		super(position, tag);
		this.health = health;
		this.animationSprite = animationSprite;
		
		if(animSpeed < 0.01)
			animSpeed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/animSpeed*5f);
		
		actFrame = 0;
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
	}
	
	public LivingEntity(int health, Vector2 position, String animationPath, float animSpeed, String tag) {
		this(health, position, GameManager.getResources().getAnimationByPath(animationPath), animSpeed, tag);
	}
		
	/*//ordre des elements modifié pour eviter les ambiguités
	public LivingEntity(int health, Vector2 position, Sprite defaultSprite) {
		this(health, position, new Sprite[] {defaultSprite}, 1.0f);
	}
	
	public LivingEntity(int health, Sprite defaultSprite) {
		this(health, Vector2.zero(), defaultSprite);
	}


	/**
     * 
     */
    private int health;

    
    @Override
    public Sprite display() {  
    	
    	if(animationSprite.length == 1) {
    		return animationSprite[0];
    		
    	}else if(animationSprite.length > 1) {
    		long delta = GameManager.getInstance().getClockMillis() - lastFrameUpdate;
    		    		
    		
    		if(delta >=  timeLoopAnimation) {
    			lastFrameUpdate = GameManager.getInstance().getClockMillis();
    		}
    		
 
    		return animationSprite [ (int)(((float)delta/(float)timeLoopAnimation)*animationSprite.length)%animationSprite.length];
    	}
    	
    	
    	
  	   return null;
    }
    
    
    
}