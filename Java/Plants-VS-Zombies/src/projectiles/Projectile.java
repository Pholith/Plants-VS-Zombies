package projectiles;

import zombies.Zombie;

public interface Projectile {

	
    public void hit(Zombie z);

	public boolean canByPassScreenDoor();
    
	public int getDammage();
}
