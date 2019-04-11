package zombies;

import base.Terrain;
import base.Vector2;
import main.GameManager;
import plants.Plant;

public class PoleVaulterZombie extends Zombie {

	public PoleVaulterZombie(Vector2 position) {
		super(100, position, "zombies/polevaulter.png", 0f, 1.5f);
		
		}

	private boolean canVault = true;
	
	@Override
	public String name() { return "PoleVaulterZombie"; }
	
	@Override
	public void update() {
		super.update();
		
		if (canVault) {
	    	Plant firstEnemy = (Plant) GameManager.getInstance().getFirstPlant(this);
	    	// si le zombie rencontre une plante devant lui et assez proche, il s'arr�te pour la manger
	    	if (firstEnemy != null && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.5) {
	    		this.translation(-0.5f, 0); // translation de une case vers la gauche
	    		canVault = false;
	    		this.addSpeed(-0.5f);
	    	}
		}

	}
}
