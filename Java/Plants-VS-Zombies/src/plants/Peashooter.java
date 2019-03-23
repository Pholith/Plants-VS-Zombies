package plants;


import java.util.*;

import base.Vector2;
import projectiles.Peash;

/**
 * 
 */
public class Peashooter extends AttackingPlant {

	public Peashooter(Vector2 position) {
		super(100, position, 100, 3f, 25, 10f, "plants/pea_shooter.png", 4f);

		
	}

	
    /**
     * Default constructor
     */
	
	@Override
	public void attack(Vector2 position) {
		new Peash(position.add(0.6f, 0.1f)); // fait planter le truc pour le moment : A FIX
	}

}