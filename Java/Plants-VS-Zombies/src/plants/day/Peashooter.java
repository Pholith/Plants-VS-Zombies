package plants.day;

import base.Vector2;
import plants.AttackingPlant;
import projectiles.Pea;
import zombies.Zombie;

public class Peashooter extends AttackingPlant {

	public Peashooter(Vector2 position) {
		super(100, position, 3f, "plants/pea_shooter.png", 4f);

	}

	public static int getCost() {
    	return 100;
	}

    @Override
    public String name() {return "Peashooter";}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Pea(position.add(0.2f, 0.1f));
	}
}