package plants;


import java.util.*;

import base.Vector2;

/**
 * 
 */
public class Sunflower extends Plant {




    private int production;

    /*
	public Sunflower(int health, Vector2 position, int cost, float reloadTime, String animationPath,
			float animationSpeed) {
		super(health, position, cost, reloadTime, animationPath, animationSpeed);
	}
    */
    
    public Sunflower(Vector2 position) {
		super(100, position, 50, 15f, "plants/sunflower.png", 2f);
	}

	
    
    /**
     * Default constructor
     */
/*
	public Sunflower(int health, int cost, float reloadTime) {
		super(health, cost, reloadTime);
		// TODO Auto-generated constructor stub
	}*/


}