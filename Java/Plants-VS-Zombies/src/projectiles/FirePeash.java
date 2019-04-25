package projectiles;

import base.Vector2;
import zombies.Zombie;

public class FirePeash extends Projectile {
	public FirePeash(Vector2 position) {
		
		super(position, new Vector2(0.06f, 0), 10, "plants/firepeash.png");
	}

	// TODO class à faire plus tard
	@Override
	public void update() {
		super.update();
	}
    @Override
    public String name() {
    	return "FirePeash";
    }
}
