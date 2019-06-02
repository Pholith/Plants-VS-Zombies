package plants.fog;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;

public class Pumpkin extends Plant {

	public Pumpkin(Vector2 position) {
		super(500, position, EnumReloadTime.slow, 125, "plants/Pumpkin1.png", 1f);

	}

	@Override
    public String name() {return "Pumpkin";}

	@Override
	public void update() {
		super.update();
		if (getHealth() < 200) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/Pumpkin3.png"));
			
		} else if (getHealth() < 400) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/Pumpkin2.png"));
		}
	}

}
