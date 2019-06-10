package projectiles;

import base.Vector2;

public class Pea extends LineProjectile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4053060156770010153L;
	public Pea(Vector2 position) {
		this(position, false);
	}

	public Pea(Vector2 position, boolean burned, boolean inverted) {
		super(position, inverted ? new Vector2(-0.06f, 0) : new Vector2(0.06f, 0), 10, "plants/peash.png", burned);
	}

	public Pea(Vector2 position, boolean burned) {
		this(position, false, false);
	}

	@Override
	public void update() {
		super.update();
	}
	@Override
	public String name() {
		return "Peash";
	}
	@Override
	public void onBurn() {
		super.onBurn();
		new FirePea(getPosition());
		destroy();
	}

}