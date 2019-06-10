package zombies.ground;


import base.Vector2;
import zombies.Zombie;

public class ConeheadZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -765088847610181539L;
	public ConeheadZombie(Vector2 position) {
		super(200, position, "zombies/ConeheadZombie.png", 5f, 1f);

	}
	@Override
	public String name() {return "ConeheadZombie";}


}