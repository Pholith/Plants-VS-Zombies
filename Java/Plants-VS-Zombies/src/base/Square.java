package base;


import java.util.*;

public class Square {

	private int posX;
	private int posY;
	
	private final Vector2 pos;

	private final boolean inWater;
	
	public boolean isInWater() {
		return inWater;
	}
	
	private ArrayList<LivingEntity> contain;
	
	
	
    public Square(int x, int y, boolean inWater) {
    posX = x;
    posY = y;    
    pos = Terrain.caseToPosition(x,y);    
    this.inWater = inWater;
    contain = new ArrayList<LivingEntity>();
    }
    
    public Square(int x, int y) {
    	this(x, y, false);
    }
    
    
    public void addContain(LivingEntity ent) {
    	contain.add(ent);  
    	if(contain.size() > 1)
    		ent.translationFixed(0.1f, 0);
    }
    
    public void removeEnt(LivingEntity plant) {
    	if(contain.contains(plant)) {
    		contain.remove(plant);    		
    	}
    }
    
    
    public void destroyLast() {
    	if(contain.size() > 0) 
    		contain.get(contain.size()-1).destroy();	
    	
    }
  
    
    
	public ArrayList<LivingEntity> getContain() {
		return contain;
	}
    
    public Vector2 getPos() {
		return pos;
	}
    

}