package ui;


import java.util.function.Consumer;

import base.Vector2;
import main.GameManager;

public class UI_LittleSun extends UI_Sun {

	public UI_LittleSun(Vector2 pos, Consumer simpleFunction) {
		super(pos, simpleFunction, GameManager.getResources().getAnimationByPath("particles/sun2.png")[0]);
	}


}
