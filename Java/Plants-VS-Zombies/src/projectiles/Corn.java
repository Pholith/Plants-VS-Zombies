package projectiles;

import base.LivingEntity;
import base.Vector2;
import zombies.Zombie;

public class Corn extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745884236059424761L;

	public Corn(Vector2 position, Zombie target) {
		super(position, 15, "plants/Corn.png", target);
	}

	@Override
	public String name() { return "Corn"; }

	@Override
	public void hit(LivingEntity l) {
		Zombie z = (Zombie) l;
		super.hit(z);

		if (Math.random() > 0.3d) {
			z.stun();
			z.takeDammage(20);
		}
	}

}
