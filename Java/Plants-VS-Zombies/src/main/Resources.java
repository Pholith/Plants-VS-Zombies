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
import base.Vector2;

//Les resources ont une visibilités de "package"
class Resources {
	
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
    	loadImageAtPath("plants/pea_shooter.png");
    	
    	
    	Sprite[] pea_shooter = cutImage("plants/pea_shooter.png", 13, 3);
    	GameObject testAffiche = new LivingEntity(20, pea_shooter, new Vector2(5,5), 5f);
    	
        
    	GameObject atestAffiche = new LivingEntity(20, getAnimationByPath("plants/pea_shooter.png"), new Vector2(2,2), 5f);
        
        
    	
    }
    

    
    
    public Sprite[] cutImage(String path, int cntX, int cntY) {
    	Sprite[] palette = new Sprite[cntX*cntY];
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
	     	return loadedImages.get(Constant.errorTexture);
 }
 
 
 public Sprite[] getAnimationByPath(String spritePath ){
	 		if(loadedAnimation.containsKey(spritePath))
	     		return loadedAnimation.get(spritePath);
	 		
	     	return null;
 }
 
 
}
