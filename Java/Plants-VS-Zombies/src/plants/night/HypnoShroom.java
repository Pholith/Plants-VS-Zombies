package plants.night;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import zombies.Zombie;

public class HypnoShroom extends Shroom {

	public HypnoShroom(Vector2 position) {
		super(100, position, EnumReloadTime.slow, "plants/HypnoShroom.png", 3f);
	}

    @Override
    public String name() {return "HypnoShroom";}

    public void update() {
    	if (isSleeping()) {
			return;
		}
		Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
		if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 1f) {
			firstEnemy.hypnotise();
			destroy();
		}

    }
}
