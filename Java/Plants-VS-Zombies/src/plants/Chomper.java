package plants;

import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class Chomper extends AttackingPlant {

	
	public Chomper(Vector2 position) {
		super(150, position, 100, 3f, 50, 10f, "plants/chomper.png", 3f);

		
	}

	private double digestTimeDelay = 10;
	private double digestTime = digestTimeDelay;

	@Override
	public void update() {

		if (digestTime >= digestTimeDelay) {
			
			Zombie firstEnemy = (Zombie) GameManager.getInstance().getFirstZombie(this);
			if (firstEnemy != null && firstEnemy.getPosition().getX() < this.getPosition().getX()+ 2f) {
				firstEnemy.takeDammage(1000);
				digestTime = 0;
			}
		}
		digestTime += GameManager.getInstance().getDeltatime();
	}
	
    @Override
    public String name() {return "Chomper";}

}
