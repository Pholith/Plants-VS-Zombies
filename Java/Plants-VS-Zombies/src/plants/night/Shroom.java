package plants.night;

import base.Vector2;
import main.GameManager;
import plants.Plant;

public abstract class Shroom extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8129930468465270305L;

	public Shroom(int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, 0,animationPath , animationSpeed);
	
		isSleeping = !GameManager.getResources().getGameInfo().isNight();
		if (isSleeping) {
			setInactive();
		}
	}

    @Override
    public String name() {return "Shroom";}

    private boolean isSleeping;
    
    public boolean isSleeping() {
    	return isSleeping;
    }
    @Override
	public void update() {
    	super.update();
    }
}	