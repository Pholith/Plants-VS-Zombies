package plants.fog;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.night.Shroom;
import zombies.Zombie;

public class MagnetShroom extends Shroom {


	public MagnetShroom(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/MagnetShroom.png", 3f);

	}

	private static final long serialVersionUID = -1248640139519234767L;


	private double timeBeforeMagnet = 0;
	@Override
	public void update() {
		super.update();
		if (isSleeping()) {
			return;
		}

		if (timeBeforeMagnet <= 0) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/MagnetShroom.png"));

			var zombies = GameManager.getInstance().getZombieArround(this, 5);

			for (Zombie zombie : zombies) {
				if (zombie.takeMetalProtection())
				{
					timeBeforeMagnet = 10;
					break;
				}
			}

		} else {
			timeBeforeMagnet -= GameManager.getInstance().getDeltatime();
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/MagnetShroom2.png"));

		}
	}


	@Override
	public String name() {return "MagnetShroom";}

}
