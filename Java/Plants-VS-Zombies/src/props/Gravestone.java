package props;

import base.LivingEntity;
import base.Vector2;

public class Gravestone extends LivingEntity{

	private static final long serialVersionUID = -318750136912032570L;

	public Gravestone(Vector2 pos) {
		super(100, pos, "props/gravestone.png", 1f);
	}
	
	


    @Override
	public String name() {return "Gravestone";}

}
