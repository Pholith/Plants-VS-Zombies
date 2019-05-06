package plants;


import java.util.*;

import base.Vector2;
import projectiles.Peash;

/**
 * 
 */
public class Shroom extends Plant {

	public Shroom(int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, animationPath , animationSpeed);
	}

    @Override
    public String name() {return "Shroom";}

    private boolean isSleeping;
    
    public void start() {
    	
    }
    public void update() {
    	super.update();
    	
    	if (isSleeping) {
			return;
		}
    }
}	