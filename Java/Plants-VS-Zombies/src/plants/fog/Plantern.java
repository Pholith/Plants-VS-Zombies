package plants.fog;

import base.Vector2;
import plants.Plant;

public class Plantern extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6252287764580523611L;


	public Plantern(Vector2 position) {
		super(100, position, 1f, 25, "plants/plantern.png", 100f);
		// TODO Auto-generated constructor stub
	}


	@Override
	public float getLightRange() {
		return 2.5f;
	}


}
