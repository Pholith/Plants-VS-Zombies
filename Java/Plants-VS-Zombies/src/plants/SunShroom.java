package plants;


import base.Vector2;
import main.GameManager;
import ui.UI_LittleSun;

public class SunShroom extends Shroom {

    
    public SunShroom(Vector2 position) {
		super(100, position, 15f, "plants/SunShroom.png", 4f);
		productionDelay = 20;
		growDelay = 40;
	}    
	
    private int productionDelay;
	private int growDelay;
	
	private float grow = 0;
    private float production = 0;

    @Override
    public String name() {return "SunShroom";}

    
    private boolean isBig() {
    	return (grow >= growDelay);
    }
	public static int getCost() {
    	return 25;
	}
	

    
    @Override
    public void update() {
    	super.update();
    	if (!isSleeping()) {
	
	    	if(production >= productionDelay) {
	        	new UI_LittleSun(getPosition(), func -> { GameManager.getInstance().getResources().getASun( !isBig() );} );     	
	        	production = 0;
	    	}
	    	if (!isBig()) {
				grow += GameManager.getInstance().getDeltatime();
			}
	    	production += GameManager.getInstance().getDeltatime();
    	}
    	
    }
}