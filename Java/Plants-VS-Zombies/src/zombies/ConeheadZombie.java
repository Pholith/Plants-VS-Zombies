package zombies;


import java.util.*;

import base.Vector2;

public class ConeheadZombie extends Zombie {

	public ConeheadZombie(Vector2 position) {
		super(200, position, "zombies/ConeheadZombie.png", 5f, 1f);
		
		}
	@Override
    public String name() {return "ConeheadZombie";}


}