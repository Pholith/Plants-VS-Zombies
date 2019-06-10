package plants.day;


import base.Vector2;
import enums.EnumReloadTime;
import plants.Plant;

public class WallNut extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -461871528190460058L;

	public WallNut(Vector2 position) {
		super(500, position, EnumReloadTime.slow, 50, "plants/wallNut.png", 3f);
	}

	@Override
	public String name() {return "WallNut";}


}