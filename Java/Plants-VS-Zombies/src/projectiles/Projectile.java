package projectiles;

import base.LivingEntity;

public interface Projectile {


	public void hit(LivingEntity z);

	public boolean canByPassScreenDoor();

	public int getDammage();
}
