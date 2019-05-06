package projectiles;

import java.util.*;

import base.Vector2;
import main.GameManager;

/**
 * 
 */
public class PuffProjectile extends Projectile {

	public PuffProjectile(Vector2 position) {
		this(position, -1);
		
	}
	public PuffProjectile(Vector2 position, float maxLenght) {
		super(position, new Vector2(0.06f, 0), 10, "plants/PuffProjectile.png");
		this.originalPosition = position;
		this.maxlenght = maxLenght;
	}
	private float maxlenght;
	private Vector2 originalPosition;
	
	
	@Override
	public void update() {
		super.update();
		
		if (maxlenght != -1 && getPosition().getX() - originalPosition.getX() > maxlenght) {
			destroy();
		}
	}
    @Override
    public String name() {
    	return "PuffProjectile";
    }

}