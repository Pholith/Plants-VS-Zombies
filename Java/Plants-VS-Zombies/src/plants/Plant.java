package plants;

import java.util.*;

import base.LivingEntity;
import base.Sprite;
import base.Vector2;
import main.GameManager;

/**
 * 
 */
public abstract class Plant extends LivingEntity {

    /**
     * Default constructor
     */

	public Plant( int health, Vector2 position, int cost, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, animationPath, animationSpeed + (float)Math.random());
		this.cost = cost;
		this.reloadTime = reloadTime;
	}


    /**
     * 
     */
    private int cost;

    @Override
    public String name() {return "Plant";}


	@Override
	public boolean isPlant() {
		return true;
	}
	/**
     * 
     */
    private float reloadTime;


}