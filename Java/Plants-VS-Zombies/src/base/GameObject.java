package base;


import java.awt.Graphics2D;
import java.io.IOException;
import java.util.*;

import main.GameManager;

/**
 * 
 */
public abstract class GameObject {

	  private Vector2 position;
	  private final float renderScale;
	  
	  
    /**
     * Default constructor
     */
    public GameObject(String tag) {
    	this(new Vector2(0, 0), tag);
    }

    
    public float getRenderScale() {
		return renderScale;
	}
    
    
	//Lors de la creation d'un objet, celui-ci est directement ajouté à la scene
    //grace à la fonction "addGameObjectToScene" du GameManager    
    public GameObject(Vector2 pos, float scale, String tag) {
    	position = pos;
    	this.tag = tag;
    	GameManager.getInstance().addGameObjectToScene(this);
    	renderScale = scale;
    }   
    
    public GameObject(Vector2 pos, String tag) {
    	this(pos, 1.0f, tag);
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
    
	private final String tag; // "zombie" / "plant" / "neutral"
	public String getTag() {return tag;}
	
	public boolean isEnemy(GameObject o) {
    	if (tag == "plant" && o.tag == "zombie") return true;
    	if (tag == "zombie" && o.tag == "plant") return true;

    	// dans le cas plant et zombie OU si l'un des objects est neutre
    	return false;
    }
	
    public boolean isAlly(GameObject o) {
    	if (tag == "plant" && o.tag == "plant") return true;
    	if (tag == "zombie" && o.tag == "zombie") return true;

    	// dans le cas plant et zombie OU si l'un des objects est neutre
    	return false;
    }

}