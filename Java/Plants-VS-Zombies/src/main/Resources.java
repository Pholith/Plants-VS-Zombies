﻿package main;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import base.Constant;
import base.Lawnmower;
import base.LivingEntity;
import base.Sprite;
import base.Square;
import base.Terrain;
import base.UI_Element;
import base.Vector2;
import enums.EnumTerrain;
import plants.*;
import ui.UI_Button;
import ui.UI_Label;
import ui.UI_PlantButton;
import ui.UI_Sprite;
import ui.UI_Sun;
import ui.UI_TremblingLabel;
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
 
    private GameInfo gameInfo;
    
    private Class[] zombiesTotalList;
    private Class[] PlantsTotalList;
    
    public Class[] getPlantsTotalList() {
		return PlantsTotalList;
	}
    public Class[] getZombiesTotalList() {
		return zombiesTotalList;
	}
    
    
    
    //si la pelle est selectionnée

    private boolean shovelMode;
    
    private int money;
    
    private UI_Label moneyRender;
    
    public GameInfo getGameInfo() {
		return gameInfo;
	}
    
    public void setSelectedUi(UI_Element selectedUi) {
		this.selectedUi = selectedUi;
	}
    
    public boolean isSelectedUi(UI_Element elem) {
		return selectedUi == elem;
	}
    

    
    private  UI_PlantButton[] plantButtonList;  
    
 
    private  Sprite[] errorAnim;   
    
    
    public Sprite getErrorSprite() {
		return errorAnim[0];
	}
    
    Resources()  {
    	loadedImages = new HashMap<String, Image>(); 
    	loadedAnimation = new HashMap<String, Sprite[]>();
    	terrainButtonList = new ArrayList<UI_Button>();
    	selectedPlant = -1;
    	money = 1000;
    }


    public Square addEntityToTerrain(int x, int y, LivingEntity ent) {
    	return actTerrain.addEntity(x, y, ent);    	
    }

 
    void loadResources() throws IOException {
    	
    	 ///Prechargement des textures entieres
    	
    	errorAnim = cutImage(Constant.errorTexture, 1, 1, 50);
    	

    	
    	zombiesTotalList = new Class[] {
    			SimpleZombie.class, ConeheadZombie.class, PoleVaulterZombie.class, BucketHeadZombie.class,
    			FootballZombie.class, ScreenDoorZombie.class, /* FlagZombie.class pas lui */
    	};
    	
    	PlantsTotalList = new Class[] {
    			Peashooter.class, Sunflower.class, WallNut.class, CherryBomb.class,
    			Chomper.class, FreezePeaShooter.class, PotatoMine.class
    	};
    	//nomer les textures en fonction du nom des classes. (si on veut, on pourra géneraliser l'appel des textures avec le nom des classes)
    	    	
    	loadImageAtPath("cards/CherryBomb_icon.png");
    	loadImageAtPath("cards/Chomper_icon.png");
    	loadImageAtPath("cards/FreezePeaShooter_icon.png");
    	loadImageAtPath("cards/Peashooter_icon.png");
    	loadImageAtPath("cards/PotatoMine_icon.png");
    	loadImageAtPath("cards/Repeater_icon.png");
    	loadImageAtPath("cards/Sunflower_icon.png");
    	loadImageAtPath("cards/WallNut_icon.png");
    	    
    	loadImageAtPath("cards/shovel_icon.png");
    	loadImageAtPath("cards/emptyfield.png");
    	
    	
    	
    	// Chargement des sprites et animations  
    	loadImageAtPath("lawn.jpg");
    	loadImageAtPath("nightTerrain.jpg");
    	loadImageAtPath("poolTerrain.jpg");
    	loadImageAtPath("roofTerrain.jpg");
    	loadImageAtPath("roofTerrainNight.jpg");
    
    	
    	// nombre de colonne puis nombre de ligne
    	cutImage("plants/pea_shooter.png", 13, 3, 64);
    	cutImage("plants/sunflower.png", 6, 9, 70);
    	cutImage("plants/wallNut.png", 9, 3, 60);
    	cutImage("plants/cherryBomb.png", 1, 1, 260);
    	cutImage("plants/chomper.png", 31, 1,new Vector2(0.4f,0.8f), 80);
    	cutImage("plants/freeze_pea_shooter.png", 7, 3, 64);
    	cutImage("plants/patatomine.png", 9, 6, 75);
    	
    	cutImage("plants/LilyPad.png", 1, 1,new Vector2(0.5f,0.4f), 175);
    	
    	cutImage("Lawnmower.png", 1, 1, 60);

    	
    	cutImage("zombies/SimpleZombie.png", 6, 1, 200);    	 
      	cutImage("zombies/ConeheadZombie.png", 6, 1, 200);    
    	cutImage("zombies/FlagZombie.png", 1, 1,135);
    	cutImage("zombies/PoleVaulterZombie.png", 1, 1, 290);
    	cutImage("zombies/BucketHeadZombie.png", 1, 1, 450);
    	cutImage("zombies/FootballZombie.png", 2, 1, 80);
    	cutImage("zombies/ScreenDoorZombie.png", 1, 1, 320);
    	
    	cutImage("plants/peash.png", 1, 1, new Vector2(0.5f,2.75f), 100);
    	cutImage("plants/snowpeash.png", 1, 1, new Vector2(0.5f,2.75f), 100);
    	
    	cutImage("particles/explosion.png", 4, 4, new Vector2(0.5f,0.5f), 30);   
    	cutImage("particles/sun.png", 1, 1, new Vector2(0.5f,0.5f), 80);   
    	

    	
    	

    	MainMenu.start_menu();
    }
    
    
    public void startGame(GameInfo game) {
    	
       	gameInfo = game;
       	
    	
    	Sprite terrainSp = getErrorSprite();
    
    	
    switch(game.getSelectedTerrain()) {    
	    case lawn:
	    	terrainSp = new Sprite(getImageByPath("lawn.jpg"),Vector2.zero(), 85);
	    break;
	    case night_lawn:
	    	terrainSp = new Sprite(getImageByPath("nightTerrain.jpg"),Vector2.zero(), 85);
	    break;
	    case pool:
	    	terrainSp = new Sprite(getImageByPath("poolTerrain.jpg"),Vector2.zero(), 85);
	    break;
    }
    
	actTerrain = new Terrain(terrainSp, game.getSelectedTerrain());
    
 	Sprite emptyField = new Sprite(getImageByPath("cards/emptyfield.png"), 75);
 	new UI_Sprite(new Vector2(1.5f, 0.3f), emptyField);

 	// Les tondeuses
	for (int i = 0; i < actTerrain.getTerrainSize().getY(); i++) {
		new Lawnmower(Terrain.caseToPosition(-1, i));		
	}

	// Les boutons des plantes
	Class[] plants = gameInfo.getListOfPlants();
		
	plantButtonList = new UI_PlantButton[plants.length];
		
	for(int i = 0; i < plants.length; i++) {
		int b = i;//obligé.. oups. ?
		plantButtonList[i] = new UI_PlantButton(new Vector2(1.5f, 1f + 0.9f*i), new Sprite(getImageByPath("cards/" + plants[i].getSimpleName() +"_icon.png"), 75), func -> {selectPlantOfType(b);}, 1f);			
	}
		
		
	/*
	
	{
	new UI_PlantButton(new Vector2(1.5f, 1f), new Sprite(getImageByPath("cards/peashootericon.png"), 75), func -> {selectPlantOfType(0);}, 1f),
	new UI_PlantButton(new Vector2(1.5f, 1.9f), new Sprite(getImageByPath("cards/sunflowericon.png"), 75),  func -> {selectPlantOfType(1);}, 1f),
	new UI_PlantButton(new Vector2(1.5f, 2.8f), new Sprite(getImageByPath("cards/wallnuticon.png"), 75),    func -> {selectPlantOfType(2);}, 1f),
	new UI_PlantButton(new Vector2(1.5f, 3.7f), new Sprite(getImageByPath("cards/cherrybombicon.png"), 75), func -> {selectPlantOfType(3);}, 1f),
	new UI_PlantButton(new Vector2(1.5f, 4.6f), new Sprite(getImageByPath("cards/chompericon.png"), 75), func -> {selectPlantOfType(4);}, 1f),
	new UI_PlantButton(new Vector2(1.5f, 5.5f), new Sprite(getImageByPath("cards/snowpeaicon.png"), 75), func -> {selectPlantOfType(5);}, 1f),

	};
	*/
	
	
   	new UI_PlantButton(new Vector2(1.5f, 6.4f), new Sprite(getImageByPath("cards/shovel_icon.png"), 75), func -> {selectShovel();}, 1f);
	
	
	
 	new UI_Sprite(new Vector2(2f, 0.3f), new Sprite(getImageByPath("particles/sun.png"), 100));
   	moneyRender = new UI_Label(new Vector2(1f, 0.4f), "0", Color.black, 3f);


   	GameManager.getInstance().setGameStarted(true);
    }

	
    
    
    
    
    public void spawnSun(Vector2 pos) {    	
    	new UI_Sun(pos, func -> {getASun();});     	
     	 	
        }
    
    public void getASun() {    	
    	money += 50;
    }
    
    
    
    
    
    void updateResources() {
    	
  
    	
		if(GameManager.getInstance().getGameStarted()) {
			
    	//Effet joli sur la couleur des bouttons du terrain
    	if(!shovelMode) {
	    	for (UI_Button but : terrainButtonList)
	    		but.setRenderColor(new Color(255, 165, 0, 200 + (int)(50d*Math.cos( (double)(GameManager.getInstance().getClockMillis()/250d))) ));
	    	} else {
	    		for (UI_Button but : terrainButtonList)
	        		but.setRenderColor(new Color(0, 165, 255, 200 + (int)(50d*Math.cos( (double)(GameManager.getInstance().getClockMillis()/250d))) ));
    	}
    		
    	
    	//Gestion du mode debug
		if (GameManager.getInstance().isDebugMode()) {
		
						
			
			if (plantSpawnCounter >= 2f) {
				Vector2 terrainSize = actTerrain.getTerrainSize();
				int randomX = (int) (Math.random()*terrainSize.getX());
				int randomY = (int) (Math.random()*terrainSize.getY());

				shovelMode = false;
				System.out.println(" random: "+randomX+" "+randomY);

				selectedPlant =  (int)(Math.random() * gameInfo.getListOfPlants().length);
				onSelectTerrainButton(new Integer[] {randomX, randomY});
				plantSpawnCounter = 0;
			}
			plantSpawnCounter += GameManager.getInstance().getDeltatime();
		}
		
		

		//money actualisation
		moneyRender.setText(Integer.toString(money));
		
		}
		    	
    }
    
    
    
    
    private void selectShovel() {  
    	shovelMode = true; 
	    drawTerrainButtons(-1);
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
	    	drawTerrainButtons(selectedPlant);

    	}
    }
    
      
    
    private void removeTerrainButtons() {
    	   for(int i = 0; i < terrainButtonList.size(); i++)
    		   terrainButtonList.get(i).destroy();
    	   terrainButtonList = new ArrayList<UI_Button>();
    }
    
    private void drawTerrainButtons(int selectedPlant) {
    	removeTerrainButtons();
    	Consumer<Integer[]> buttonFunc =  (x) -> onSelectTerrainButton(x);
    	
    	// Renvoie les données de la plante pour générer les bonnes cases
    	actTerrain.generateButtons(terrainButtonList, buttonFunc, shovelMode);
    	
    	
	    if(terrainButtonList.size() == 0)
    		selectedPlant = -1;
    }
    
    
   private void onSelectTerrainButton(Integer[] coords) {
    	
    	if (coords == null || coords.length != 2) 
    		return;
    	
    	
    
    	if (shovelMode) {
    		  actTerrain.removeEntity(coords[0], coords[1]);
    		  	selectPlantOfType(-1);
    		return;
    	}
    	var vector = new Vector2(coords[0], coords[1]);
    	
    	// Liste des class de plantes
    	/*Class[] listOfPlant = new Class[] {
    			Peashooter.class, Sunflower.class, WallNut.class, CherryBomb.class,
    			Chomper.class, FreezePeaShooter.class, PatatoMine.class
    	};*/
    	Class[] listOfPlant = gameInfo.getListOfPlants();
    	
    	selectedPlant %= listOfPlant.length;
    	
    	// La plante choisi
    	Class<? extends Plant> selectedPlantClass = listOfPlant[selectedPlant];

    	try {
    		// Récupère la méthode static getCost de la plante
    		Method method = selectedPlantClass.getMethod("getCost");
    		// récupère l'entier en résultat de la méthode
	    	Object result = method.invoke(null, null);
	    		 
	    	if(actTerrain.isWater(new Vector2(coords[0], coords[1])))
	    		result = (int)result + 25;
	    		
	    	
    	if (money >= (int) result) {
	    		// instancie la plante
		    	Constructor<? extends Plant> constructor = selectedPlantClass.getDeclaredConstructor(new Class[] {Vector2.class});
				constructor.newInstance(new Object[] {vector});
				money -= (int) result;	
				plantButtonList[selectedPlant].selectPlant();
		    	
			} else {
				new UI_TremblingLabel(Terrain.caseToPosition(vector), "Not enought sun", 1.5);
				
			}
	   		
    	} catch (Exception e){
    		System.out.println(e);
    	}

    	
    
    	selectPlantOfType(-1); // remet la sélection à null
    	

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
 
 
 
 

