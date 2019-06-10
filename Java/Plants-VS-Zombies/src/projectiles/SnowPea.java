package projectiles;

import base.LivingEntity;
import base.Vector2;
import zombies.Zombie;

public class SnowPea extends LineProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975374527286108868L;
	public SnowPea(Vector2 position, boolean burned) {
		super(position, new Vector2(0.06f, 0), 10, "plants/snowpeash.png", burned);
	}
	public SnowPea(Vector2 position) {
		this(position, false);
	}

	@Override
	public String name() {
		return "SnowPeash";
	}
	@Override
	public void hit(LivingEntity l) {
		Zombie z = (Zombie) l; 
		super.hit(z);
		z.slow();
	}
	@Override
	public void onBurn() {
		super.onBurn();
		new Pea(getPosition(), true);
		destroy();
	}

}
