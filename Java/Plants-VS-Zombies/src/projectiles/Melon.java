package projectiles;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class Melon extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745884236059424761L;
	
	public Melon(Vector2 position, Zombie target) {
		super(position, 15, "plants/Melon.png", target);
	}
	
    @Override
	public String name() { return "Melon"; }

    @Override
	public void hit(Zombie z) {
    	super.hit(z);
    	
    	var zombies = GameManager.getInstance().getZombieArround(z, 1.5f);
    	for (Zombie zombie : zombies) {
			zombie.takeDammage(30);
		}
    }

}
