package projectiles;

import base.Vector2;
import zombies.Zombie;

public class SnowPeash extends Projectile {
	public SnowPeash(Vector2 position) {
		
		super(position, new Vector2(0.08f, 0), 20, "plants/snowpeash.png");
	}

    /**
     * Default constructor
     */
	@Override
	public void update() {
		super.update();
	}
    @Override
    public String name() {
    	return "SnowPeash";
    }
    @Override
    public void hit(Zombie z) {
    	super.hit(z);
    	z.slow();
    }

}
