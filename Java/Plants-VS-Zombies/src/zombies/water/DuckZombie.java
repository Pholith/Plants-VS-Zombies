package zombies.water;

import base.Vector2;
import zombies.Zombie;

public class DuckZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8549720965732979065L;
	public DuckZombie(Vector2 position) {
		super(100, position, "zombies/DuckZombie.png", 5f, 1f, true);

	}
	@Override
	public String name() {return "DuckZombie";}

}
