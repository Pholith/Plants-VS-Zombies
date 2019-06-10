package plants.night;

import base.Vector2;
import main.GameManager;

public abstract class ExplodingShroom extends Shroom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2679381334433828454L;

	public ExplodingShroom(int health, Vector2 position, float reloadTime, int cost, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, cost, animationPath, animationSpeed);
	}

	private float timeExplode = 0;

	public void onExplosion() {
		destroy();
	}


	@Override
	public void update() {

		if (!isSleeping()) {

			if (timeExplode > 1f) {
				onExplosion();
			}
			timeExplode += GameManager.getInstance().getDeltatime();
		}
	}


	@Override
	public String name() {return "ExplodingShroom";}

}
