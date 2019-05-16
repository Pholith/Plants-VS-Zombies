package plants.pool;

import base.Explosion;
import base.Vector2;
import main.GameManager;
import plants.Plant;
import zombies.Zombie;

public class Squash extends Plant {

	public Squash(Vector2 position) {
		super(100, position, 10f, "plants/Squash.png", 1);

	}
	
	public static int getCost() {
		return 50;
	}
	
	public void update() {
	
		var zombies = GameManager.getInstance().getZombieArround(this, 2f); // TODO améliorer pour prendre le zombie le plus à gauche
		zombies.removeIf( (o) -> { return !o.getPosition().isOnSameRow(this.getPosition()); } );
		Zombie firstEnemy = null;
		if (zombies.size() >= 1) {
			firstEnemy = (Zombie) zombies.toArray()[0];
		}
		
		if (firstEnemy != null) {
			
			for (Zombie	gameObject: GameManager.getInstance().getZombieArround(firstEnemy, 1f)) {
				gameObject.takeDammage(500);
			}
			new Explosion(firstEnemy.getPosition());
			destroy();
    	}
	}
	
	@Override
	public String name() {return "Squash";}
	
	
}
