package zombies;


import java.util.*;

import base.LivingEntity;
import base.Sprite;
import base.Vector2;
import main.GameManager;

/**
 * 
 */
public class Zombie extends LivingEntity {


	public Zombie(int health, Vector2 position, String animationPath, float animSpeed, float speed) {
		super(health, position, animationPath, animSpeed + (float)Math.random());
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
    public boolean isZombie() {
    	return true;
    }
    
    @Override
    public void update() {
    	
    	//System.out.println(GameManager.getInstance().getFirstEnemy(this));

	var firstEnemy = GameManager.getInstance().getFirstEnemy(this);
    	// si le zombie rencontre une plante devant lui et assez proche, il s'arrête pour la manger
    	if (firstEnemy != null && firstEnemy.isPlant() && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.6f) {
    		// TODO
    	}
    	else
    		this.translation(new Vector2(speed, 0f));
    }
}