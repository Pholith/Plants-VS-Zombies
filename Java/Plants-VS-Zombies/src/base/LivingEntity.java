package base;

import enums.RenderMode;
import main.GameManager;
import projectiles.Projectile;
import ui.UI_AnimatedSprite;
import zombies.Zombie;

public abstract class LivingEntity extends GameObject{

  
	

	private static final long serialVersionUID = -5366255047840179209L;
	private Sprite[] animationSprite;
	private final long timeLoopAnimation;
    private long lastFrameUpdate;
    private boolean isActive; // pour stoper et remettre l'animation (chomper)

    

	public LivingEntity(int health, Vector2 position, Sprite[] animationSprite, float animSpeed) {
		super(Terrain.caseToPosition(position), RenderMode.Sprite, 10);
		this.health = health;
		

			this.animationSprite = animationSprite;

		
		if(animSpeed < 0.01)
			animSpeed = 0.01f;
		
		timeLoopAnimation= (long)(1000.0/animSpeed*5f);
		
		lastFrameUpdate =  GameManager.getInstance().getClockMillis();
		
		this.isActive = true;
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
    
    
    /*	Fait baisser la vie de l'entité
     * Renvoie vrai si les dégats ont tué l'entité vivante
     */    
    
    public boolean takeDammage(int dammage) {
    	return takeDammage(dammage, (Projectile) null);
    }   
    
    public boolean takeDammage(int dammage, Projectile lobProjectile) {
    	health -= onTakeDammage(dammage, lobProjectile);
    	addHitMarker();
     	if (health <= 0) {
    		destroy();
    		return true;
    	}
    	return false;
    }
    
    public boolean takeDammage(int dammage, Zombie zombie) {
    	health -= onTakeDammage(dammage, zombie);
    	addHitMarker();
    	if (health <= 0) {
    		destroy();
    		return true;
    	}
    	return false;
    }
    // cette méthode sert pour les zombies qui encaissent des dégats via leurs outils
    public int onTakeDammage(int dammage) {
    	return onTakeDammage(dammage, (Zombie) null);
    }
    public int onTakeDammage(int dammage, Projectile p) {
    	return dammage;
    }
    
    public int onTakeDammage(int dammage, Zombie z) {
    	return dammage;
    }
    
    
	//Particules d'impact 
    private void addHitMarker() {
    	new UI_AnimatedSprite(getPosition().add(Vector2.randomVector().multiply(0.3f)).add(new Vector2(0,-0.15f)), "particles/sparks.png", 6f, true);
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
    		int complexCalcul = (int) ((delta/realTimeLoop) * animationSprite.length);
    		if (complexCalcul >= animationSprite.length) {
				onLastFrame();
			}
    		return animationSprite [ complexCalcul % animationSprite.length ];
    	}
    	 	
    	
  	   return null;
    }
    
    
    public float getLightRange() {
    	return -1f;
    }
    
    
}