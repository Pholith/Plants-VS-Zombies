package plants;


import java.util.*;

import base.Vector2;
import main.GameManager;

public class Sunflower extends Plant {

    
    public Sunflower(Vector2 position) {
		super(100, position, 15f, "plants/sunflower.png", 2f);
		production = 20;
	}    
	private int production;

    @Override
    public String name() {return "Sunflower";}

	public static int getCost() {
    	return 50;
	}
	

    float timer = 0;
    @Override
    public void update() {
    	if(timer >= production) {
    		GameManager.getResources().spawnSun(getPosition());
    		timer = 0;
    	}
    	timer += GameManager.getInstance().getDeltatime();
    	
    }
	


}