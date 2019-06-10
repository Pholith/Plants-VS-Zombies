package plants.night;


import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import ui.UI_LittleSun;
import ui.UI_Sun;

public class SunShroom extends Shroom {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4692640622404725591L;

	public SunShroom(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 25, "plants/SunShroom.png", 4f);
		productionDelay = 17;
		growDelay = 40;
	}    

	private final int productionDelay;
	private final int growDelay;

	private float grow = 0;
	private float production = 10;  // La sunflower produit son premier soleil plus rapidement

	@Override
	public String name() {return "SunShroom";}


	private boolean isBig() {
		return grow >= growDelay;
	}


	@Override
	public void update() {
		super.update();
		if (!isSleeping()) {

			if(production >= productionDelay) {
				if (isBig()) {
					new UI_Sun(getPosition(), func -> { GameManager.getResources().getASun(50);} );     	
				} else {
					new UI_LittleSun(getPosition(), func -> { GameManager.getResources().getASun(25);} );     	
				}
				production = 0;
			}
			if (!isBig()) {
				grow += GameManager.getInstance().getDeltatime();
			}
			production += GameManager.getInstance().getDeltatime();
		}

	}
}