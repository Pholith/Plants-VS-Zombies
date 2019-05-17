package plants;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public abstract class AttackingPlant extends Plant {

	public AttackingPlant(int health, Vector2 position, float reloadTime, int cost, String animationPath, float animationSpeed) {
		super(health, position, reloadTime, cost, animationPath , animationSpeed);
	}

	private float attackSpeedCount = 0;

    @Override
    public String name() {return "AttackingPlant";}

    @Override
    public void update() {
    	super.update();
    	// lance des projectiles si la ligne n'est pas vide
    	if (conditionToAttack()) {
        	attackSpeedCount += GameManager.getInstance().getDeltatime();
  
	    	if(attackSpeedCount >= 0.9f) {
	    		attack(getPosition(), target);
	    		attackSpeedCount = 0;
	    	}
    	}
    }
    
    public boolean conditionToAttack() {
    	target = (Zombie) GameManager.getInstance().getFirstZombie(this);
    	return (GameManager.getInstance().getFirstZombie(this) != null);
    }
    private Zombie target;

    public void attack(Vector2 position, Zombie zombie) {
    	
    }
    public void attack(Vector2 position) {
    	attack(position, null);
    }
    
}