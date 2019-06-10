package plants.day;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import projectiles.Pea;
import zombies.Zombie;

public class Repeater extends AttackingPlant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6829643354897157540L;

	public Repeater(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 200, "plants/Repeater.png", 1.75f);
	}

	@Override
	public String name() {return "Repeater";}


	private final float secondShootDelay = 0.2f;
	private float secondShoot = -1f;

	@Override
	public void update() {
		super.update();
		if (secondShoot != -1) {
			secondShoot += GameManager.getInstance().getDeltatime();
		}
		if (secondShoot > secondShootDelay) {
			attack(this.getPosition());
			secondShoot = -1;
		}
	}
	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Pea(position.add(0.2f, 0.1f));
		secondShoot = 0;
	}

}