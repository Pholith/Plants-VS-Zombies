package plants;

import base.LivingEntity;
import base.Square;
import base.Vector2;
import main.GameManager;


public abstract class Plant extends LivingEntity {

    private Square associatedSquare;

    
	public Plant( int health, Vector2 position, float reloadTime, int cost, String animationPath, float animationSpeed) {
		super(health, position, animationPath, animationSpeed + (float)Math.random());
		this.reloadTime = reloadTime;
		this.cost = cost;
		associatedSquare = GameManager.getResources().addEntityToTerrain((int)position.getX(), (int)position.getY(), this);
	}


 
	// renvoie le prix d'une plante
	public int cost;
    public int getCost() {
    	return cost;
    }
    
    @Override
    public String name() {return "Plant";}


	@Override
	public boolean isPlant() {
		return true;
	}

	private float reloadTime;
    public float getReloadTime() {
    	return reloadTime;
    }
    
    @Override
    public void onDestroy() {
    if(associatedSquare != null)
    	associatedSquare.removeEnt(this);
    }
}