package base;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

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
    public void display(Vector2 camPos, Graphics2D graphics ) {
    	
    	//Boucherie gros TEST pour charger les img (tkt elle seront pas chargés ici les img) de plus le 
    	// display est mal foutu.
    	
    	
    	   File pathToFile = new File("resources/textures/plants/plant_idl_0.png");
    	   Image image = null;
		try {			
			image = ImageIO.read(pathToFile);
			
			graphics.drawImage(image, 30, 30, 150, 150, 0, 0, 16, 16, null);
			
		System.out.println("aaaaa");
		} catch (IOException e) {
			System.out.println("bbbbb");	
		}
    	
    	
    }
}