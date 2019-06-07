package props;

import base.Vector2;
import main.GameManager;
import ui.UI_AnimatedSprite;
import ui.UI_Sprite;


public class Fog extends UI_AnimatedSprite{

	
	
	
	public Fog(Vector2 pos) {
		super(pos, "particles/fog.png", 5f+ (float)Math.random()*2f, false, 30);
	}

	


	
	


	
	
	
}
