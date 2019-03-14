package base;




/**
 * 
 */
public class LivingEntity extends GameObject {

    /**
     * Default constructor
     */

	
	public LivingEntity(int health) {
		super();
		this.health = health;
	}

	

	/**
     * 
     */
    private int health;

  
    
    @Override
    public String display() {    	
  	   return "resources/textures/plants/plant_idl_0.png";
    }
}