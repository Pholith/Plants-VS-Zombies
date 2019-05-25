package zombies.ground;

import base.Vector2;
import zombies.Zombie;

public class FlagZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 803708618177035203L;
	public FlagZombie(Vector2 position) {
		super(100, position, "zombies/FlagZombie.png", 0f, 1f);
		
		}
	@Override
    public String name() {return "FlagZombie";}

}