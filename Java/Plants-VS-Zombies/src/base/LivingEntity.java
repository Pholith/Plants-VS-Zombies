package base;


import main.GameManager;
import projectiles.Projectile;


public abstract class LivingEntity extends GameObject {

  
	private Sprite[] animationSprite;
	private int actFrame;
	private final long timeLoopAnimation;
    private long lastFrameUpdate;
    private boolean isActive; // pour stoper et remettre l'animation (chomper)

    

	public LivingEntity(int health, Vector2 position, Sprite[] animationSprite, float animSpeed) {
		super(Terrain.caseToPosition(position));
		this.health = health;
		this.animationSprite = animationSprite;
		
		if(animSpeed < 0.01)
			animSpeed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/animSpeed*5f);
		
		actFrame = 0;
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
		
		this.isActive = true;
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
	}*/


    private int health;
    public int getHealth() {
    	return health;
    }
    protected void setActive() {
    	isActive = true;
    }
    protected void setInactive() {
    	isActive = false;
    }
    /*	Fait baisser la vie de l'entit�
     * Renvoie vrai si les d�gats ont tu� l'entit� vivante
     */
    public boolean takeDammage(int dammage) {
    	return takeDammage(dammage, null);
    }
    public boolean takeDammage(int dammage, Projectile lobProjectile) {
    	health -= onTakeDammage(dammage, lobProjectile);
    	if (health <= 0) {
    		destroy();
    		return true;
    	}
    	return false;
    }
    // cette m�thode sert pour les zombies qui encaissent des d�gats via leurs outils
    public int onTakeDammage(int dammage) {
    	return onTakeDammage(dammage, null);
    }
    public int onTakeDammage(int dammage, Projectile p) {
    	return dammage;
    }
    

   
    protected void setAnimationSprite(Sprite[] newAnim) {
    	animationSprite = newAnim;
    	lastFrameUpdate = GameManager.getInstance().getClockMillis();
    	
    }
    // utile pour l'explosion
    public void onLastFrame() {}
    
    @Override
    public void update() {
    	super.update();
    }
    @Override
    public Sprite display() {  
    	
    	if(animationSprite.length == 1 || !(isActive)) {
    		return animationSprite[0];
    		
    	} else if (animationSprite.length > 1) {
    		long delta = GameManager.getInstance().getClockMillis() - lastFrameUpdate;

    		float realTimeLoop =  timeLoopAnimation /GameManager.getInstance().getTimeScale();
    		
    		if (delta >=  realTimeLoop) {
    			lastFrameUpdate = GameManager.getInstance().getClockMillis();
    		}
    		int complexCalcul = (int) (((float) delta/realTimeLoop) * animationSprite.length);
    		if (complexCalcul >= animationSprite.length) {
				onLastFrame();
			}
    		return animationSprite [ complexCalcul % animationSprite.length ];
    	}
    	 	
    	
  	   return null;
    }
    
    
    
}