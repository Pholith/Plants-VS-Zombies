package plants.roof;

import base.Vector2;
import enums.EnumReloadTime;
import plants.AttackingPlant;
import projectiles.Cabbage;
import zombies.Zombie;

public class CabbagePult extends AttackingPlant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3181715344348714771L;

	public CabbagePult(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/CabbagePult.png", 2f);
	}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Cabbage(getPosition(), zombie);
	}

    @Override
	public String name() { return "CabbagePult"; }
}
