package zombies;


import java.util.*;

import main.LivingEntity;

/**
 * 
 */
public class Zombie extends LivingEntity {

	public Zombie(int health, float speed) {
		super(health);
		this.speed = speed;
	}

    /**
     * 
     */
    private float speed;



}