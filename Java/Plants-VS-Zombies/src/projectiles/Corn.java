package projectiles;

import base.Vector2;
import zombies.Zombie;

public class Corn extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2745884236059424761L;
	
	public Corn(Vector2 position, Zombie target) {
		super(position, new Vector2((target.getPosition().getX()-position.getX()-0.3f)/100 , -0.05f), 15, "plants/Corn.png", target);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
    @Override
	public String name() { return "Corn"; }

    @Override
	public void hit(Zombie z) {
    	super.hit(z);
    	
    	if (Math.random() > 0.3d) {
    		z.stun();
    		z.takeDammage(20);
    	}
    }

}
