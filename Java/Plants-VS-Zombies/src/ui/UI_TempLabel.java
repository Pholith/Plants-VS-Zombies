package ui;

import java.awt.Color;
import base.Vector2;
import main.GameManager;

public class UI_TempLabel extends UI_Label {


	private static final long serialVersionUID = 1811565844735917900L;
	private final double livingTime; // temps (sec) avant disparition automatique du label
	private double living;
	private boolean useTimeScale = true;


	public UI_TempLabel(Vector2 pos, String objectText, Color renderColor, int renderScale, double livingTime, int layer, boolean useTimeScale) {
		super(pos, objectText, renderColor, renderScale, layer);
		this.livingTime = livingTime;
		living = 0;
		this.useTimeScale = useTimeScale;
	}

	public UI_TempLabel(Vector2 pos, String objectText, Color renderColor, int renderScale, double livingTime) {
		this(pos, objectText, renderColor, renderScale, livingTime, 95, true);
	}

	public UI_TempLabel(Vector2 pos, String objectText, double livingTime) {
		this(pos, objectText, Color.black, 3, livingTime);
	}

	@Override
	public void update() {
		super.update();

		if(useTimeScale) {
			living += GameManager.getInstance().getDeltatime();
		} else {
			living += GameManager.getInstance().getTrueDeltatime();
		}

		if (living >= livingTime) {
			destroy();
		}

	}	
	@Override
	public String name() { return "UI_TempLabel" ;}

}
