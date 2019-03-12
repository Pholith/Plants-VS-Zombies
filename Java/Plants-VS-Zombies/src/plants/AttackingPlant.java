package plants;


import java.util.*;

/**
 * 
 */
public class AttackingPlant extends Plant {

    /**
     * Default constructor
     */

	public AttackingPlant(int health, int cost, float reloadTime, int dommage, int range) {
		super(health, cost, reloadTime);
		this.dommage = dommage;
		this.range = range;
	}


    /**
     * 
     */
    private int dommage;




	/**
     * 
     */
    private int range;


}