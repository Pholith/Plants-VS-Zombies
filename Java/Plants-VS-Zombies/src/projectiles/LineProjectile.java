package projectiles;


import base.Constant;
import base.GameObject;
import base.Sprite;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;


public class LineProjectile extends GameObject implements Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719476588207871569L;
	private Sprite defaultSprite;
	
    public LineProjectile(Vector2 position, Vector2 speed, int dammage, String texturePath, boolean burned) {
		super(position);
		this.speed = speed;
		this.dammage = dammage;
		Sprite[] sprts = GameManager.getResources().getAnimationByPath(texturePath);
		if(sprts.length == 0)
			sprts = GameManager.getResources().getAnimationByPath(Constant.errorTexture);
		
		defaultSprite = sprts[0];
		this.burned = burned;
	}

    public LineProjectile(Vector2 position, Vector2 speed, int dammage, String texturePath) {
    	this(position, speed, dammage, texturePath, false);
    }

    private Vector2 speed;
    private int dammage; 

    @Override
	public int getDammage() {
    	return dammage;
    }
    @Override
    public boolean isProjectile() {
    	return true;
    }
    @Override
	public void hit(Zombie z) {
		z.takeDammage(dammage, this);
		destroy();
    }
    
    @Override
    public void update() {
    	super.update();
    	Zombie firstEnemy =  (Zombie) GameManager.getInstance().getFirstZombie(this);
    	// si le projectile rencontre un zombie
    	if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.6) {
    		hit(firstEnemy);
    	}
    	translation(speed);
    }
    @Override
    public String name() {
    	return "Projectile";
    }
    @Override
    public Sprite display() {  
    	return defaultSprite;
    }
    @Override
	public boolean canByPassScreenDoor() {
    	return false;
    }
    private boolean burned;
    public void burn() {
    	if (!burned) {
			onBurn();
		}
    }
    public void onBurn() {
    	
    }
    
}