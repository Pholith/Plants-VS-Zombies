package plants.day;


import java.util.*;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import main.Resources;
import plants.Plant;
import ui.UI_Sun;

public class Sunflower extends Plant {

    
    public Sunflower(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 50, "plants/sunflower.png", 2f);
		productionDelay = 18;
	}    
	private int productionDelay;
    private float production = 12; // La sunflower produit son premier soleil plus rapidement

    @Override
    public String name() {return "Sunflower";}
	

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