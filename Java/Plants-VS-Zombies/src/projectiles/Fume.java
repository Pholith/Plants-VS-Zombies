package projectiles;

import java.util.HashSet;

import base.LivingEntity;
import base.Vector2;
import zombies.Zombie;

public class Fume extends LineProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = -551467092123706681L;

	public Fume(Vector2 position) {
		super(position, new Vector2(0.06f, 0), 10, "plants/Fume.png");
		alreadyAttacked = new HashSet<Zombie>();
		this.originalPosition = position;
		this.maxlenght = 3f;
	}

	private final float maxlenght;
	private final Vector2 originalPosition;

	HashSet<Zombie> alreadyAttacked;

	@Override
	public void update() {
		super.update();

		if (getPosition().getX() - originalPosition.getX() > maxlenght) {
			destroy();
		}

	}

	@Override
	public void hit(LivingEntity z) {
		if (alreadyAttacked.contains(z)) {
			return;
		}
		alreadyAttacked.add((Zombie) z);
		z.takeDammage(getDammage(), this);
	}
	@Override
	public boolean canByPassScreenDoor() {
		return true;
	}

}
