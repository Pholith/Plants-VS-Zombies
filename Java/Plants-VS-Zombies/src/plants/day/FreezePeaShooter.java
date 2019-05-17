package plants.day;

import base.Vector2;
import enums.EnumReloadTime;
import plants.AttackingPlant;
import projectiles.SnowPea;
import zombies.Zombie;

public class FreezePeaShooter extends AttackingPlant {
    
	public FreezePeaShooter(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 175, "plants/freeze_pea_shooter.png", 4f);

	}
	@Override
    public String name() {return "FreezePeaShooter";}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new SnowPea(position.add(0.2f, 0.1f));
	}

}
