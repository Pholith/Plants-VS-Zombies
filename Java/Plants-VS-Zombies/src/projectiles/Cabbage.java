package projectiles;

import base.Vector2;
import zombies.Zombie;

public class Cabbage extends LobProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223280193823263400L;
	public Cabbage(Vector2 position, Zombie target) {
		super(position, new Vector2((target.getPosition().getX()-position.getX()-0.3f)/100 , -0.05f), 15, "plants/Cabbage.png", target);
		this.target = target;
	}
	
	private Zombie target;
	@Override
	public void update() {
		super.update();
		System.out.println(target);
	}
	
    @Override
	public String name() { return "Cabbage"; }

}
