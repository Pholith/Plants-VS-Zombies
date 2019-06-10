package zombies.ground;

import base.Vector2;
import zombies.Zombie;

public class JacksonZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2106560930761221922L;
	public JacksonZombie(Vector2 position) {
		super(100, position, "zombies/JacksonZombie.png", 5f, 1f);

	}
	@Override
	public String name() {return "JacksonZombie";}

}
