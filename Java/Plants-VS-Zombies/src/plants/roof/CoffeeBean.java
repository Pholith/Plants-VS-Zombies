package plants.roof;

import java.util.Objects;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.Plant;
import plants.night.Shroom;

public class CoffeeBean extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6437048781022792380L;

	private final Shroom linkedShroom;

	public CoffeeBean(Vector2 position) {
		super(150, position, EnumReloadTime.slow, 50,"plants/CoffeeBean.png", 3f);
		linkedShroom = Objects.requireNonNull((Shroom) GameManager.getResources().searchShroomInTerrain((int)position.getX(), (int)position.getY()), "The coffee bean can't have a null linkedShroom");
	}

	private double delayBeforeWakeUp = 4f;
	@Override
	public void update() {
		super.update();
		delayBeforeWakeUp -= GameManager.getInstance().getDeltatime();

		if (delayBeforeWakeUp <= 0) {
			linkedShroom.wakeUp();
			destroy();
		}
	}


	@Override
	public String name() {return "CoffeeBean";}


}