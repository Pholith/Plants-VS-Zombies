package plants.fog;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.night.AttackingShroom;
import projectiles.PuffProjectile;
import zombies.Zombie;

public class SeaShroom extends AttackingShroom{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1463339189317477343L;

	public SeaShroom(Vector2 position) {
		super(100, position, EnumReloadTime.slow, 0, "plants/SeaShroom.png", 4f);
	}

	@Override
	public String name() {return "SeaShroom";}

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
