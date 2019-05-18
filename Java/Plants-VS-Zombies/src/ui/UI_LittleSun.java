package ui;


import java.util.function.Consumer;

import base.Vector2;
import main.GameManager;

public class UI_LittleSun extends UI_Sun {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202827875642670775L;

	public UI_LittleSun(Vector2 pos, Consumer simpleFunction) {
		super(pos, simpleFunction, GameManager.getResources().getAnimationByPath("particles/sun2.png")[0]);
	}


}
