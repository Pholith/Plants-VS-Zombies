package plants.night;

import base.Vector2;
import main.GameManager;
import plants.Plant;
import props.Gravestone;

public class GraveBuster extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2160030191688500322L;


	public GraveBuster( Vector2 position) {
		super(100, position, 1f, 75 , "plants/GraveBuster.png", 1f);
		linkedGrave = (Gravestone)GameManager.getResources().searchClassInTerrain((int)position.getX(), (int)position.getY(), Gravestone.class);
		CalcTargetDir();
	}

	private final Gravestone linkedGrave;
	private float waitTime;	
	private float animCounter = 1f;	
	private Vector2 targetDir;


	private void CalcTargetDir() {
		if (linkedGrave == null) {
			return;
		}
		targetDir = new Vector2( getPosition().getX() - linkedGrave.getPosition().getX(), getPosition().getY()-  linkedGrave.getPosition().getY() ).add(Vector2.randomVector().multiply(0.01f));

	}


	@Override
	public void update() {
		waitTime+= GameManager.getInstance().getDeltatime();
		animCounter += GameManager.getInstance().getDeltatime();

		translationFixed(targetDir.multiply(Math.min(-0.01f / Vector2.distance(linkedGrave.getPosition(), getPosition()), 0.01f)   ) );

		if(animCounter > 0.1f) {
			animCounter = 0;
			CalcTargetDir();
		}

		if(waitTime >= 10f) {
			destroy();
			linkedGrave.destroy();
		}



	}





}
