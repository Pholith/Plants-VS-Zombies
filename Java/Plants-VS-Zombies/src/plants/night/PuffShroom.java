package plants.night;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import projectiles.PuffProjectile;
import zombies.Zombie;

public class PuffShroom extends AttackingShroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3879882259660663122L;

	public PuffShroom(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 0, "plants/PuffShroom.png", 4f);
	}

	@Override
	public String name() {return "PuffShroom";}

	@Override
	public void attack(Vector2 position) {
		new PuffProjectile(position, 3f);
	}

	@Override
	public boolean conditionOfAttacking() {
		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		return firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 3f;
	}
}