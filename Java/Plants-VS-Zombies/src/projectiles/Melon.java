package projectiles;

import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class Melon extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745884236059424761L;

	public Melon(Vector2 position, Zombie target) {
		super(position, 50, "plants/Melon.png", target);
	}

	@Override
	public String name() { return "Melon"; }

	@Override
	public void hit(LivingEntity z) {
		super.hit(z);

		var zombies = GameManager.getInstance().getZombieArround(z, 1.0f);
		for (Zombie zombie : zombies) {
			zombie.takeDammage(20);
		}
	}

}
