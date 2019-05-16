package plants.pool;

import base.Explosion;
import base.Vector2;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

public class TangleKelp extends Plant {

	public TangleKelp(Vector2 position) {
		super(100, position, 10f, "plants/TangleKelp.png", 3f);
	}
	
	public static int getCost() {
    	return 25;
	}
	
	public void update() {

		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() +0.5f) {
			firstEnemy.destroy();
			new Explosion(getPosition());
			destroy();
    	}
				
	}
	
	@Override
    public String name() {return "TangleKelp";}
}