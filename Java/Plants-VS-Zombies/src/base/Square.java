package base;


import java.util.*;

import main.GameManager;
import plants.Peashooter;
import ui.UI_Sprite;

/**
 * 
 */
public class Square {

	private int posX;
	private int posY;
	
	private final Vector2 pos;
	private GameObject associatedLillyPad;
	
	private final boolean inWater;
	
	public boolean isInWater() {
		return inWater;
	}
	
	private LivingEntity contain;
	
	
	
    public Square(int x, int y, boolean inWater) {
    posX = x;
    posY = y;    
    pos = Terrain.caseToPosition(x,y);    
    this.inWater = inWater;
    }
    
    public Square(int x, int y) {
    	this(x, y, false);
    }
    
    
    public void setContain(LivingEntity ent) {
    	contain = ent;  
    	
    	if(inWater) {
    	if(ent != null) {
    		if(associatedLillyPad == null)
    			associatedLillyPad = new UI_Sprite(Terrain.caseToPosition(posX, posY),  GameManager.getResources().getAnimationByPath("plants/LilyPad.png")[0]);
    			GameManager.getInstance().invertLastGameObjectQueue();
    	}else {
    		if(associatedLillyPad != null)
    			associatedLillyPad.destroy();
    	}
    }
    }
    
    
	public LivingEntity getContain() {
		return contain;
	}
    
    public Vector2 getPos() {
		return pos;
	}
    

}