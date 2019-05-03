package plants;


import java.util.*;

import base.GameObject;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;

/**
 * 
 */
public class PotatoMine extends Plant {

	public PotatoMine(Vector2 position) {
		super(100, position, 3f, "plants/patatomine.png", 6f);
	}
	
	private float timeBeforeReady = 10;
	public void start() {
		
	}
	
	private boolean exploded;
	public static int getCost() {
    	return 25;
	}
	
	private float explodeAnim = 0;

	
	public void update() {

		if (timeBeforeReady <= 0) {
	    	setActive();
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.5f) {
				
				for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this, 1f)) {
					gameObject.takeDammage(500);
					setAnimationSprite(GameManager.getResources().getAnimationByPath("particles/explosion.png"));
					explodeAnim = 0.1f;
				}

	    	}
	    } else {
			setInactive();
			timeBeforeReady -= GameManager.getInstance().getDeltatime();
		}
		
		if(explodeAnim != 0) {					
			explodeAnim+= GameManager.getInstance().getDeltatime();
			if(explodeAnim > 0.7)
		destroy();
		}
		
	}
	
	/*
	*/
	@Override
    public String name() {return "CherryBomb";}


}