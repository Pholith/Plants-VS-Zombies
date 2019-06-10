package plants;

import base.GameObject;
import base.LivingEntity;
import base.Square;
import base.Vector2;
import main.GameManager;
import ui.UI_Sprite;


public abstract class Plant extends LivingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7281120931319503073L;
	private final Square associatedSquare;


	public Plant( int health, Vector2 position, float reloadTime, int cost, String animationPath, float animationSpeed) {
		super(health, position, animationPath, animationSpeed + (float)Math.random());
		this.reloadTime = reloadTime;
		this.cost = cost;
		associatedSquare = GameManager.getResources().addEntityToTerrain((int)position.getX(), (int)position.getY(), this);
	}


	private boolean hasLadder = false;
	private GameObject ladder;
	public void placeLadder() {
		hasLadder = true;
		ladder = new UI_Sprite(getPosition().add(new Vector2(0.3f, 0)), GameManager.getResources().getAnimationByPath("props/ladder.png")[0],  getLayer()+1);
	}
	
	public boolean hasLadder() {
		return hasLadder;
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

	public boolean isShroom() {
		return false;
	}
	private final float reloadTime;
	public float getReloadTime() {
		return reloadTime;
	}

	@Override
	public void onDestroy() {
		if(associatedSquare != null) {
			associatedSquare.removeEnt(this);
		}
		if(ladder != null)
			ladder.destroy();
	}
}