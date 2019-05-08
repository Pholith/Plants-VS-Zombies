package plants.pool;

import base.Vector2;
import plants.Plant;
import projectiles.Pea;

public class LilyPad extends Plant {

	public LilyPad(Vector2 position) {
		super(50, position, 3f, "plants/LilyPad.png", 1f);
	}

	public static int getCost() {
    	return 25;
	}

    @Override
    public String name() {return "LilyPad";}
}
