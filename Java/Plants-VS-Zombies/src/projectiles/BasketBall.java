package projectiles;

import base.LivingEntity;
import base.Vector2;

public class BasketBall extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223280193823263400L;
	public BasketBall(Vector2 position, LivingEntity target) {
		super(position, 10, "zombies/BasketBall.png", target, new Vector2((target.getPosition().getX() - position.getX() - 0.2f) / 100, -0.05f));
	}

	@Override
	public String name() { return "BasketBall"; }

}
