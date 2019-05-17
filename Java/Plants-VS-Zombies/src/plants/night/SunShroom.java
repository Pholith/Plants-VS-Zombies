package plants.night;


import base.Vector2;
import enums.EnumReloadTime;
import main.GameManager;
import ui.UI_LittleSun;

public class SunShroom extends Shroom {

    
    public SunShroom(Vector2 position) {
		super(100, position, EnumReloadTime.fast, "plants/SunShroom.png", 4f);
		productionDelay = 17;
		growDelay = 40;
	}    
	
    private int productionDelay;
	private int growDelay;
	
	private float grow = 0;
    private float production = 10;  // La sunflower produit son premier soleil plus rapidement

    @Override
    public String name() {return "SunShroom";}

    
    private boolean isBig() {
    	return (grow >= growDelay);
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