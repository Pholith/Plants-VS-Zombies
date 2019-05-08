package zombies;

import base.Vector2;
import projectiles.LineProjectile;
import projectiles.Projectile;

public abstract class ProtectedZombie extends Zombie {

	public ProtectedZombie(int health, Vector2 position, String animationpath, float animSpeed, float speed, int healthOfProtection) {
		super(health, position, animationpath, animSpeed, speed);
		this.healthOfProtection = healthOfProtection;		
	}

	private int healthOfProtection;
	
	@Override
	public int onTakeDammage(int dammage, Projectile p) {
		
		if (p != null && haveScreenDoor() &&  p.canByPassScreenDoor()) {
			return dammage;
		} else {
			
			if (healthOfProtection <= 0) {
				return dammage;
			}
			healthOfProtection -= dammage;
			if (healthOfProtection <= 0) {
				return Math.abs(healthOfProtection - dammage);
			}
		}
		return 0;
	}
	@Override
	public void update() {
		super.update();
	}
	
	public boolean haveScreenDoor() {
		return false;
	}

}

