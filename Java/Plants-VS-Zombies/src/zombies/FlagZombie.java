package zombies;


import java.util.*;

import base.Sprite;
import base.Vector2;

/**
 * 
 */
public class FlagZombie extends Zombie {

	public FlagZombie(Vector2 position) {
		super(100, position, "plants/sunflower.png", 5f, 1f);
		
		}
	@Override
    public String name() {return "FlagZombie";}

}