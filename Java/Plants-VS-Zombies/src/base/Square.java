package base;


import java.util.*;

import plants.Peashooter;

/**
 * 
 */
public class Square {

	int posX;
	int posY;
	
	private final Vector2 pos;
	
	
	
	private LivingEntity contain;
	
	
	
    public Square(int x, int y) {
    posX = x;
    posY = y;    
    pos = Terrain.caseToPosition(x,y);    
    }
    
    
    public void setContain(LivingEntity ent) {
    	contain = ent;    	
    }
	public LivingEntity getContain() {
		return contain;
	}
    
    public Vector2 getPos() {
		return pos;
	}
    

}