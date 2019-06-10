package plants.roof;

import base.Vector2;
import enums.EnumReloadTime;
import plants.AttackingPlant;
import projectiles.Melon;
import zombies.Zombie;

public class MelonPult extends AttackingPlant {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2529851896405838807L;

	public MelonPult(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 300, "plants/MelonPult.png", 1.2f);
	}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Melon(getPosition().add(-0.2f, 0), zombie);
	}

    @Override
	public String name() { return "MelonPult"; }

}
