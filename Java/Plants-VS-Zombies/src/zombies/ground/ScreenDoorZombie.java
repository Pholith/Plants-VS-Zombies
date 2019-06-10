package zombies.ground;


import base.Vector2;
import zombies.ProtectedZombie;

public class ScreenDoorZombie extends ProtectedZombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6869274534723805321L;

	public ScreenDoorZombie(Vector2 position) {
		super(100, position, "zombies/ScreenDoorZombie.png", 5f, 1f, 300);

	}
	@Override
	public String name() {return "ScreenDoorZombie";}

	@Override
	public boolean haveScreenDoor() {
		return true;
	}
}