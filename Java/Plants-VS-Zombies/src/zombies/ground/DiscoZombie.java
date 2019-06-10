package zombies.ground;

import base.Terrain;
import base.Vector2;
import main.GameManager;
import zombies.Zombie;

public class DiscoZombie extends Zombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422913465218913246L;

	public DiscoZombie(Vector2 position) {
		super(200, position, "zombies/DiscoZombie.png", 5f, 1f);
		
	}

	private boolean isDancing = false;
	private float danceDelay = 3f;
	private float dance = 0f;
	private int danced = 0;
	
	@Override
	public void update() {
		super.update();
		if ( (getPosition().getX() <= 10f && danced == 0) || (getPosition().getX() <= 8f && danced == 1) ) {
			isDancing = true;
			danced ++;
			var position = Terrain.positionToCase(getPosition());
			
			new JacksonZombie( new Vector2(position.getX() + 1,  position.getY()       ));
			
			if (position.getY() < GameManager.getResources().getTerrainSize().getY() - 1)
				new JacksonZombie( new Vector2(position.getX() , position.getY() + 1   ));
			
			new JacksonZombie( new Vector2(position.getX() - 1,  position.getY()       ));
			
			if (position.getY() >= 1)
				new JacksonZombie( new Vector2(position.getX() , position.getY() - 1   ));
			
		}
		if (isDancing) {
			if (dance >= danceDelay) {
				dance = 0;
				isDancing = false;
				return;
			}
			
			translation( (float) Math.random() / 200, 0);
			dance += GameManager.getInstance().getDeltatime();

		}
		
	}
	@Override
    public String name() {return "DiscoZombie";}


}
