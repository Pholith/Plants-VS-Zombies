package plants;

import java.util.*;

import base.LivingEntity;
import base.Vector2;

/**
 * 
 */
public class Plant extends LivingEntity {

    /**
     * Default constructor
     */

	public Plant(int health, int cost, float reloadTime) {
		super(health, null, null);
		this.cost = cost;
		this.reloadTime = reloadTime;
	}


    /**
     * 
     */
    private int cost;



	/**
     * 
     */
    private float reloadTime;


}