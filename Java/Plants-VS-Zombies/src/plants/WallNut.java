package plants;


import java.util.*;

import base.Vector2;

public class WallNut extends Plant {

	public WallNut(Vector2 position) {
		super(500, position, 3f, "plants/wallNut.png", 3f);
	}

	public static int getCost() {
    	return 50;
	}
	

	@Override
    public String name() {return "WallNut";}

	
}