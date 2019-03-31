package main;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import base.Constant;
import base.GameObject;
import base.LivingEntity;
import base.Sprite;
import base.Square;
import base.Terrain;
import base.UI_Button;
import base.UI_Element;
import base.UI_Label;
import base.Vector2;
import plants.*;
import zombies.*;

//Les resources ont une visibilités de "package"
public class Resources {
	
	
    private final Map<String,Image> loadedImages;
    private final Map<String,Sprite[]> loadedAnimation;
    private Terrain actTerrain;
    private int selectedPlant;
    private ArrayList<UI_Button> terrainButtonList;
    private UI_Element selectedUi;
    
    public void setSelectedUi(UI_Element selectedUi) {
		this.selectedUi = selectedUi;
	}
    
    
    public boolean isSelectedUi(UI_Element elem) {
		return selectedUi == elem;
	}
    
    
    /*Terrain getActTerrain() {
		return actTerrain;
	}*/
    
   
    
 
    private  Sprite[] errorAnim;    
    
    public Sprite getErrorSprite() {
		return errorAnim[0];
	}
    
    Resources()  {
    	loadedImages = new HashMap<String, Image>(); 
    	loadedAnimation = new HashMap<String, Sprite[]>();
    	terrainButtonList = new ArrayList<UI_Button>();
    	selectedPlant = -1;
    }


    public Square addEntityToTerrain(int x, int y, LivingEntity ent) {
    	return actTerrain.addEntity(x, y, ent);    	
    }
    
 
    void startGame() throws IOException {
    	
    	 ///Prechargement des textures entieres
    	
    	errorAnim = cutImage(Constant.errorTexture, 1, 1, 64);
    	
    	loadImageAtPath("lawn.jpg");    
    	loadImageAtPath("plants/plant_idl_0.png");
    	
    	loadImageAtPath("cards/wallnuticon.png");
    	loadImageAtPath("cards/cherrybombicon.png");
    	loadImageAtPath("cards/potatomineicon.png");
    	loadImageAtPath("cards/peashootericon.png");
    	loadImageAtPath("cards/repeatericon.png");
    	loadImageAtPath("cards/snowpeaicon.png");
    	loadImageAtPath("cards/sunflowericon.png");
    	
    	
    	
    	// Chargement des sprites et animations
  
    	Sprite terrain = new Sprite(getImageByPath("lawn.jpg"),Vector2.zero(), 85);
    	actTerrain = new Terrain(terrain);
    	
    	cutImage("plants/pea_shooter.png", 13, 3, 64);
    	cutImage("plants/sunflower.png", 6, 9, 70);
    	cutImage("plants/wallNut.png", 1, 1, 150);

    	cutImage("zombies/zombie_flying.png", 6, 1, 150);    	 
    	cutImage("plants/peash.png", 1, 1, 100);

    	
    	
    
    	
    	
    	for(int i = 0; i < 5; i++) {
	    	new Sunflower(new Vector2(i%9, i/9));
    	}
    	for(int i = 5; i < 10; i++) {
	    	new Peashooter(new Vector2(i%9, i/9));
    	}
    	
    	//new SimpleZombie(new Vector2(13f, 1.6f));
    	//new SimpleZombie(new Vector2(11f, 3.25f));

    	//GameObject testAffiche2 = new LivingEntity(20, getAnimationByPath("plants/pea_shooter.png"), new Vector2(2,2), 5f);
        
    	
    	new UI_Button(new Vector2(1.5f, 1f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/peashootericon.png"), 75), func -> {selectPlantOfType(0);});
    	new UI_Button(new Vector2(1.5f, 2f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/sunflowericon.png"), 75),  func -> {selectPlantOfType(1);});
    	new UI_Button(new Vector2(1.5f, 3f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/wallnuticon.png"), 75),    func -> {selectPlantOfType(2);});
    }
    
    
    void updateResources() {
    	
    	/*
    	if(selectedUi == null && selectedPlant != -1) {
    		selectPlantOfType(-1);
    	}*/
    	
    	//Effet joli sur la couleur des bouttons du terrain
    	for(UI_Button but : terrainButtonList)
    		but.setRenderColor(new Color(255,165, 0, 200 + (int)(50d*Math.cos( (double)(GameManager.getInstance().getClockMillis()/250d))) ));
    	
    }
    
    
    private void selectPlantOfType(int value) {
    
    	System.out.println(value);
 
    	if (value == -1 || selectedPlant == value) {
    		selectedPlant = -1;
    		removeTerrainButtons();
    	}
    	else {
	    	selectedPlant = value;   
	    	drawTerrainButtons();
    	}
    }
    
      
    
    private void removeTerrainButtons() {
    	   for(int i = 0; i < terrainButtonList.size(); i++)
    		   terrainButtonList.get(i).destroy();
    	   terrainButtonList = new ArrayList<UI_Button>();
    }
    
    private void drawTerrainButtons() {
    	removeTerrainButtons();
    	 Consumer<Integer[]> buttonFunc =  (x) -> onSelectTerrainButton(x);
    	actTerrain.generateButtons(terrainButtonList, buttonFunc);    	  	
    }
    
    
    private void onSelectTerrainButton(Integer[] coords) {
    
    	switch (selectedPlant) {
		case 0:
			new Peashooter(new Vector2(coords[0], coords[1]));
			break;

		case 1:
			new Sunflower(new Vector2(coords[0], coords[1]));
			break;
		case 2:
			new WallNut(new Vector2(coords[0], coords[1]));
			break;
		}
    	
    	selectPlantOfType(-1);
    	
    }
    

    
    
    
    public Sprite[] cutImage(String path, int cntX, int cntY, int pixelPerUnit) throws IOException {
    	Sprite[] palette = new Sprite[cntX*cntY];
    	
    	loadImageAtPath(path);
    	
    	Image original = getImageByPath(path);
    	float currentWidth = (original.getWidth(null)/cntX);
    	float currenHeight = (original.getHeight(null)/cntY);

    	
    	///Decoupe et creation des sprites     	    	
    	for(int i = 0; i < palette.length; i++) 
    		palette[i] = new Sprite(original, new Vector2((i%cntX)*currentWidth,currenHeight*(i/cntX)), new Vector2(((i%cntX)+1)*currentWidth,currenHeight*((i/cntX)+1)), pixelPerUnit);
    	
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
	 		
	 		
	     	return errorAnim;
 }
 
 
}
