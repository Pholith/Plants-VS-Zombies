package projectiles;

import base.Vector2;
import zombies.Zombie;

public class SnowPea extends LineProjectile {
	
	public SnowPea(Vector2 position, boolean burned) {
		super(position, new Vector2(0.06f, 0), 10, "plants/snowpeash.png", burned);
	}
	public SnowPea(Vector2 position) {
		this(position, false);
	}

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
    @Override
    public void onBurn() {
    	super.onBurn();
    	new Pea(getPosition(), true);
    	destroy();
    }

}
