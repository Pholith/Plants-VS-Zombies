package base;


import java.io.Serializable;
import java.util.*;

import plants.AttackingPlant;
import plants.fog.Pumpkin;
import plants.night.AttackingShroom;
import plants.night.ExplodingShroom;
import plants.night.Shroom;
import plants.pool.LilyPad;
import props.Fog;
import props.Gravestone;
import ui.UI_Sprite;

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
	
	private Fog fog;
	
	
	
    public Square(int x, int y, boolean inWater, boolean inFog) {
    pos = Terrain.caseToPosition(x,y);    
    this.inWater = inWater;
    contain = new ArrayList<LivingEntity>();
    if(inFog) {
    	fog = new Fog(pos);
    }
    
    }
    
    
    public Square(int x, int y) {
    	this(x, y, false, false);
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
    

    
    
    public void setActiveFog(boolean val, boolean set) {    	
    	if(fog == null)
    		return;    	
    	if(set)
    	fog.setRenderActive(val);   
    	else
    	fog.setRenderActive(val && fog.isRenderActive() );	
    		
    }
    
    
    
    public void destroyLast() {
    	if(contain.size() > 0) 
    		contain.get(contain.size()-1).destroy();	
    	
    }
    public boolean hasShroom() {
		for (LivingEntity livingEntity : contain) {
			var superClazz = livingEntity.getClass().getSuperclass(); // TODO rendre plus g�n�rique
			if (superClazz == Shroom.class || superClazz == ExplodingShroom.class || superClazz == AttackingShroom.class) {
				return true;
			}
		}
		return false;
    }
    
    /* Renvoie vraie si une plante normal peut �tre pos�e sur la case
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
    
    public boolean hasGravestone() {
		for (LivingEntity livingEntity : contain) {
			if (livingEntity.getClass() == Gravestone.class) {
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
    public LivingEntity getLast() {
    	if(contain.size() > 0) 
    		return contain.get(contain.size()-1);	
    	return null;
    }

}