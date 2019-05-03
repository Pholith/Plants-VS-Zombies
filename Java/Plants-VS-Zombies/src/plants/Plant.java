package plants;

import java.util.*;

import base.LivingEntity;
import base.Sprite;
import base.Square;
import base.Vector2;
import main.GameManager;


public abstract class Plant extends LivingEntity {

    private Square associatedSquare;

    
	public Plant( int health, Vector2 position, float reloadTime, String animationPath, float animationSpeed) {
		super(health, position, animationPath, animationSpeed + (float)Math.random());
		this.reloadTime = reloadTime;
		associatedSquare = GameManager.getResources().addEntityToTerrain((int)position.getX(), (int)position.getY(), this);
	}


 
	// renvoie le prix d'une plante
    public static int getCost() {
    	return 0;
    }
    
    @Override
    public String name() {return "Plant";}


	@Override
	public boolean isPlant() {
		return true;
	}
	/* TO DELETE si Julien n'en a pas besoin
	public boolean isAquatic() {
		return false;
	}
	public boolean isLilyPad() {
		return false;
	}
	public boolean canBePlacedOnLilyPad() {
		return true;
	}*/

    private float reloadTime;
 
    
    @Override
	   public void onDestroy() {
	    if(associatedSquare != null)
	    	associatedSquare.setContain(null);
	   }
}