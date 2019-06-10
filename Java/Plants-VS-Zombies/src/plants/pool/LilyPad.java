package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import plants.Plant;

public class LilyPad extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 428028147981568338L;

	public LilyPad(Vector2 position) {
		super(50, position, EnumReloadTime.very_fast, 25, "plants/LilyPad.png", 1f);
	}

	@Override
	public String name() {return "LilyPad";}
}
