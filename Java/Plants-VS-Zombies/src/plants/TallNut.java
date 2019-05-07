package plants;


import java.util.*;

import base.Vector2;
import main.GameManager;

public class TallNut extends Plant {

	public TallNut(Vector2 position) {
		super(1000, position, 3f, "plants/TallNut.png", 3f);
	}

	public static int getCost() {
    	return 125;
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