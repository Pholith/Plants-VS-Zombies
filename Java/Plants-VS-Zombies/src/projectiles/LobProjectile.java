package projectiles;


import base.Constant;
import base.GameObject;
import base.Sprite;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;


public class LobProjectile extends GameObject implements Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6384191522990649520L;
	private Sprite defaultSprite;
	
    public LobProjectile(Vector2 position, int dammage, String texturePath, Zombie target) {
		super(position);
		
		this.speed = new Vector2((target.getPosition().getX()-position.getX() - 0.3f) / 100, -0.05f);
		this.dammage = dammage;
		
		Sprite[] sprts = GameManager.getResources().getAnimationByPath(texturePath);
		if(sprts.length == 0)
			sprts = GameManager.getResources().getAnimationByPath(Constant.errorTexture);
		
		defaultSprite = sprts[0];
		
		this.target = target;
		acceleration = new Vector2(0f, 0.001f);
	}


    private Vector2 speed;
    private Vector2 acceleration;
    private Zombie target;
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
    protected int getLayer() {
    	return super.getLayer() + 10;
    }
    @Override
    public void update() {
    	super.update();
    	if (speed.getY() >= 0 && target.getPosition().getX() < this.getPosition().getX() + 0.3 ) {
    		hit(target);
		}
    	speed = speed.add(acceleration);
    	translationFixed(speed);
    }
    @Override
    public String name() {
    	return "LobProjectile";
    }
    @Override
    public Sprite display() {  
    	return defaultSprite;
    }
	@Override
	public boolean canByPassScreenDoor() {
		return true;
	}
}