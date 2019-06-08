package props;

import base.LivingEntity;
import base.Square;
import base.Vector2;

public class Gravestone extends LivingEntity{

	private static final long serialVersionUID = -318750136912032570L;

	private final Square associatedSquare;
	
	public Gravestone(Vector2 pos, Square sqr) {
		super(100, pos, "props/gravestone.png", 1f);
		this.associatedSquare = sqr;
	}


	
    @Override
    public void onDestroy() {
    if(associatedSquare != null)
    	associatedSquare.removeEnt(this);
    }


    @Override
	public String name() {return "Gravestone";}

}
