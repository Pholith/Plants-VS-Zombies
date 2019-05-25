package zombies.ground;

import base.Vector2;
import zombies.ProtectedZombie;

public class NewspaperZombie extends ProtectedZombie {

	private static final long serialVersionUID = 6869274534723805321L;

	public NewspaperZombie(Vector2 position) {
		super(100, position, "zombies/NewspaperZombie.png", 5f, 1f, 50);
		
		}
	@Override
	public String name() {return "NewspaperZombie";}

	@Override
	public void doOnLoseProtection() {
		addSpeed(1f);
	}
}
