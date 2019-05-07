package plants;

import base.Vector2;
import main.GameManager;

public class AttackingPlant extends Plant {

	public AttackingPlant(int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, animationPath , animationSpeed);
	}


	public static int getCost() {
    	return 0;
	}
    
    private float attackSpeedCount = 0;

    @Override
    public String name() {return "AttackingPlant";}

    public boolean conditionToAttack() {
    	return (GameManager.getInstance().getFirstZombie(this) != null);
    }
    @Override
    public void update() {
    	super.update();
    	// lance des projectiles si la ligne n'est pas vide
    	if (conditionToAttack()) {
        	attackSpeedCount += GameManager.getInstance().getDeltatime();
  
	    	if(attackSpeedCount >= 0.8f) {
	    		attack(getPosition());
	    		attackSpeedCount = 0;
	    	}
    	}
    }
    
    
    public void attack(Vector2 position) {
    	
    }
    
}