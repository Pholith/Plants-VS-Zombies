package plants.night;

import base.Vector2;
import main.Explosion;
import main.GameManager;
import zombies.Zombie;

public class DoomShroom extends ExplodingShroom {

	public DoomShroom(Vector2 position) {
		super(100, position, 20f, "plants/DoomShroom.png", 3f);
	}
	
	public static int getCost() {
    	return 125;
	}

	@Override
	public void onExplosion() {
		var allZombies = GameManager.getInstance().getAllZombies();
		for (Zombie zombie : allZombies) {
			zombie.destroy();
		}
		new Explosion(getPosition());
		super.onExplosion();
	}
	@Override
    public String name() {return "DoomShroom";}


}
