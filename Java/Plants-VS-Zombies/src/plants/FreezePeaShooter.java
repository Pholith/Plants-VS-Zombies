package plants;

import base.Vector2;
import projectiles.SnowPeash;

public class FreezePeaShooter extends AttackingPlant {
    
	public FreezePeaShooter(Vector2 position) {
		super(100, position, 3f, 50, 10f, "plants/freeze_pea_shooter.png", 4f);

	}
	@Override
    public String name() {return "FreezePeaShooter";}

	public static int getCost() {
    	return 175;
	}

	@Override
	public void attack(Vector2 position) {
		new SnowPeash(position.add(0.2f, 0.1f)); // fait planter le truc pour le moment : A FIX
	}

}
