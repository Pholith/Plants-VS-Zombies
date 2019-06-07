package plants.pool;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import props.Explosion;
import zombies.Zombie;

public class Squash extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658580890233503275L;

	public Squash(Vector2 position) {
		super(100, position, EnumReloadTime.medium, 50, "plants/Squash.png", 1);

	}
	
	@Override
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
