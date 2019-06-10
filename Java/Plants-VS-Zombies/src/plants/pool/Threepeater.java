package plants.pool;

import base.Terrain;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import projectiles.Pea;
import zombies.Zombie;

public class Threepeater extends AttackingPlant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3797155690029072371L;
	public Threepeater(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 325,"plants/Threepeater.png", 4f);

	}

	@Override
	public String name() {return "Threepeater";}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		var newPosition = position.add(0.2f, 0.1f);
		new Pea(newPosition);
		new Pea(newPosition.add(0,  1));
		new Pea(newPosition.add(0, -1));
	}

	@Override
	public boolean conditionToAttack() {
		return super.conditionToAttack() 
				|| GameManager.getInstance().isZombieOnRow( (int) Terrain.positionToCase(getPosition()).getY() + 1)
				|| GameManager.getInstance().isZombieOnRow( (int) Terrain.positionToCase(getPosition()).getY() - 1);
	}
	@Override
	public void update() {
		super.update();
	}
}