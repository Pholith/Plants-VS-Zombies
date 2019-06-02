package zombies;

import base.Vector2;
import projectiles.Projectile;

public abstract class ProtectedZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584863804058794081L;

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
			if (healthOfProtection <= 0) { // Si la protection se casse
				doOnLoseProtection();
				return Math.abs(healthOfProtection - dammage);
			}
		}
		return 0;
	}
	@Override
	public boolean takeMetalProtection() {
		if (healthOfProtection <= 0) {
			return false;
		}
		System.out.println("A protection was took by a MagnetShroom");
		healthOfProtection = 0;
		return true;
	}
	
	public void doOnLoseProtection() {
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public boolean haveScreenDoor() {
		return false;
	}

}

