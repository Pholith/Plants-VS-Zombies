package projectiles;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class FirePea extends LineProjectile {
	public FirePea(Vector2 position) {
		
		super(position, new Vector2(0.06f, 0), 20, "plants/FirePea.png");
	}

    @Override
    public String name() {
    	return "FirePeash";
    }
    // dégats de zone
    @Override
    public void hit(Zombie z) {
		var zombies = GameManager.getInstance().getZombieArround(z, 1f);
		for (Zombie zombie : zombies) {
			zombie.takeDammage(getDammage());
		}
		destroy();
    }
}
