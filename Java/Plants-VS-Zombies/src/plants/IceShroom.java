package plants;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class IceShroom extends ExplodingShroom {

	public IceShroom(Vector2 position) {
		super(100, position, 10f, "plants/IceShroom.png", 3f);
	}
	
	public static int getCost() {
    	return 75;
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
