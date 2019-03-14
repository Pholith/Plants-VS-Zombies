package base;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.media.sound.Toolkit;

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