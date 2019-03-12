package main;


import java.util.*;

/**
 * 
 */
public abstract class GameObject {

    /**
     * Default constructor
     */
    public GameObject() {
    }

    /**
     * 
     */
    private Vector2D position;
    
    public void translation(Vector2D v) {
    	position = v.add(position);
    }
    public void translation(int x, int y) {
    	translation(new Vector2D(x, y));
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
     * 
     */
    public void display() {
        // TODO implement here
    }

}