package plants.night;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import projectiles.PuffProjectile;
import zombies.Zombie;

public class ScaredyShroom extends AttackingShroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2173676450217102004L;
	public ScaredyShroom(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 25, "plants/ScaredyShroom.png", 4f);
		hidden = false;
	}


	@Override
	public String name() {return "ScaredyShroom";}

	private boolean hidden;
	@Override
	public void update() {
		super.update();
		if (!isSleeping()) {
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 2f) {
				hidden = true;
			} else {
				hidden = false;
			}
		}

	}
	@Override
	public void attack(Vector2 position) {
		new PuffProjectile(position);
	}
	@Override
	public boolean conditionOfAttacking() {
		return !hidden && super.conditionOfAttacking();
	}
}