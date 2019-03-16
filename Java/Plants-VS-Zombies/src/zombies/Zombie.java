package zombies;


import java.util.*;

import base.LivingEntity;
import base.Sprite;
import base.Vector2;

/**
 * 
 */
public class Zombie extends LivingEntity {


	public Zombie(int health, Vector2 position, String animationPath, float animSpeed, float speed) {
		super(health, position, animationPath, animSpeed, "zombie");
		this.speed = -speed/100; // pour avoir une petite vitesse sans avoir des 0.00002f
	}

	/**
     * 
     */
    private float speed;

    @Override
    public void start() {
    	System.out.println("Zombie created");
    }
    
    @Override
    public void update() {
    	
    	//if (getPosition())
    	this.translation(new Vector2(speed, 0f));
    	System.out.println(5);
    	
    }
}