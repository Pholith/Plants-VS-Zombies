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
    public GameObject() {
    	this(new Vector2(0, 0));
    }

    
    public float getRenderScale() {
		return renderScale;
	}
    
    
	//Lors de la creation d'un objet, celui-ci est directement ajouté à la scene
    //grace à la fonction "addGameObjectToScene" du GameManager    
    public GameObject(Vector2 pos, float scale) {
    	position = pos;
    	GameManager.getInstance().addGameObjectToScene(this);
    	renderScale = scale;
    }   
    
    public GameObject(Vector2 pos) {
    	this(pos, 1.0f);
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

}