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
		super(100, position, 150, 3f, "plants/cherryBomb.png", 4f);
	}
	


	private int timeExplode = 0;
	public void start() {
		
	}
	
	public void update() {

		if (timeExplode > 100) {
			for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this)) {
				gameObject.takeDammage(300);
			}
			destroy();
		}
		timeExplode ++;
	}
	
	/*
	*/
	@Override
    public String name() {return "Peashooter";}




}