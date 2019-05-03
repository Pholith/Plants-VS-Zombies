package plants;


import java.util.*;

import base.GameObject;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;

/**
 * 
 */
public class CherryBomb extends Plant {

	public CherryBomb(Vector2 position) {
		super(100, position, 3f, "plants/cherryBomb.png", 6f);
	}
	

	private float timeExplode = 0;
	private boolean exploded;
	
	
	public static int getCost() {
    	return 150;
	}
	

	
	public void update() {

		if (timeExplode > 1f && !exploded) {
			for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this)) {
				gameObject.takeDammage(500);				
			}
			setAnimationSprite(GameManager.getResources().getAnimationByPath("particles/explosion.png"));
			
			exploded = true;
		}
		if (timeExplode > 1.7f) 
			destroy();				
		
		
		timeExplode += GameManager.getInstance().getDeltatime();
	}
	

    public String name() {return "CherryBomb";}


}