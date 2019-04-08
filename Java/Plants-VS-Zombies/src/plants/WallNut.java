package plants;


import java.util.*;

import base.Vector2;

/**
 * 
 */
public class WallNut extends Plant {

	public WallNut(Vector2 position) {
		super(600, position, 50, 3f, "plants/wallNut.png", 3f);

	}

	@Override
    public String name() {return "WallNut";}

	
}