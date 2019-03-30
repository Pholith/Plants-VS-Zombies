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
   
    private Square associatedSquare;
    

	public LivingEntity(int health, Vector2 position, Sprite[] animationSprite, float animSpeed) {
		super(Terrain.caseToPosition(position));
		this.health = health;
		this.animationSprite = animationSprite;
		
		if(animSpeed < 0.01)
			animSpeed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/animSpeed*5f);
		
		actFrame = 0;
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
		
		associatedSquare = GameManager.getResources().addEntityToTerrain((int)position.getX(), (int)position.getY(), this);
	}
	
	public LivingEntity(int health, Vector2 position, String animationPath, float animSpeed) {
		this(health, position, GameManager.getResources().getAnimationByPath(animationPath), animSpeed);
	}
	@Override
    public String name() {return "LivingEntity";}

	/*//ordre des elements modifi� pour eviter les ambiguit�s
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
    /*	Fait baisser la vie de l'entit�
     * Renvoie vrai si les d�gats ont tu� l'entit� vivante
     */
    public boolean takeDammage(int dammage) {
    	health -= dammage;
    	if (health <= 0) {
    		destroy();
    		return true;
    	}
    	return false;
    }
    
    
   @Override
   public void onDestroy() {
    if(associatedSquare != null)
    	associatedSquare.setContain(null);
   }
   
    
    
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