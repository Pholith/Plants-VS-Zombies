package base;

import java.awt.Color;

public class UI_TremblingLabel extends UI_TempLabel {

	private final float originX;
	private final float originY;
	private Vector2 trembling;


	public UI_TremblingLabel(Vector2 pos, String objectText, Color renderColor, int renderScale, double livingTime, Vector2 trembling) {
		super(pos, objectText, renderColor, renderScale, livingTime);
		
		originX = pos.getX();
		originY = pos.getY();
		
		this.trembling = trembling.multiply(1f / 100f); // pour diminuer grandement la vitesse
		System.out.println(this.trembling);
	}

	public UI_TremblingLabel(Vector2 pos, String objectText, double livingTime) {
		this(pos, objectText, Color.black, 3, livingTime, new Vector2(1f, 0));
	}
	
	public String name() { return "UI_TremblingLabel" ;};

	private float maxFar = 0.04f;
	public void update() {
		super.update();
		
		translation(trembling.getX(), trembling.getY());
		
		if (getPosition().getX() + maxFar < originX || getPosition().getX() - maxFar  > originX) {
			trembling = trembling.add(-2 * trembling.getX(), 0);
		}
		if (getPosition().getY() + maxFar < originY || getPosition().getY() - maxFar  > originY) {
			trembling = trembling.add(0, -2 * trembling.getY());
		}
		
	}

}
