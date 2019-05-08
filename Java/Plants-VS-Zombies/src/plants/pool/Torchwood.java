package plants.pool;

import java.util.HashSet;
import java.util.function.Function;

import base.GameObject;
import base.Vector2;
import main.GameManager;
import plants.Plant;
import projectiles.LineProjectile;

public class Torchwood extends Plant {

	public Torchwood(Vector2 position) {
		super(100, position, 10f, "plants/Torchwood.png", 3f);
	}
	
	public void update() {
		super.update();
		
		HashSet<GameObject> projectiles = GameManager.getInstance().getGameObjectArround(this, 0.6f, new Function<GameObject, Boolean>() {
			@Override
			public Boolean apply(GameObject t) {
				if (t.isProjectile() && t instanceof LineProjectile) {
					return Boolean.valueOf(true);
				}
				return Boolean.valueOf(false);
			}
		});
		
		// unique endroit avec des instanceof
		for (GameObject projectile : projectiles) {
			((LineProjectile) projectile).burn();
		}
	}
	public String name() {return "Torchwood"; }

	public static int getCost() { return 175; }
}
