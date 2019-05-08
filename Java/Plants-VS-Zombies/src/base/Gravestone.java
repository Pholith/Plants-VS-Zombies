package base;

import enums.RenderMode;
import main.GameManager;

public class Gravestone extends GameObject {

	public Gravestone(Vector2 pos) {
		super(pos, RenderMode.Sprite);
		sprite = GameManager.getResources().getAnimationByPath("Gravestone.png");
	}
	

	private Sprite[] sprite;

	public	Sprite display() {
		return sprite[0];
	}

    public String name() {return "Gravestone";}

}
