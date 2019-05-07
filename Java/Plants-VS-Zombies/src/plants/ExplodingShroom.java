package plants;

import base.Vector2;
import main.Explosion;
import main.GameManager;
import zombies.Zombie;

public class ExplodingShroom extends Shroom {

	public ExplodingShroom(int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, animationPath, animationSpeed);
	}

	private float timeExplode = 0;
		
	public void onExplosion() {
		destroy();
	}

	
	public void update() {

    	if (!isSleeping()) {
	
			if (timeExplode > 1f) {
				onExplosion();
			}
			timeExplode += GameManager.getInstance().getDeltatime();
    	}
	}
	

    public String name() {return "ExplodingShroom";}

}
