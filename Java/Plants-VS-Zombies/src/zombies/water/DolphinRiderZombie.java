package zombies.water;

import base.LivingEntity;
import base.Vector2;
import main.GameManager;
import plants.pool.TallNut;
import zombies.Zombie;

public class DolphinRiderZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327298733010759779L;

	public DolphinRiderZombie(Vector2 position) {
		super(100, position, "zombies/DolphinRiderZombie.png", 5f, 1.5f, true);

	}
	@Override
    public String name() {return "DolphinRiderZombie";}

	private boolean canJump = true;
	
	@Override
	public void update() {
		super.update();
		
		if (canJump) {
	    	LivingEntity firstEnemy = (LivingEntity) GameManager.getInstance().getFirstPlant(this);
	    	// si le zombie rencontre une plante devant lui et assez proche, il s'arr�te pour la manger
	    	if (firstEnemy != null && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.5) {
	    		if (!(firstEnemy instanceof TallNut)) {
		    		this.translationFixed(-0.5f, 0); // translation de une case vers la gauche
	    		}
	    		canJump = false;
	    		this.addSpeed(-0.5f);
	    	}
		}
	}


}
