package plants.night;

import base.Vector2;
import main.GameManager;
import projectiles.Fume;
import zombies.Zombie;

public class FumeShroom extends AttackingShroom {

	public FumeShroom(Vector2 position) {
		super(100, position, 5f, "plants/FumeShroom.png", 3f);
	}

	public static int getCost() {
    	return 75;
	}
   
    @Override
    public String name() {return "FumeShroom";}

    @Override
    public void attack(Vector2 position) {
    	new Fume(position);
    }
    
    @Override
    public boolean conditionOfAttacking() {
		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		return (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 3f);
    }

}
