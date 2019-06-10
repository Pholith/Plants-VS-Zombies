package plants.day;


import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import props.Explosion;
import zombies.Zombie;


public class CherryBomb extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1765822425004079617L;


	public CherryBomb(Vector2 position) {
		super(100, position, EnumReloadTime.slow, 150, "plants/cherryBomb.png", 6f);
	}


	private float timeExplode = 0;


	@Override
	public void update() {

		if (timeExplode > 1f) {
			for (Zombie	gameObject: GameManager.getInstance().getZombieArround(this)) {
				gameObject.takeDammage(500);
			}
			new Explosion(getPosition());
			destroy();
		}

		timeExplode += GameManager.getInstance().getDeltatime();
	}


	@Override
	public String name() {return "CherryBomb";}


}