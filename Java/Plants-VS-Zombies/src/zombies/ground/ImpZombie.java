package zombies.ground;


import base.Vector2;
import zombies.Zombie;

public class ImpZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1814752436569740712L;

	public ImpZombie(Vector2 position) {
		super(60, position, "zombies/ImpZombie.png", 3f, 1.8f);
	}

	@Override
	public String name() {return "ImpZombie";}

}

