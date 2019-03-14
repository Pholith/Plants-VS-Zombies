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
	
    /**
     * Default constructor
     */
    public GameObject() {
    	this(new Vector2(0, 0));
    }

    
	//Lors de la creation d'un objet, celui-ci est directement ajouté à la scene
    //grace à la fonction "addGameObjectToScene" du GameManager    
    public GameObject(Vector2 pos) {
    	position = pos;
    	GameManager.getInstance().addGameObjectToScene(this);
    }
      
    
    final public Vector2 getPosition() {
		return position;
	}
    
    public void translation(Vector2 v) {
    	position = v.add(position);
    }
    public void translation(int x, int y) {
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