package zombies.ground;

import java.util.function.Function;

import base.GameObject;
import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import plants.pool.Spikeweed;
import zombies.Zombie;

public class Zombonie extends Zombie {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4198724749687465741L;

	public Zombonie(Vector2 position) {
		super(500, position, "zombies/Zombonie.png", 5f, 1.2f);
	}

	@Override
    public String name() {return "Zombonie";}

	@Override
	protected void eatPlant(LivingEntity enemy) {
		enemy.destroy();
	}
	@Override
	public void update() {
		super.update();
		
		var self = this;

		var spikeweedTest = GameManager.getInstance().getGameObjectArround(this, 1f, new Function<GameObject, Boolean>() {
			@Override
			public Boolean apply(GameObject t) {
				return t.getClass() == Spikeweed.class && t.isOnSameRow(self);
			}
		});
		if (spikeweedTest.size() > 0) {
			for (GameObject spike : spikeweedTest) {
				spike.destroy();
				destroy();
			}
		}
	}
}
