package plants.roof;

import base.Vector2;
import plants.AttackingPlant;
import projectiles.Cabbage;
import zombies.Zombie;

public class CabbagePult extends AttackingPlant {

	public CabbagePult(Vector2 position) {
		super(100, position, 10f, "plants/CabbagePult.png", 2f);
	}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Cabbage(getPosition(), zombie);
	}

    public static int getCost() { return 100; }

    public String name() { return "CabbagePult"; }
}
