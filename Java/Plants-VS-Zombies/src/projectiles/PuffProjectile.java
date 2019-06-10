package projectiles;

import base.Vector2;

public class PuffProjectile extends LineProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7812064061458956225L;


	public PuffProjectile(Vector2 position) {
		this(position, -1);

	}
	public PuffProjectile(Vector2 position, float maxLenght) {
		super(position, new Vector2(0.06f, 0), 10, "plants/Bubble.png");
		this.originalPosition = position;
		this.maxlenght = maxLenght;
	}
	private final float maxlenght;
	private final Vector2 originalPosition;


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