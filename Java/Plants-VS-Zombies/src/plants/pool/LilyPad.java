package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import plants.Plant;
import projectiles.Pea;

public class LilyPad extends Plant {

	public LilyPad(Vector2 position) {
		super(50, position, EnumReloadTime.very_fast, 25, "plants/LilyPad.png", 1f);
	}

    @Override
    public String name() {return "LilyPad";}
}
