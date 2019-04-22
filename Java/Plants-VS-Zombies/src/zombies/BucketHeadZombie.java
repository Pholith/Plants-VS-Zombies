package zombies;

import base.Vector2;

public class BucketHeadZombie extends ProtectedZombie {

	public BucketHeadZombie(Vector2 position) {
		super(100, position, "zombies/buckethead.png", 5f, 1f, 300);
		
	}
	@Override
    public String name() {return "BucketHeadZombie";}

	@Override
	public void update() {
		super.update();
	}

}