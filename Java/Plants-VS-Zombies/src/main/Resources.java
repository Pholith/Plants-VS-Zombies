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
import base.UI_Sprite;
import base.UI_Sun;
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
    private float plantSpawnCounter;
    //si la pelle est selectionnée
    private boolean shovelMode;
    
    private int money;
    
    private UI_Label moneyRender;
    
    
    
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
    	money = 0;
    }


    public Square addEntityToTerrain(int x, int y, LivingEntity ent) {
    	return actTerrain.addEntity(x, y, ent);    	
    }

 
    void startGame() throws IOException {
    	
    	 ///Prechargement des textures entieres
    	
    	errorAnim = cutImage(Constant.errorTexture, 1, 1, 50);
    	
    	loadImageAtPath("lawn.jpg");    
    	loadImageAtPath("plants/plant_idl_0.png");
    	
    	loadImageAtPath("cards/wallnuticon.png");
    	loadImageAtPath("cards/cherrybombicon.png");
    	loadImageAtPath("cards/potatomineicon.png");
    	loadImageAtPath("cards/peashootericon.png");
    	loadImageAtPath("cards/repeatericon.png");
    	loadImageAtPath("cards/snowpeaicon.png");
    	loadImageAtPath("cards/sunflowericon.png");
    	loadImageAtPath("cards/cherryBombIcon.png");
    	loadImageAtPath("cards/shovelicon.png");
    	loadImageAtPath("cards/emptyfield.png");

    	

    	
    	Sprite emptyField = new Sprite(getImageByPath("cards/emptyfield.png"), 75);

    	
    	// Chargement des sprites et animations
  
    	Sprite terrain = new Sprite(getImageByPath("lawn.jpg"),Vector2.zero(), 85);
    	actTerrain = new Terrain(terrain);
    	
    	cutImage("plants/pea_shooter.png", 13, 3, 64);
    	cutImage("plants/sunflower.png", 6, 9, 70);
    	cutImage("plants/wallNut.png", 1, 1, new Vector2(0.5f, 0.6f), 150);
    	cutImage("plants/cherryBomb.png", 1, 1, new Vector2(0.5f, 0.5f), 260);
    	   	
    	cutImage("zombies/zombie_flying.png", 6, 1, 200);    	 
      	cutImage("zombies/zombie_conehead.png", 6, 1, 200);    
    	cutImage("zombies/flag_zombie.png", 1, 1,135);
    	cutImage("plants/peash.png", 1, 1, new Vector2(0.5f,2.75f),100);

    	cutImage("particles/explosion.png", 4, 4, new Vector2(0.5f,0.5f), 30);   
    	cutImage("particles/sun.png", 1, 1, new Vector2(0.5f,0.5f), 80);   
     
    	
       	   	
    	/*for(int i = 0; i < 5; i++) {
	    	new Sunflower(new Vector2(i%9, i/9));
    	}
    	for(int i = 5; i < 10; i++) {
	    	new Peashooter(new Vector2(i%9, i/9));
    	}*/
    	
    	//new SimpleZombie(new Vector2(13f, 1.6f));
    	//new SimpleZombie(new Vector2(11f, 3.25f));

    	//GameObject testAffiche2 = new LivingEntity(20, getAnimationByPath("plants/pea_shooter.png"), new Vector2(2,2), 5f);
        
    	
    	new UI_Button(new Vector2(1.5f, 1f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/peashootericon.png"), 75), func -> {selectPlantOfType(0);});
    	new UI_Button(new Vector2(1.5f, 2f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/sunflowericon.png"), 75),  func -> {selectPlantOfType(1);});
    	new UI_Button(new Vector2(1.5f, 3f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/wallnuticon.png"), 75),    func -> {selectPlantOfType(2);});
    	new UI_Button(new Vector2(1.5f, 4f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/cherryBombIcon.png"), 75), func -> {selectPlantOfType(3);});
       	new UI_Button(new Vector2(1.5f, 6.25f), 1f, Color.BLACK, new Sprite(getImageByPath("cards/shovelicon.png"), 75), func -> {selectShovel();});

       	new UI_Sprite(new Vector2(1.5f, 0.3f), emptyField);
     	new UI_Sprite(new Vector2(2f, 0.3f), new Sprite(getImageByPath("particles/sun.png"), 100));
       	moneyRender = new UI_Label(new Vector2(1f, 0.4f), "0", Color.black, 3f);


   
     	
    
       	
     
       	
       	
    }
    
    
    
    public void spawnSun(Vector2 pos) {    	
    	new UI_Sun(pos, func -> {getASun();});     	
     	 	
        }
    
    private void getASun() {    	
    money += 50;
    
    }
    
    
    
    void updateResources() {
    	
  
    	//Effet joli sur la couleur des bouttons du terrain
    	if(!shovelMode) {
    	for(UI_Button but : terrainButtonList)
    		but.setRenderColor(new Color(255,165, 0, 200 + (int)(50d*Math.cos( (double)(GameManager.getInstance().getClockMillis()/250d))) ));
    	}else {
    		for(UI_Button but : terrainButtonList)
        		but.setRenderColor(new Color(0,165, 255, 200 + (int)(50d*Math.cos( (double)(GameManager.getInstance().getClockMillis()/250d))) ));
    	}
    		
    	
    	//Gestion du mode debug
		if (GameManager.getInstance().isDebugMode()) {
		
			
			if (plantSpawnCounter >= 2f) {
				Vector2 terrainSize = actTerrain.getTerrainSize();
				int randomX = (int) (Math.random()*terrainSize.getX());
				int randomY = (int) (Math.random()*terrainSize.getY());

				shovelMode = false;
				System.out.println(" random: "+randomX+" "+randomY);

				selectedPlant =  (int)(Math.random()*10);
				onSelectTerrainButton(new Integer[] {randomX, randomY});
				plantSpawnCounter = 0;
			}
			plantSpawnCounter += GameManager.getInstance().getDeltatime();
		}
		
		
		
		
		//money actualisation
		moneyRender.setText(Integer.toString( money));
		
		
    	
    }
    
    
    
    
    private void selectShovel() {        
    	shovelMode = true; 
	    drawTerrainButtons();
    }
    
    
    private void selectPlantOfType(int value) {
    shovelMode = false;
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
    	actTerrain.generateButtons(terrainButtonList, buttonFunc, shovelMode);    	 
	    if(terrainButtonList.size() == 0)
    		selectedPlant = -1;
    }
    
    
   private void onSelectTerrainButton(Integer[] coords) {
    	
    	if(coords == null || coords.length != 2) 
    		return;
    	
    	
    	if(shovelMode) {
    		  actTerrain.removeEntity(coords[0], coords[1]);
    		  	selectPlantOfType(-1);
    		return;
    	}
    	
    	switch (selectedPlant%4) {
		case 0:
			new Peashooter(new Vector2(coords[0], coords[1]));
			break;

		case 1:
			new Sunflower(new Vector2(coords[0], coords[1]));
			break;
		case 2:
			new WallNut(new Vector2(coords[0], coords[1]));
			break;
	   	case 3:
    		new CherryBomb(new Vector2(coords[0], coords[1]));
    		break;
    	}
    	
    	selectPlantOfType(-1);
    	
    }
    

    

    public Sprite[] cutImage(String path, int cntX, int cntY, int pixelPerUnit) throws IOException {
    	return cutImage( path,  cntX,  cntY, new Vector2(0.5f,0.8f) ,  pixelPerUnit);
    }
    
    public Sprite[] cutImage(String path, int cntX, int cntY, Vector2 anchor, int pixelPerUnit) throws IOException {
    	Sprite[] palette = new Sprite[cntX*cntY];
    	
    	loadImageAtPath(path);
    	
    	Image original = getImageByPath(path);
    	float currentWidth = (original.getWidth(null)/cntX);
    	float currenHeight = (original.getHeight(null)/cntY);

    	
    	///Decoupe et creation des sprites     	    	
    	for(int i = 0; i < palette.length; i++) 
    		palette[i] = new Sprite(original, new Vector2((i%cntX)*currentWidth,currenHeight*(i/cntX)), new Vector2(((i%cntX)+1)*currentWidth,currenHeight*((i/cntX)+1)),anchor, pixelPerUnit);
    	
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
