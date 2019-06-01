package zombies;


import base.LivingEntity;
import base.Terrain;
import base.Vector2;
import main.GameManager;

public abstract class Zombie extends LivingEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1527264097639594956L;


	public Zombie(int health, Vector2 position, String animationPath, float animSpeed, float speed, boolean inWater) {
		super(health, position, animationPath, animSpeed + (float)Math.random());
		this.speed = -speed/400 * GameManager.getResources().getGameConfig().getConfigFloat("zombieSpeedCoeff"); // pour avoir une petite vitesse sans avoir des 0.00002f
		this.inWater = inWater;
	}
	public Zombie(int health, Vector2 position, String animationPath, float animSpeed, float speed) {
		this(health, position, animationPath, animSpeed, speed, false);
	}
		
    private float speed;
    private int dammage = 20;
    private boolean inWater;
       
    @Override
    public String name() {return "Zombie";}
    
    public boolean isWaterZombie() {
    	return inWater;
    }
    
    @Override
    public void start() {
    }

    @Override
    public boolean isZombie() {
    	return !hypnotised;
    }
    @Override
    public boolean isTargetable() {
    	return true;
    }
    
    
    private float eatDelay = 2f;
    private float eatCouldown = eatDelay;

    public void addSpeed(float f) {
    	speed += -f/400;
    }
    private boolean slowed = false;
    private double timeSlow = 0;
    private double timeSlowDelay = 7;
    
    public void slow() {
    	slowed = true;
    }
    
    private double timeStun = 0; // état immobilisation
    public void stun() { 
    	timeStun = 3f;
    }
    private boolean hypnotised = false;
    public void hypnotise() { // deviens allié des plantes
    	hypnotised = true;
    	speed = 2 * (-speed);
    }
    public boolean isHypnotised() {
    	return hypnotised;
    }
	public void changeRow() {
		var position = Terrain.positionToCase(getPosition());
		Vector2 translationV;
		
		if (position.getY() > GameManager.getResources().getTerrainSize().getY() - 2) {
			translationV = new Vector2(0, -1);
			
		} else if (position.getY() < 1) {
			translationV = new Vector2(0, 1);
		
		} else {
			translationV = new Vector2(0, 1);
		}

		translationFixed(translationV);
		
	}
    
    // Renvoie vrai si les conditions pour manger sont remplies
    protected boolean conditionToEat(LivingEntity firstEnemy) {
    	return (!hypnotised && firstEnemy.getPosition().getX() > this.getPosition().getX() - 0.5 || (hypnotised && firstEnemy.getPosition().getX() < this.getPosition().getX() + 0.5));
    }
    
    
    // Retourne faux si le zombie ne mange pas
    protected LivingEntity findEnemy() {
    	LivingEntity enemy;
    	if (hypnotised) {
    		enemy = (LivingEntity) GameManager.getInstance().getFirstZombie(this);
		} else {
			enemy = (LivingEntity) GameManager.getInstance().getFirstPlant(this);
		}
    	return enemy;
    }

    // Inflige des dégats ou autre sur la plante
    protected void eatPlant(LivingEntity enemy) {
		enemy.takeDammage(dammage, this);
    }
    

    protected boolean eating() {
    	
    	// si le zombie rencontre une plante devant lui et assez proche, il la mange
    	
    	var enemy = findEnemy();
    	
    	if (enemy != null && conditionToEat(enemy)) {
        	
    		eatCouldown += GameManager.getInstance().getDeltatime();
    		if (eatCouldown >= eatDelay) {
    			eatPlant(enemy);
        		eatCouldown = 0;
			}
    		return true;
    	}
		return false;
    }
    @Override
    public void update() {
    	super.update();
    	
    	if (timeStun > 0) {
    		timeStun -= GameManager.getInstance().getDeltatime();
			setInactive();
    		return;
		}
    	setActive();
    	if (slowed) {
    		timeSlow += GameManager.getInstance().getDeltatime();
    		if (timeSlow >= timeSlowDelay) {
				slowed = false;
				timeSlow = 0;
			}
		}

    	if (eating()) {
    		
    	} else {
    		if (slowed) {
    			this.translation(new Vector2(speed / 3, 0f));
			}
    		else {
    			this.translation(new Vector2(speed, 0f));
    		}
    	}
    	if (this.getPosition().getX() < 2f) {
			GameManager.getInstance().endGame(false);
		}
    }
}