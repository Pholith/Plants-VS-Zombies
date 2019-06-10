package plants.day;

import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import plants.AttackingPlant;
import zombies.Zombie;

public class Chomper extends AttackingPlant {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1203901686241945647L;

	public Chomper(Vector2 position) {
		super(100, position, EnumReloadTime.medium, 150, "plants/chomper.png", 2.5f);
	}

	private boolean isDigesting = false;
	private final double digestTimeDelay = 20;
	private double digestTime = digestTimeDelay;

	@Override
	public void update() {

		if (digestTime >= digestTimeDelay && isDigesting) {
			setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/chomper.png"));
			isDigesting = false;
		}
		if (!isDigesting) {

			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 2f) {
				firstEnemy.takeDammage(1000);
				digestTime = 0;
				isDigesting = true;
				setAnimationSprite(GameManager.getResources().getAnimationByPath("plants/eating_chomper.png"));
			}
		}
		digestTime += GameManager.getInstance().getDeltatime();
	}

	@Override
	public String name() {return "Chomper";}

}
