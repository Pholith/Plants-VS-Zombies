package zombies.ground;

import base.LivingEntity;
import base.Vector2;
import plants.Plant;
import zombies.Zombie;

public class LadderZombie extends Zombie {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1724437095882415206L;
	/**
	 * 
	 */
	public LadderZombie(Vector2 position) {
		super(200, position, "zombies/LadderZombie.png", 3f, 1.3f);
	}

	@Override
	public String name() {return "LadderZombie";}

	private boolean hasLadder = true;
	@Override
	public boolean takeMetalProtection() {
		if (hasLadder) {
			hasLadder = false;
			return true;
		}
		return false;
	}
	@Override
	protected void eatPlant(LivingEntity enemy) {
		Plant plant = (Plant) enemy;
		if (hasLadder) {
			plant.placeLadder();
			hasLadder = false;
		}
		super.eatPlant(enemy);
	}

}
