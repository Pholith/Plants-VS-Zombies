package base;


import java.awt.Graphics2D;
import java.io.IOException;
import java.util.*;

import main.GameManager;
import projectiles.Projectile;

/**
 * 
 */
public abstract class GameObject {

	  private Vector2 position;
	  private final RenderMode renderMode;
	  
    /**
     * Default constructor
     */
	
	
    
	//Lors de la creation d'un objet, celui-ci est directement ajouté à la scene
    //grace à la fonction "addGameObjectToScene" du GameManager    
    public GameObject(Vector2 pos, RenderMode renderMode) {
    	position = Objects.requireNonNull(pos);
    	this.renderMode = renderMode;
    	GameManager.getInstance().addGameObjectToScene(this);
    	System.out.println(name()+" created");
    }   
    
    
    // gameobject sans position n'étant pas dans le jeu (paradoxe ?)
    public GameObject(Vector2 pos) {
    	this(pos, RenderMode.Sprite);
    }
    /* Détruit un gameObject */
    public void destroy() {
    	onDestroy();
    	GameManager.getInstance().removeGameObjectFromScene(this);
    	System.out.println("Objet "+toString()+" détruit!");
    }
    public void onDestroy() {
    	
    }
    
    
    
    final public Vector2 getPosition() {
		return position;
	}
    
    public void translation(Vector2 v) {
    	position = v.add(position);
    }
    public void translation(float x, float y) {
    	translation(new Vector2(x, y));
    }

    /**
     * 
     */
    public void start() {
        // TODO implement here
    }

    /**
     * 
     */
    public void update() {
        // TODO implement here
    }

    /**
     * @throws IOException 
     * 
     */
    public Sprite display() {
    	return null;
    }
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
	
	}
	
    public String name() {return "GameObject";}

    public boolean isOnSameRow(GameObject o) {
    	return position.isOnSameRow(o.position);
    }
    public RenderMode getRenderMode() {
    	return renderMode;
    }
    
	public boolean isEnemy(GameObject o) {///Une catastrophe. faire une énumeration à la place ca prend 2 sec
    	if (isPlant() && o.isZombie()) return true;
    	if (isZombie() && o.isPlant()) return true;
    	if (isProjectile() && o.isZombie()) return true;
    	if (isZombie() && o.isProjectile()) return true;
    	// dans le cas avec les mêmes tag OU si l'un des objects est neutre
    	return false;
    }
	public boolean isPlant() {
		return false;
	}
	public boolean isZombie() {
		return false;
	}
	public boolean isProjectile() {
		return false;
	}
    public boolean isAlly(GameObject o) {
    	if (isPlant() && o.isPlant())return true;
    	if (isZombie() && o.isZombie()) return true;

    	// dans le cas plant et zombie OU si l'un des objects est neutre
    	return false;
    }

}