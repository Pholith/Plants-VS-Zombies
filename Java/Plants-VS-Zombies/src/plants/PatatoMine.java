package plants;


import java.util.*;

import base.GameObject;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;

/**
 * 
 */
public class PatatoMine extends Plant {

	public PatatoMine(Vector2 position) {
		super(100, position, 25, 3f, "plants/patatomine.png", 6f);
	}
	
	private float timeBeforeReady = 10;
	public void start() {
		
	}
	
	private boolean exploded;
	
	public void update() {

		if (timeBeforeReady <= 0) {
	    	setActive();
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.5f) {
				
				for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this, 1f)) {
					gameObject.takeDammage(500);
				}
				destroy();
	    	}
	    } else {
			setInactive();
			timeBeforeReady -= GameManager.getInstance().getDeltatime();
		}
		
	}
	
	/*
	*/
	@Override
    public String name() {return "CherryBomb";}


}