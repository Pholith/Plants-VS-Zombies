package plants;


import java.util.*;

import base.Vector2;
import projectiles.Peash;

/**
 * 
 */
public class Peashooter extends AttackingPlant {

	public Peashooter(Vector2 position) {
		super(100, position, 3f, "plants/pea_shooter.png", 4f);

	}

	public static int getCost() {
    	return 100;
	}

    @Override
    public String name() {return "Peashooter";}

	@Override
	public void attack(Vector2 position) {
		new Peash(position.add(0.2f, 0.1f));
	}

}