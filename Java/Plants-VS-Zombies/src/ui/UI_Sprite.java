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


	public UI_Sprite(Vector2 pos, Sprite sprite, int layer) {
		super(pos, 1f, Color.white, RenderMode.Sprite , layer);
		this.sprite  = sprite;
	}
	public UI_Sprite(Vector2 pos, Sprite sprite) {
		this(pos, sprite, 90 );
	}
	
	

	@Override
	public String name() {return "UI_Sprite";}

	@Override
	public	Sprite display() {
		return sprite;
	}

}
