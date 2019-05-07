package projectiles;

import java.util.HashSet;

import base.Vector2;
import zombies.Zombie;

public class Fume extends Projectile {

	public Fume(Vector2 position) {
		super(position, new Vector2(0.06f, 0), 10, "plants/Fume.png");
		alreadyAttacked = new HashSet<Zombie>();
		this.originalPosition = position;
		this.maxlenght = 3f;
	}
	
	private float maxlenght;
	private Vector2 originalPosition;

	HashSet<Zombie> alreadyAttacked;
	
	@Override
	public void update() {
		super.update();
		
		if (getPosition().getX() - originalPosition.getX() > maxlenght) {
			destroy();
		}

	}
	
	@Override
    public void hit(Zombie z) {
		if (alreadyAttacked.contains(z)) {
			return;
		}
		alreadyAttacked.add(z);
		z.takeDammage(getDammage(), this);
	}
    public boolean canByPassScreenDoor() {
    	return true;
    }

}
