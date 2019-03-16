package projectiles;


import java.util.*;

import base.GameObject;

/**
 * 
 */
public class Projectile extends GameObject {

    /**
     * Default constructor
     */
    public Projectile(String tag, float speed) {
		super(tag);
		this.speed = speed;
	}

	/**
     * 
     */
    private float speed;

    /**
     * 
     */


}