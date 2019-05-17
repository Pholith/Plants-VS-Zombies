package plants.day;

import base.Vector2;
import enums.EnumReloadTime;
import plants.AttackingPlant;
import projectiles.Pea;
import zombies.Zombie;

public class Peashooter extends AttackingPlant {

	public Peashooter(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/pea_shooter.png", 4f);

	}

    @Override
    public String name() {return "Peashooter";}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Pea(position.add(0.2f, 0.1f));
	}
}