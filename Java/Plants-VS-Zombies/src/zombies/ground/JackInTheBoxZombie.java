package zombies.ground;

import java.util.HashSet;
import java.util.function.Function;

import base.GameObject;
import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import props.Explosion;
import zombies.Zombie;

public class JackInTheBoxZombie extends Zombie {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2773242591581311245L;

	public JackInTheBoxZombie(Vector2 position) {
		super(200, position, "zombies/JackInTheBoxZombie.png", 3f, 1.3f);
	}

	@Override
	public String name() {return "JackInTheBoxZombie";}

	private boolean hasBox = true;
	@Override
	public boolean takeMetalProtection() {
		if (hasBox) {
			hasBox = false;
			return true;
		}
		return false;
	}

	@Override
	protected void eatPlant(LivingEntity enemy) {
		explode();
		destroy();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		explode();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void explode() {
		if (!hasBox) {
			return;
		}
		Function func = new Function<GameObject, Boolean>() {
			@Override
			public Boolean apply(GameObject t) {
				if (t.isPlant()) {
					return Boolean.valueOf(true);
				}
				return Boolean.valueOf(false);
			}
		};

		HashSet<GameObject> plants = GameManager.getInstance().getGameObjectArround(this, 1.5f, func);

		for (GameObject object : plants) {
			object.destroy();
			new Explosion(object.getPosition());
		}
		new Explosion(getPosition());
	}
}
