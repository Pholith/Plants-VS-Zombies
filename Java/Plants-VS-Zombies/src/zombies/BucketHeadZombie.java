package zombies;

import base.Vector2;

public class BucketHeadZombie extends Zombie {

	public BucketHeadZombie(Vector2 position) {
		super(100, position, "zombies/buckethead.png", 5f, 1f);
		
	}
	@Override
    public String name() {return "BucketHeadZombie";}

	private int healthOfBucket = 300;
	
	@Override
	public int onTakeDammage(int dammage) {
		if (healthOfBucket <= 0) {
			return dammage;
		}
		healthOfBucket -= dammage;
		if (healthOfBucket <= 0) {
			return Math.abs(healthOfBucket - dammage);
		}
		return 0;
	}
	@Override
	public void update() {
		super.update();
	}
}