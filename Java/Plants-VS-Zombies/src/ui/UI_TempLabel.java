package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import base.Vector2;
import main.GameManager;

public class UI_TempLabel extends UI_Label {

	private final double livingTime; // temps (sec) avant disparition automatique du label
	private double living;
	
	public UI_TempLabel(Vector2 pos, String objectText, Color renderColor, int renderScale, double livingTime) {
		super(pos, objectText, renderColor, renderScale);
		this.livingTime = livingTime;
		living = 0;
	}
	
	public UI_TempLabel(Vector2 pos, String objectText, double livingTime) {
		this(pos, objectText, Color.black, 3, livingTime);
	}
	
	@Override
	public void update() {
		super.update();
		
		living += GameManager.getInstance().getDeltatime();
		if (living >= livingTime) {
			destroy();
		}
	}
	public String name() { return "UI_TempLabel" ;}
	
}
