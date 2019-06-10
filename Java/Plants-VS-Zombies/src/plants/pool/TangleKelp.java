package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import props.Explosion;
import zombies.Zombie;

public class TangleKelp extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3591778266999438930L;

	public TangleKelp(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 25,"plants/TangleKelp.png", 3f);
	}

	@Override
	public void update() {

		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.5f) {
			firstEnemy.destroy();
			new Explosion(getPosition());
			destroy();
		}
	}

	@Override
	public String name() {return "TangleKelp";}
}