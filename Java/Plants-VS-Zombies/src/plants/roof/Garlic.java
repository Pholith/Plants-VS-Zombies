package plants.roof;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

public class Garlic extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6437048781022792380L;
	public Garlic(Vector2 position) {
		super(300, position, EnumReloadTime.medium, 50,"plants/Garlic.png", 3f);
	}

	@Override
    public String name() {return "Garlic";}

	boolean changed = false;
	@Override
	public void update() {
		super.update();
		if (!changed && getHealth() < 100) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/Garlic_cracked.png"));
		}
	}
	@Override
	public int onTakeDammage(int dammage, Zombie z) {
		if (z != null) {
			z.changeRow();
		}
		return super.onTakeDammage(dammage, z);
	}
}