package plants.fog;

import base.Vector2;
import main.GameManager;
import plants.AttackingPlant;
import plants.Plant;
import props.Gravestone;

public class GraveBuster extends Plant {

	public GraveBuster( Vector2 position) {
		super(100, position, 1f, 75 , "plants/GraveBuster.png", 1f);
	}
	
	private Gravestone linkedGreave;
	private float waitTime;
	
	
	@Override
	public void update() {
		waitTime+= GameManager.getInstance().getDeltatime();
		
		if(waitTime >= 10f) {
			destroy();
		}
	}
	
	
	

	
}
