package plants.night;

import base.Explosion;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import zombies.Zombie;

public class DoomShroom extends ExplodingShroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8892382443880564271L;
	public DoomShroom(Vector2 position) {
		super(100, position, EnumReloadTime.very_slow, 125, "plants/DoomShroom.png", 3f);
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
