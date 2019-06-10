package ui;

import java.awt.Color;
import base.Sprite;
import base.UI_Element;
import base.Vector2;
import enums.RenderMode;

public class UI_Sprite extends UI_Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4716337722130003353L;
	private final Sprite sprite;


	public UI_Sprite(Vector2 pos, Sprite sprite) {
		super(pos, 1f, Color.white, RenderMode.Sprite );
		this.sprite  = sprite;
	}

	@Override
	public String name() {return "UI_Sprite";}

	@Override
	public	Sprite display() {
		return sprite;
	}

}
