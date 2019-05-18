package base;

public class Explosion extends LivingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4803255196428136427L;
	public Explosion(Vector2 pos) {
		super(999, Terrain.positionToCase(pos), "particles/explosion.png", 4f);
	}

	@Override
	public void onLastFrame() {
		super.onLastFrame();
		destroy();
	}
	@Override
	public String name() {return "Explosion";}
}
