package projectiles;

import base.Vector2;
import zombies.Zombie;

public class Cabbage extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223280193823263400L;
	public Cabbage(Vector2 position, Zombie target) {
		super(position, 25, "plants/Cabbage.png", target);
	}
	
    @Override
	public String name() { return "Cabbage"; }

}
