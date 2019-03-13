package plants;

import java.util.*;

import base.LivingEntity;

/**
 * 
 */
public class Plant extends LivingEntity {

    /**
     * Default constructor
     */

	public Plant(int health, int cost, float reloadTime) {
		super(health);
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