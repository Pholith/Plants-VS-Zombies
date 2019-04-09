package plants;


import java.util.*;

import base.Vector2;
import main.GameManager;

/**
 * 
 */
public class Sunflower extends Plant {

	
	
	

    private int production;

    /*
	public Sunflower(int health, Vector2 position, int cost, float reloadTime, String animationPath,
			float animationSpeed) {
		super(health, position, cost, reloadTime, animationPath, animationSpeed);
	}
    */
    
    public Sunflower(Vector2 position) {
		super(100, position, 50, 15f, "plants/sunflower.png", 2f);
	}    
    
    @Override
    public String name() {return "Sunflower";}

float timer = 0;
    
    @Override
    public void update() {
    	if(timer >= 10f) {
    		GameManager.getResources().spawnSun(getPosition());
    		timer = 0;
    	}
    	timer += GameManager.getInstance().getDeltatime();
    	
    }
	


}