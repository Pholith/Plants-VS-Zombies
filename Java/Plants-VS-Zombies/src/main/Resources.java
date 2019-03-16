package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import base.Constant;
import base.GameObject;
import base.LivingEntity;
import base.Sprite;
import base.Terrain;
import base.Vector2;
import plants.*;
import zombies.*;

//Les resources ont une visibilités de "package"
public class Resources {
	
	
    private final Map<String,Image> loadedImages;
    private final Map<String,Sprite[]> loadedAnimation;
    
    
    
    Resources() {
    	loadedImages = new HashMap<String, Image>(); 
    	loadedAnimation = new HashMap<String, Sprite[]>(); 
    }


    void startGame() throws IOException {
    	
    	 ///Prechargement des textures entieres
    	loadImageAtPath(Constant.errorTexture);	     	
    	loadImageAtPath("plants/plant_idl_0.png");
    	loadImageAtPath("lawn.jpg");
    	
    	
    	Sprite terrain = new Sprite(getImageByPath("lawn.jpg"));
    
    	Sprite[] pea_shooter = cutImage("plants/pea_shooter.png", 13, 3);
    	Sprite[] simple_zombie = cutImage("zombies/normal.png", 1, 1);
    	
    
    	new Terrain(terrain, 0.75f);
    	
    	for(int i = 0; i < 9*5; i++) {
    	new Peashooter(new Vector2(3 + (i%9) * 0.935f,  0.8f+(i/9)*1.1f));
    	}
    	new SimpleZombie(new Vector2(13f, 3f));
    	
        
    	//GameObject testAffiche2 = new LivingEntity(20, getAnimationByPath("plants/pea_shooter.png"), new Vector2(2,2), 5f);
        
        
    	
    }
    

    
    
    public Sprite[] cutImage(String path, int cntX, int cntY) throws IOException {
    	Sprite[] palette = new Sprite[cntX*cntY];
    	
    	loadImageAtPath(path);
    	
    	Image original = getImageByPath(path);
    	float currentWidth = (original.getWidth(null)/cntX);
    	float  currenHeight  = (original.getHeight(null)/cntY);

    	
    	///Decoupe et creation des sprites     	    	
    	for(int i = 0; i < palette.length; i++) 
    		palette[i] = new Sprite(original, new Vector2((i%cntX)*currentWidth,currenHeight*(i/cntX)), new Vector2(((i%cntX)+1)*currentWidth,currenHeight*((i/cntX)+1)));
    	
    	loadedAnimation.put(path, palette);
    	
    return palette;    	
    }
    
    
    
    
    
    
    public void loadImageAtPath(String spritePath ) throws IOException {
 	   File file = new File(Constant.texturePath + spritePath);
 	   Image img = ImageIO.read(file);    
 	  loadedImages.put(spritePath, img);
    }
    
    
 
 public Image getImageByPath(String spritePath ){
	 		if(loadedImages.containsKey(spritePath))
	     		return loadedImages.get(spritePath);
	 		System.out.println("error when loading sprite "+spritePath);
	     	return loadedImages.get(Constant.errorTexture);
 }
 
 
 public Sprite[] getAnimationByPath(String spritePath ){
	 		if(loadedAnimation.containsKey(spritePath))
	     		return loadedAnimation.get(spritePath);
	 		
	     	return null;
 }
 
 
}
