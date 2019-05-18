package base;

import enums.RenderMode;
import main.GameManager;

public class Gravestone extends GameObject {

	private static final long serialVersionUID = -318750136912032570L;

	public Gravestone(Vector2 pos) {
		super(pos, RenderMode.Sprite);
		sprite = GameManager.getResources().getAnimationByPath("Gravestone.png");
	}
	

	private Sprite[] sprite;

	@Override
	public	Sprite display() {
		return sprite[0];
	}

    @Override
	public String name() {return "Gravestone";}

}
