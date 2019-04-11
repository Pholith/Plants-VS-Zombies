package base;

import java.util.ArrayList;

import main.GameManager;
import zombies.BucketHeadZombie;

/**
 * 
 */
public abstract class LivingEntity extends GameObject {

  
	private Sprite[] animationSprite;
	private int actFrame;
	private final long timeLoopAnimation;
    private long lastFrameUpdate;
   

    

	public LivingEntity(int health, Vector2 position, Sprite[] animationSprite, float animSpeed) {
		super(Terrain.caseToPosition(position));
		this.health = health;
		this.animationSprite = animationSprite;
		
		if(animSpeed < 0.01)
			animSpeed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/animSpeed*5f);
		
		actFrame = 0;
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
		
	}
	
	public LivingEntity(int health, Vector2 position, String animationPath, float animSpeed) {
		this(health, position, GameManager.getResources().getAnimationByPath(animationPath), animSpeed);
	}
	@Override
    public String name() {return "LivingEntity";}

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
    /*	Fait baisser la vie de l'entité
     * Renvoie vrai si les dégats ont tué l'entité vivante
     */
    public boolean takeDammage(int dammage) {
    	health -= onTakeDammage(dammage);
    	if (health <= 0) {
    		destroy();
    		return true;
    	}
    	return false;
    }
    // cette méthode sert pour les zombies qui enchaissent des dégats via leurs outils
    public int onTakeDammage(int dammage) {
    	return dammage;
    }
    

   
    protected void setAnimationSprite(Sprite[] newAnim) {
    	animationSprite = newAnim;
    	lastFrameUpdate = GameManager.getInstance().getClockMillis();
    	
    }
    
    @Override
    public void update() {
    	super.update();
    }
    @Override
    public Sprite display() {  
    	
    	if(animationSprite.length == 1) {
    		return animationSprite[0];
    		
    	}else if(animationSprite.length > 1) {
    		long delta = GameManager.getInstance().getClockMillis() - lastFrameUpdate;
    		    		
    		float realTimeLoop =  timeLoopAnimation /GameManager.getInstance().getTimeScale();
    		
    		if(delta >=  realTimeLoop) {
    			lastFrameUpdate = GameManager.getInstance().getClockMillis();
    		}
    		
 
    		return animationSprite [ (int)(((float)delta/realTimeLoop)*animationSprite.length)%animationSprite.length];
    	}
    	
    	
    	
  	   return null;
    }
    
    
    
}