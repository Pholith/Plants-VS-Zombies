package plants;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class Chomper extends AttackingPlant {

	
	public Chomper(Vector2 position) {
		super(100, position, 3f, "plants/chomper.png", 3f);

		
	}

	private double digestTimeDelay = 20;
	private double digestTime = digestTimeDelay;

	public static int getCost() {
    	return 150;
	}

	@Override
	public void update() {

		if (digestTime >= digestTimeDelay) {
			setActive();
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 2f) {
				firstEnemy.takeDammage(1000);
				digestTime = 0;
				setInactive();
			}
		}
		digestTime += GameManager.getInstance().getDeltatime();
	}
	
    @Override
    public String name() {return "Chomper";}

}
