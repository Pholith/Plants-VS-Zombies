package plants;

import base.Vector2;
import projectiles.Peash;

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
