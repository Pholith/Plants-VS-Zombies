package plants;

import base.Terrain;
import base.Vector2;
import main.GameManager;
import projectiles.Peash;

public class Threepeater extends AttackingPlant {

	public Threepeater(Vector2 position) {
		super(100, position, 3f, "plants/Threepeater.png", 4f);

	}

	public static int getCost() {
    	return 325;
	}

    @Override
    public String name() {return "Threepeater";}

	@Override
	public void attack(Vector2 position) {
		var newPosition = position.add(0.2f, 0.1f);
		new Peash(newPosition);
		new Peash(newPosition.add(0,  1));
		new Peash(newPosition.add(0, -1));
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