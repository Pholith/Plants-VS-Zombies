package base;

import java.util.ArrayList;

import main.GameManager;

/**
 * 
 */
public class LivingEntity extends GameObject {

  
	private final Sprite[] animationSprite;
	private int actFrame;
	private final float animSpeed;
    private long lastFrameUpdate;
    
	


	public LivingEntity(int health,  Sprite[] animationSprite, Vector2 position, float animSpeed) {
		super(position);
		this.health = health;
		this.animationSprite = animationSprite;
		this.animSpeed = animSpeed;
		actFrame = 0;
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
	}
	
	//ordre des elements modifié pour eviter les ambiguités
	public LivingEntity(int health, Vector2 position, Sprite defaultSprite) {
		this(health, new Sprite[] {defaultSprite}, position, 1.0f);
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
    	if(animationSprite.length > 0) {
    		long millis = GameManager.getInstance().getClockMillis();
    		
    		if(millis - lastFrameUpdate >= (long)(1000f / (animSpeed*10.0f)) ) {
    			lastFrameUpdate = millis;
    			actFrame++;
    		}
    		return animationSprite[actFrame%animationSprite.length];
    	}
  	   return null;
    }
}