package zombies.ground;

import base.LivingEntity;
import base.Terrain;
import base.Vector2;
import zombies.Zombie;

public class GargantuarZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744408812723717852L;

	public GargantuarZombie(Vector2 position) {
		super(2500, position, "zombies/GargantuarZombie.png", 5f, 0.5f);
	}

	@Override
	public String name() {return "GargantuarZombie";}

	@Override
	protected void eatPlant(LivingEntity enemy) {
		enemy.destroy();
	}

	private boolean spawnedImp = false;
	@Override
	public void update() {
		super.update();

		if (!spawnedImp && getHealth() < 1000) {
			new ImpZombie(Terrain.positionToCase(getPosition()));
			spawnedImp = true;
		}
	}
}
