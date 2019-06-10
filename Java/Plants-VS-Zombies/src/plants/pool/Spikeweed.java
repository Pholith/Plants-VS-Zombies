package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import zombies.Zombie;

public class Spikeweed extends AttackingPlant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7559993030311394735L;
	public Spikeweed(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/Spikeweed.png", 3f);
	}

	@Override
	public void attack(Vector2 position, Zombie zombie__) {
		super.attack(position, zombie__);
		var zombies = GameManager.getInstance().getZombieArround(this, 0.8f);
		for (Zombie zombie : zombies) {
			zombie.takeDammage(10);
		}
	}

	// Pour ne pas subir des dégats des zombies
	@Override
	public boolean isPlant() {
		return false;
	}
	@Override
	public String name() { return "Spikeweed" ;}

}
