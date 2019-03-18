package plants;


import java.util.*;

import base.Vector2;
import main.GameManager;

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
       
    
    int test = 0;
    
    
    @Override
    public void update() {
    	// lance des projectiles si la ligne n'est pas vide
    	test++;
    	//if (GameManager.getInstance().getFirstEnemy(this) != null) {
    	if(test % 10 == 0)
    		attack(getPosition());
    	//}
    }
    
    
    public void attack(Vector2 position) {
    	
    }
    
}