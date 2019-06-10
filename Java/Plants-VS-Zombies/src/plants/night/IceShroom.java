package plants.night;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import zombies.Zombie;

public class IceShroom extends ExplodingShroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7830252647189491567L;
	public IceShroom(Vector2 position) {
		super(100, position, EnumReloadTime.slow, 75, "plants/IceShroom.png", 3f);
	}

	@Override
	public void onExplosion() {
		var allZombies = GameManager.getInstance().getAllZombies();
		for (Zombie zombie : allZombies) {
			zombie.takeDammage(20);
			zombie.stun();
		}
		super.onExplosion();
	}
	@Override
	public String name() {return "IceShroom";}


}
