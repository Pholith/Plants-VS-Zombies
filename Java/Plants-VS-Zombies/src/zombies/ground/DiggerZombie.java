package zombies.ground;

import java.util.function.Function;

import base.GameObject;
import base.LivingEntity;
import base.Terrain;
import base.Vector2;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

public class DiggerZombie extends Zombie {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5207881299211689909L;
	
	public DiggerZombie(Vector2 position) {
		super(100, position, "zombies/DiggerZombie.png", 5f, 0.8f);
	}
	
	@Override
	public boolean isTargetable() {
		return !isDigging;
	}
	
	@Override
	protected LivingEntity findEnemy() {
		if (isDigging) {
			return null;
		}
		var self = this;
    	
		// Fonction de sélection des plantes à droite du zombie
    	Function<GameObject, Boolean> lambda = new Function<GameObject, Boolean>() {
	    	@Override
			public Boolean apply(GameObject t) {
				if (t.isPlant() && t.isOnSameRow(self) && t.getPosition().getX() - 0.5 < self.getPosition().getX() ) {
					return Boolean.valueOf(true);
				}
				return Boolean.valueOf(false);
	    	}
		};
		var plants = GameManager.getInstance().getGameObjectArround(this, 10, lambda);
		Plant targetLeft = null;
		if (plants.size() > 0) {
			targetLeft = (Plant) plants.toArray()[0];
		}
		return targetLeft;

	}
	@Override
	protected boolean conditionToEat(LivingEntity firstEnemy) {
    	return true;
	}
	private boolean isDigging = true;
	@Override
    public String name() {return "DiggerZombie";}

	@Override
	public void update() {
		
		
		super.update();
		
		if (isDigging && Terrain.positionToCase(getPosition()).getX() < 0.1) {
			isDigging = false;
			
			addSpeed(-1.5f);
			setAnimationSprite( GameManager.getResources().getAnimationByPath("zombies/DiggerZombie2.png"));
		}
	}
}