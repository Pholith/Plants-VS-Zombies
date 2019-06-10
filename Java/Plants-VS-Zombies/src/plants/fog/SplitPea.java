package plants.fog;

import java.util.function.Function;

import base.GameObject;
import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import projectiles.Pea;
import zombies.Zombie;

public class SplitPea extends AttackingPlant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5065949634093744769L;

	public SplitPea(Vector2 position) {
		super(100, position, EnumReloadTime.fast, 100, "plants/SplitPea.png", 4f);

	}

	@Override
	public String name() {return "SplitPea";}

	@Override
	public void attack(Vector2 position, Zombie zombie) {
		new Pea(position.add(0.2f, 0.1f));
	}

	private float attackSpeedCount = 0;

	@Override
	public void update() {
		super.update();


		var self = this;
		// Fonction de sélection des zombies à gauche de la plante
		Function<GameObject, Boolean> lambda = new Function<GameObject, Boolean>() {
			@Override
			public Boolean apply(GameObject t) {
				if (t.isZombie() && t.isOnSameRow(self) && t.getPosition().getX() < self.getPosition().getX()) {
					return Boolean.valueOf(true);
				}
				return Boolean.valueOf(false);
			}
		};

		var zombies = GameManager.getInstance().getGameObjectArround(this, 10, lambda);
		Zombie targetLeft = null;
		if (zombies.size() > 0) {
			targetLeft = (Zombie) zombies.toArray()[0];
		}

		if (targetLeft != null) {
			attackSpeedCount += GameManager.getInstance().getDeltatime();

			if(attackSpeedCount >= 0.6f) {
				attack2(getPosition());
				attackSpeedCount = 0;
			}
		}
	}

	private void attack2(Vector2 position) {
		new Pea(position.add(-0.6f, 0.1f), false, true);

	}


}
