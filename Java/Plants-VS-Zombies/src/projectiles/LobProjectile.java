package projectiles;


import base.Constant;
import base.GameObject;
import base.LivingEntity;
import base.Sprite;
import base.Vector2;
import main.GameManager;


public class LobProjectile extends GameObject implements Projectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6384191522990649520L;
	private final Sprite defaultSprite;

	public LobProjectile(Vector2 position, int dammage, String texturePath, LivingEntity target, Vector2 speed) {
		super(position);

		this.speed = speed;
		this.dammage = dammage;

		Sprite[] sprts = GameManager.getResources().getAnimationByPath(texturePath);
		if(sprts.length == 0) {
			sprts = GameManager.getResources().getAnimationByPath(Constant.errorTexture);
		}

		defaultSprite = sprts[0];

		this.target = target;
		acceleration = new Vector2(0f, 0.001f);
	}
	public LobProjectile(Vector2 position, int dammage, String texturePath, LivingEntity target) {
		this(position, dammage, texturePath, target, new Vector2((target.getPosition().getX()-position.getX() - 0.3f) / 100, -0.05f));
	}
	
	private Vector2 speed;
	private final Vector2 acceleration;
	private final LivingEntity target;
	private final int dammage; 

	@Override
	public int getDammage() {
		return dammage;
	}
	@Override
	public boolean isProjectile() {
		return true;
	}
	@Override
	public void hit(LivingEntity z) {
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
		if (speed.getY() >= 0 && target.getPosition().getX() < this.getPosition().getX() + 0.3 &&  target.getPosition().getX() > this.getPosition().getX() -0.3) {
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