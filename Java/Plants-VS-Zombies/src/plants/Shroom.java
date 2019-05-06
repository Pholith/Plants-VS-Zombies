package plants;


import java.util.*;

import base.Vector2;
import main.GameInfo;
import main.GameManager;
import projectiles.Peash;

/**
 * 
 */
public class Shroom extends Plant {

	public Shroom(int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, animationPath , animationSpeed);
	
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
    public void update() {
    	super.update();
    }
}	