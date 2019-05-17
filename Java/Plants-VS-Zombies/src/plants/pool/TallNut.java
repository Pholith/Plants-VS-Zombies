package plants.pool;


import java.util.*;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;

public class TallNut extends Plant {

	public TallNut(Vector2 position) {
		super(1000, position, EnumReloadTime.slow, 125,"plants/TallNut.png", 3f);
	}

	@Override
    public String name() {return "TallNut";}

	boolean changed = false;
	@Override
	public void update() {
		super.update();
		if (!changed && getHealth() < 300) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/TallNut_cracked.png"));
		}
	}
	
}