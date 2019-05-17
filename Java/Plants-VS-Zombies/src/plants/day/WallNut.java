package plants.day;


import java.util.*;

import base.Vector2;
import enums.EnumReloadTime;
import plants.Plant;

public class WallNut extends Plant {

	public WallNut(Vector2 position) {
		super(500, position, EnumReloadTime.slow, 50, "plants/wallNut.png", 3f);
	}

	@Override
    public String name() {return "WallNut";}

	
}