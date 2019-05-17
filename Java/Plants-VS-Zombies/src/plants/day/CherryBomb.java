package plants.day;


import java.util.*;

import base.Explosion;
import base.GameObject;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

/**
 * 
 */
public class CherryBomb extends Plant {

	public CherryBomb(Vector2 position) {
		super(100, position, EnumReloadTime.slow, 150, "plants/cherryBomb.png", 6f);
	}
	

	private float timeExplode = 0;
	
	
	public void update() {

		if (timeExplode > 1f) {
			for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this)) {
				gameObject.takeDammage(500);
			}
			new Explosion(getPosition());
			destroy();
		}
		
		timeExplode += GameManager.getInstance().getDeltatime();
	}
	

    public String name() {return "CherryBomb";}


}