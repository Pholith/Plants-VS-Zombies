package plants.day;


import java.util.*;

import base.Vector2;
import main.GameManager;
import main.Resources;
import plants.Plant;
import ui.UI_Sun;

public class Sunflower extends Plant {

    
    public Sunflower(Vector2 position) {
		super(100, position, 15f, "plants/sunflower.png", 2f);
		productionDelay = 15;
	}    
	private int productionDelay;
    private float production = 0;

    @Override
    public String name() {return "Sunflower";}

	public static int getCost() {
    	return 50;
	}
	

    @Override
    public void update() {
    	super.update();
    	if(production >= productionDelay) {
        	new UI_Sun(getPosition(), func -> {GameManager.getInstance().getResources().getASun();} );     	
        	production = 0;
    	}
    	production += GameManager.getInstance().getDeltatime();
    	
    }
}