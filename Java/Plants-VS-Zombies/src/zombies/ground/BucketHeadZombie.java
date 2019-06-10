package zombies.ground;

import base.Vector2;
import zombies.ProtectedZombie;

public class BucketHeadZombie extends ProtectedZombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8836941286440185051L;

	public BucketHeadZombie(Vector2 position) {
		super(100, position, "zombies/BucketHeadZombie.png", 5f, 1f, 300);

	}
	@Override
	public String name() {return "BucketHeadZombie";}

}