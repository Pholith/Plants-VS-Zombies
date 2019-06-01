package plants.fog;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import projectiles.Star;
import zombies.Zombie;

public class Starfruit extends AttackingPlant {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7822852246504931423L;

	public Starfruit(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 125, "plants/Starfruit.png", 4f);

	}

    @Override
    public String name() {return "Starfruit";}

    @Override
    public boolean conditionToAttack() {
    	var zombies = GameManager.getInstance().getZombieArround(this, 7);
    	return zombies.size() > 0;
    }
    
	@Override
	public void attack(Vector2 position, Zombie zombie) {
		float speed = 0.04f;
		
		new Star(getPosition(), new Vector2(speed  , speed   ));
		new Star(getPosition(), new Vector2(speed  , -speed  ));
		new Star(getPosition(), new Vector2(-speed , 0       ));
		new Star(getPosition(), new Vector2(0	   , speed   ));
		new Star(getPosition(), new Vector2(0      , -speed  ));
	}


}
