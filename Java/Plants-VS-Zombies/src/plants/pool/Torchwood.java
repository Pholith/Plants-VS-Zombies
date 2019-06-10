package plants.pool;

import java.util.HashSet;
import java.util.function.Function;

import base.GameObject;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import projectiles.LineProjectile;

public class Torchwood extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202859548868323963L;
	public Torchwood(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 175, "plants/Torchwood.png", 3f);
	}
	
	@Override
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
	@Override
	public String name() {return "Torchwood"; }
    
	@Override
    public float getLightRange() {
    	return 1f;
    }

}
