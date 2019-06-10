package plants.roof;

import base.Vector2;
import enums.EnumReloadTime;
import plants.Plant;

public class FlowerPot extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7757667393237619807L;

	/**
	 * 
	 */

	public FlowerPot(Vector2 position) {
		super(50, position, EnumReloadTime.very_fast, 25, "plants/FlowerPot.png", 1f);
	}

    @Override
    public String name() {return "FlowerPot";}
}
