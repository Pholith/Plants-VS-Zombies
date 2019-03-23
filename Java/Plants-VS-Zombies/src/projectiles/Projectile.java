package projectiles;


import java.util.*;

import base.Constant;
import base.GameObject;
import base.Sprite;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;



/**
 * 
 */
public class Projectile extends GameObject {

	private Sprite defaultSprite;
	
	
    /**
     * Default constructor
     */
    public Projectile(Vector2 position, Vector2 speed, int dammage, String texturePath) {
		super(position);
		this.speed = speed;
		this.dammage = dammage;
		Sprite[] sprts = GameManager.getResources().getAnimationByPath(texturePath);
		if(sprts.length == 0)
			sprts = GameManager.getResources().getAnimationByPath(Constant.errorTexture);
		
		defaultSprite = sprts[0];
	}

	/**
     * 
     */
    private Vector2 speed;
    private int dammage; 

    /**
     * 
     */
    @Override
    public boolean isProjectile() {
    	return true;
    }
    @Override
    public void update() {
    	
    	Zombie firstEnemy =  (Zombie) GameManager.getInstance().getFirstEnemy(this);

    	// si le projectile rencontre un zombie
    	if (firstEnemy != null && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.2f) {
    		firstEnemy.takeDammage(dammage);
    		destroy();
    	}
    	translation(speed);
    }
    @Override
    public Sprite display() {  
    	return defaultSprite;
    }
    
    
    
}