package plants.pool;

import java.util.function.Function;

import base.GameObject;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import props.Explosion;
import zombies.Zombie;

public class Jalapeno extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6392260685887828267L;
	public Jalapeno(Vector2 position) {
		super(100, position, EnumReloadTime.very_slow, 125, "plants/Jalapeno.png", 1);

	}

	private float timeExplode = 0;	
	public void onExplosion() {
		var allZombies = GameManager.getInstance().getGameObjectArround(this, 20, new Function<GameObject, Boolean>() {

			@Override
			public Boolean apply(GameObject t) {
				if (t.isZombie() && t.getPosition().isOnSameRow(getPosition())) {
					return Boolean.valueOf(true);
				}
				return Boolean.valueOf(false);
			}
		});
		for (GameObject go : allZombies) {
			var zombie = (Zombie) go;
			zombie.takeDammage(1000);
		}
		new Explosion(getPosition());
		destroy();
	}
	@Override
	public void update() {
		super.update();
		if (timeExplode > 1f) {
			onExplosion();
		}
		timeExplode += GameManager.getInstance().getDeltatime();
	}

	@Override
	public String name() {return "Jalapeno"; }

}