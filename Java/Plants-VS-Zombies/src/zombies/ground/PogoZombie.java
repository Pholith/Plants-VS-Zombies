package zombies.ground;

import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import plants.pool.TallNut;
import zombies.Zombie;

public class PogoZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7267173382726304261L;

	public PogoZombie(Vector2 position) {
		super(200, position, "zombies/PogoZombie.png", 3f, 1.2f);

	}

	private boolean hasPogo = true;

	@Override
	public String name() { return "PogoZombie"; }

	@Override
	public void update() {
		super.update();

		if (hasPogo) {
			LivingEntity firstEnemy = (LivingEntity) GameManager.getInstance().getFirstPlant(this);
			// si le zombie rencontre une plante devant lui et assez proche, il s'arr�te pour la manger
			if (firstEnemy != null && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.5) {
				if (!(firstEnemy instanceof TallNut)) {
					this.translationFixed(-0.5f, 0); // translation de une case vers la gauche
				}
			}
		}
	}
	@Override
	public boolean takeMetalProtection() {
		return hasPogo = false;
	}
}
