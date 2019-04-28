package base;

import java.awt.Color;
import java.util.function.Consumer;

import main.GameManager;

public class UI_Sprite extends UI_Element {

	private Sprite sprite;
	
	
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
