package zombies.water;

import base.LivingEntity;
import base.Vector2;
import zombies.Zombie;

public class SnorkelZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8760049230219962408L;
	public SnorkelZombie(Vector2 position) {
		super(100, position, "zombies/SnorkelZombie.png", 5f, 1f, true);
		isUnderWater = true;
	}
	@Override
    public String name() {return "SnorkelZombie";}

	@Override
	public void update() {
		isUnderWater = true;
		super.update();
	}
	@Override
	public boolean isTargetable() {
		return !isUnderWater;
	}
	private boolean isUnderWater;

	@Override
	protected void eatPlant(LivingEntity enemy) {
		super.eatPlant(enemy);
		isUnderWater = false;

	}
}
