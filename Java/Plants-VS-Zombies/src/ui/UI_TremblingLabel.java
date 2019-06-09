package ui;

import java.awt.Color;

import base.Vector2;

public class UI_TremblingLabel extends UI_TempLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 151675358489816717L;
	private final float originX;
	private final float originY;
	private Vector2 trembling;
	private final boolean fixTranslation;

	public UI_TremblingLabel(Vector2 pos, String objectText, Color renderColor, int renderScale, double livingTime, Vector2 trembling, boolean fixedTranslation, int layer) {
		super(pos, objectText, renderColor, renderScale, livingTime, layer, !fixedTranslation);
		
		originX = pos.getX();
		originY = pos.getY();
		fixTranslation = fixedTranslation;
		this.trembling = trembling.multiply(1f / 100f); // pour diminuer grandement la vitesse
		System.out.println(this.trembling);
	}

	public UI_TremblingLabel(Vector2 pos, String objectText, double livingTime) {
		this(pos, objectText, Color.black, 3, livingTime, new Vector2(1f, 0), false, 95);
	}
	
	@Override
	public String name() { return "UI_TremblingLabel" ;};

	private float maxFar = 0.04f;
	@Override
	public void update() {
		super.update();
		
		if(fixTranslation)
			translationFixed(trembling.getX(), trembling.getY());	
		else
			translation(trembling.getX(), trembling.getY());
		
		if (getPosition().getX() + maxFar < originX || getPosition().getX() - maxFar  > originX) {
			trembling = trembling.add(-2 * trembling.getX(), 0);
		}
		if (getPosition().getY() + maxFar < originY || getPosition().getY() - maxFar  > originY) {
			trembling = trembling.add(0, -2 * trembling.getY());
		}
		
	}

}
