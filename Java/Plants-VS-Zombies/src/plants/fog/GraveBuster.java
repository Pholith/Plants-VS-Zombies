package plants.fog;

import base.Vector2;
import main.GameManager;
import plants.AttackingPlant;
import plants.Plant;
import props.Gravestone;

public class GraveBuster extends Plant {

	public GraveBuster( Vector2 position) {
		super(100, position, 1f, 75 , "plants/GraveBuster.png", 1f);
		baseY = position.getY();	
		linkedGrave = (Gravestone)GameManager.getResources().searchClassInTerrain((int)position.getX(), (int)position.getY(), Gravestone.class);
		if(linkedGrave == null)
			System.out.println("EEEEEEEEEERRRRRRRRRRRRRRRRRRRR");
	}
	
	private Gravestone linkedGrave;
	private float waitTime;	
	private float baseY;
	
	
	
	@Override
	public void update() {
		waitTime+= GameManager.getInstance().getDeltatime();
				
		translationFixed(new Vector2(0,(float) Math.cos(waitTime*5)*0.02f ) );
		
		
		if(waitTime >= 10f) {
			destroy();
			linkedGrave.destroy();
		}
		
		
		
	}
	
	
	

	
}
