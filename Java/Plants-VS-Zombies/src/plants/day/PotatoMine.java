package plants.day;

import base.Explosion;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

public class PotatoMine extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3461239287327393128L;

	public PotatoMine(Vector2 position) {
		super(100, position, EnumReloadTime.slow, 25, "plants/patatomine.png", 4f);
	}
	
	private float timeBeforeReady = 10;
			
	@Override
	public void update() {

		if (timeBeforeReady <= 0) {
	    	setActive();
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.5f) {
				
				for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this, 1f)) {
					gameObject.takeDammage(500);
				}
				new Explosion(getPosition());
				destroy();
	    	}
	    } else {
			setInactive();
			timeBeforeReady -= GameManager.getInstance().getDeltatime();
		}
				
	}
	
	@Override
    public String name() {return "PotatoMine";}
}