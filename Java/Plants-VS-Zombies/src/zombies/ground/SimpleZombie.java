package zombies.ground;


import base.Vector2;
import zombies.Zombie;

/**
 * 
 */
public class SimpleZombie extends Zombie {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2444536967794211643L;
	public SimpleZombie(Vector2 position) {
		super(100, position, "zombies/SimpleZombie.png", 5f, 1f);

	}
	@Override
    public String name() {return "SimpleZombie";}


    
}