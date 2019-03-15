package plants;


import java.util.*;

import base.Vector2;

/**
 * 
 */
public class AttackingPlant extends Plant {

    /**
     * Default constructor
     */

	public AttackingPlant(int health, Vector2 position, int cost, float reloadTime, int damages, float range,  String animationPath, float animationSpeed) {
		super(health, position, cost, reloadTime, animationPath , animationSpeed);
		this.damages = damages;
		this.range = range;
	}


    /**
     * 
     */
    private float damages;




	/**
     * 
     */
    private float range;


}