package projectiles;

import base.Vector2;

public class Star extends LineProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5724073530888738802L;


	public Star(Vector2 position, Vector2 speed) {
		super(position, speed, 10, "plants/Star.png");
	}


    @Override
    public String name() {
    	return "Star";
    }

}
