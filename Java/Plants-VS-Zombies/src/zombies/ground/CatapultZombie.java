package zombies.ground;

import java.util.function.Function;

import base.GameObject;
import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import plants.pool.Spikeweed;
import projectiles.BasketBall;
import zombies.Zombie;

public class CatapultZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4092961601846492848L;


	public CatapultZombie(Vector2 position) {
		super(300, position, "zombies/CatapultZombie.png", 2f, 1f);
	}

	@Override
	public String name() {return "CatapultZombie";}


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
	
	@Override
	protected boolean conditionToEat(LivingEntity firstEnemy) {
		return getPosition().getX() <= 10f;
	}
	
	@Override
	protected void eatPlant(LivingEntity enemy) {
		new BasketBall(getPosition(), enemy);
	}
	
}
