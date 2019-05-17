package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import zombies.Zombie;

public class Spikeweed extends AttackingPlant {

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
	
	// Pour ne pas subir des d�gats des zombies
	@Override
	public boolean isPlant() {
		return false;
	}
	@Override
	public String name() { return "Spikeweed" ;}
	
}
