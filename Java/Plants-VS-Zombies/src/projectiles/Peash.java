package projectiles;



import java.util.*;

import base.Vector2;
import main.GameManager;

/**
 * 
 */
public class Peash extends Projectile {

	public Peash(Vector2 position) {
		
		super(position, new Vector2(0.1f, 0), 25, "plants/peash.png");
	}

    /**
     * Default constructor
     */
	@Override
	public void update() {
		super.update();
	}	
}