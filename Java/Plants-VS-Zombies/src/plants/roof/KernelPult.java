package plants.roof;

import base.Vector2;
import enums.EnumReloadTime;
import plants.AttackingPlant;
import projectiles.Corn;
import zombies.Zombie;

public class KernelPult extends AttackingPlant{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2529851896405838807L;

	public KernelPult(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/KernelPult.png", 2f);
	}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Corn(getPosition().add(-0.2f, 0), zombie);
	}

    @Override
	public String name() { return "KernelPult"; }

}
