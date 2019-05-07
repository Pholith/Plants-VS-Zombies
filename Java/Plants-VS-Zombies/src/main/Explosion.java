package main;

import base.GameObject;
import base.LivingEntity;
import base.Sprite;
import base.Terrain;
import base.Vector2;
import enums.RenderMode;

public class Explosion extends LivingEntity {

	public Explosion(Vector2 pos) {
		super(999, Terrain.positionToCase(pos), "particles/explosion.png", 4f);
	}

	@Override
	public void onLastFrame() {
		super.onLastFrame();
		destroy();
	}
	@Override
	public String name() {return "Explosion";}
}
