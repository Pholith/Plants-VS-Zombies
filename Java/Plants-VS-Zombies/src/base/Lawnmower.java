package base;

import enums.RenderMode;
import main.GameManager;
import zombies.Zombie;

public class Lawnmower extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 885593557573489144L;
	private final Sprite sprite;
	private final float speed;

	public Lawnmower(Vector2 pos) {
		super(pos, RenderMode.Sprite, 1);

		sprite = GameManager.getResources().getAnimationByPath("Lawnmower.png")[0];
		speed = 0.01f;
	}

	@Override
	public	Sprite display() {
		return sprite;
	}


	@Override
	public String name() {return "Lawnmower";}

	private boolean startedMove;
	@Override
	public void update() {
		super.update();

		Zombie firstEnemy =  (Zombie) GameManager.getInstance().getFirstZombie(this);
		// si le projectile rencontre un zombie
		if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX() + 0.8f) {
			if (!startedMove) {
				startedMove = true;
			}
			firstEnemy.destroy();
		}
		if (startedMove) {
			translation(speed, 0);
		}
	}
}
