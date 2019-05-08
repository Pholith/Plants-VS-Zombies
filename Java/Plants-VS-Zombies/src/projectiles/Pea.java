package projectiles;

import base.Vector2;

public class Pea extends LineProjectile {

	public Pea(Vector2 position) {
		this(position, false);
	}

	public Pea(Vector2 position, boolean burned) {
		super(position, new Vector2(0.06f, 0), 10, "plants/peash.png", burned);
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
    	return "Peash";
    }
    @Override
    public void onBurn() {
    	super.onBurn();
    	new FirePea(getPosition());
    	destroy();
    }

}