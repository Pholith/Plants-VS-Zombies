package plants.night;

import base.Vector2;
import main.GameManager;

public abstract class AttackingShroom extends Shroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271337480719168226L;

	public AttackingShroom(int health, Vector2 position, float reloadTime, int cost, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, cost, animationPath , animationSpeed);
	}

	private float attackSpeedCount = 0;

	@Override
	public String name() {return "AttackingPlant";}

	@Override
	public void update() {
		super.update();
		if (!isSleeping()) {

			if (conditionOfAttacking()) {
				attackSpeedCount += GameManager.getInstance().getDeltatime();

				if(attackSpeedCount >= 1.1f) {
					attack(getPosition());
					attackSpeedCount = 0;
				}
			}
		}
	}

	public void attack(Vector2 position) {

	}
	public boolean conditionOfAttacking() {
		return GameManager.getInstance().getFirstZombie(this) != null;
	}

}