package projectiles;


import java.util.*;

import base.GameObject;
import base.Vector2;

/**
 * 
 */
public class Projectile extends GameObject {

    /**
     * Default constructor
     */
    public Projectile(Vector2 position, Vector2 speed, int dammage) {
		super(position, "plant");
		this.speed = speed;
		this.dammage = dammage;
	}

	/**
     * 
     */
    private Vector2 speed;
    private int dammage; 

    /**
     * 
     */
    public void update() {
    	this.translation(speed);
    }

}