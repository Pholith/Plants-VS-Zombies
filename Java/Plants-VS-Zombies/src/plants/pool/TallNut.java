package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;

public class TallNut extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6437048781022792380L;
	public TallNut(Vector2 position) {
		super(1000, position, EnumReloadTime.slow, 125,"plants/TallNut.png", 3f);
	}

	@Override
    public String name() {return "TallNut";}

	boolean changed = false;
	@Override
	public void update() {
		super.update();
		if (!changed && getHealth() < 300) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/TallNut_cracked.png"));
		}
	}
	
}