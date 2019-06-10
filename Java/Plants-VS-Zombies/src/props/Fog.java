package props;

import base.Vector2;
import ui.UI_AnimatedSprite;


public class Fog extends UI_AnimatedSprite{




	/**
	 * 
	 */
	private static final long serialVersionUID = 8925277313481326667L;

	public Fog(Vector2 pos) {
		super(pos, "particles/fog.png", 5f+ (float)Math.random()*2f, false, 30);
	}











}
