package base;


import java.io.Serializable;
import java.util.*;

import plants.AttackingPlant;
import plants.fog.Pumpkin;
import plants.night.AttackingShroom;
import plants.night.ExplodingShroom;
import plants.night.Shroom;
import plants.pool.LilyPad;

public class Square implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 973040957361362706L;

	private final Vector2 pos;

	private final boolean inWater;
	
	public boolean isInWater() {
		return inWater;
	}
	
	private ArrayList<LivingEntity> contain;
	
	
    public Square(int x, int y, boolean inWater) {
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
    public boolean hasShroom() {
		for (LivingEntity livingEntity : contain) {
			var superClazz = livingEntity.getClass().getSuperclass(); // TODO rendre plus générique
			if (superClazz == Shroom.class || superClazz == ExplodingShroom.class || superClazz == AttackingShroom.class) {
				return true;
			}
		}
		return false;
    }
    
    /* Renvoie vraie si une plante normal peut être posée sur la case
     * 
     */
    public boolean canBePlacedNewGroundPlant() {
    	// bloc d'eau
    	if (inWater) {
    		if (hasLilyPad()) {
    			
				if (hasPumpkin()) {
					return contain.size() < 3;
				}
				return contain.size() < 2;

			}
			return false;
    	}
    	// bloc normal
    	if (hasPumpkin()) {
			return contain.size() < 2;
		}
		return contain.size() < 1;
    	
    }
    public boolean hasPumpkin() {
		for (LivingEntity livingEntity : contain) {
			if (livingEntity.getClass() == Pumpkin.class) {
				return true;
			}
		}
		return false;
    }
    
    public boolean hasLilyPad() {
		for (LivingEntity livingEntity : contain) {
			if (livingEntity.getClass() == LilyPad.class) {
				return true;
			}
		}
		return false;	
    }
    
	public ArrayList<LivingEntity> getContain() {
		return contain;
	}
    
    public Vector2 getPos() {
		return pos;
	}
    

}