package plants.night;


import java.util.*;

import base.Vector2;
import main.GameInfo;
import main.GameManager;
import plants.Plant;
import projectiles.Pea;


public abstract class Shroom extends Plant {

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
    public void update() {
    	super.update();
    }
}	