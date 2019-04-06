package zombies;


import java.util.*;

import base.LivingEntity;
import base.Sprite;
import base.Vector2;
import main.GameManager;
import plants.Plant;

/**
 * 
 */
public abstract class Zombie extends LivingEntity {


	public Zombie(int health, Vector2 position, String animationPath, float animSpeed, float speed) {
		super(health, position, animationPath, animSpeed + (float)Math.random());
		this.speed = -speed/200; // pour avoir une petite vitesse sans avoir des 0.00002f
	
	}

	/**
     * 
     */
    private float speed;
    private int dammage = 25;
    @Override
    public String name() {return "Zombie";}
    
    @Override
    public void start() {
    }

    @Override
    public boolean isZombie() {
    	return true;
    }
    private float eatCouldown = 0;

    
    @Override
    public void update() {
    	
    	Plant firstEnemy = (Plant) GameManager.getInstance().getFirstPlant(this);
    	// si le zombie rencontre une plante devant lui et assez proche, il s'arrête pour la manger
    	if (firstEnemy != null && firstEnemy.getPosition().getX() > this.getPosition().getX() -1) {
    	
    		eatCouldown += GameManager.getInstance().getDeltatime();
    		
    		if (eatCouldown >= 2f) {
        		firstEnemy.takeDammage(dammage);
        		eatCouldown = 0;
			}
    		
    		
    	}
    	else {
    		this.translation(new Vector2(speed, 0f));
    	}
    	if (this.getPosition().getX() < 2f) {
			GameManager.getInstance().endGame(false);
		}
    }
}