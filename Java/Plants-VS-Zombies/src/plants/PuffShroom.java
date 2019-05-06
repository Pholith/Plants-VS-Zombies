package plants;


import java.util.*;

import base.Vector2;
import main.GameManager;
import projectiles.PuffProjectile;
import zombies.Zombie;

public class PuffShroom extends AttackingShroom {

	public PuffShroom(Vector2 position) {
		super(100, position, 3f, "plants/puffshroom.png", 4f);
	}


	public static int getCost() {
    	return 0;
	}
    
    private float attackSpeedCount = 0;

    @Override
    public String name() {return "PuffShroom";}

    @Override
    public void attack(Vector2 position) {
    	new PuffProjectile(position, 3f);
    }
    
    @Override
    public boolean conditionOfAttacking() {
		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		return (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 3f);
    }
}