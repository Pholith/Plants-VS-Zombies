package zombies;


import java.util.*;

import base.Sprite;
import base.Vector2;

/**
 * 
 */
public class FlagZombie extends Zombie {

	public FlagZombie(Vector2 position) {
		super(100, position, "zombies/FlagZombie.png", 0f, 1f);
		
		}
	@Override
    public String name() {return "FlagZombie";}

}