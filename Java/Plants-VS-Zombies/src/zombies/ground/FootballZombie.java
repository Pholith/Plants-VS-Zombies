package zombies.ground;

import base.Vector2;
import zombies.ProtectedZombie;

public class FootballZombie extends ProtectedZombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4234505564145210981L;

	public FootballZombie(Vector2 position) {
		super(100, position, "zombies/FootballZombie.png", 5f, 1.5f, 300);

	}
	@Override
	public String name() {return "FootballZombie";}

}
