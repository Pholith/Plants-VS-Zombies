package base;

import enums.RenderMode;
import main.GameManager;
import zombies.Zombie;

public class Lawnmower extends GameObject {

	private Sprite[] sprite;
	private float speed;
	
	public Lawnmower(Vector2 pos) {
		super(pos, RenderMode.Sprite);
		
		sprite = GameManager.getResources().getAnimationByPath("Lawnmower.png");
		speed = 0.01f;
	}

	public	Sprite display() {
		return sprite[0];
	}

    public String name() {return "Lawnmower";}

    private boolean startedMove;
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
