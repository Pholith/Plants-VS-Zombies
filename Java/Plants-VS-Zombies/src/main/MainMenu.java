package main;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

import base.Constant;
import base.Sprite;
import base.Vector2;
import enums.EnumTerrain;
import ui.UI_Button;
import ui.UI_Label;
import ui.UI_Sprite;
import zombies.Zombie;

public final class MainMenu {
	
	 static void start_menu() {
			//new UI_Label(new Vector2(2,1.5f), "Plant VS zombies", Color.blue, 5f);
		 new UI_Sprite(new Vector2( (GameManager.getInstance().getResolutionX()/Constant.screenPixelPerUnit)/2f, 0), GameManager.getResources().getAnimationByPath("titlepage2.jpg")[0]);
			new UI_Button(new Vector2(3f, 3f),1f,Color.white, 200f,60f, new Vector2(0,0.7f), func -> {terrain_menu();});	
			new UI_Label(new Vector2(3f, 3f), "Commencer", Color.black,3f);
		 }
		 
	 
	 private static void terrain_menu() {
			GameManager.getInstance().clearScene();
			
			float yFirstPos = 1.2f;
			float spaceBetweenY = 1.6f;
			float spaceBetweenX = 2f;

			new UI_Label(new Vector2(2, 1f), "Choisissez votre terrain", Color.blue, 5f);
			
			new UI_Button(new Vector2(2f, yFirstPos + spaceBetweenY * 1), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("lawn.jpg"), 400), func -> { plant_menu(EnumTerrain.lawn);});
			new UI_Button(new Vector2(2f, yFirstPos + spaceBetweenY * 2), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("nightTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.night_lawn);});
			new UI_Button(new Vector2(2f, yFirstPos + spaceBetweenY * 3), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("poolTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.pool);});
			new UI_Button(new Vector2(2f + spaceBetweenX * 2, yFirstPos + spaceBetweenY * 1), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("fogTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.fog);});
			new UI_Button(new Vector2(2f + spaceBetweenX * 2, yFirstPos + spaceBetweenY * 2), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("roofTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.roof);});
		 }
			
	private static int selectCnt = 0;
	private static int actCnt = 0;
	
	
	// Sélectionne aléatoirement les zombies du jeu selon le terrain puis créé l'interface
	@SuppressWarnings("rawtypes")
	private static Class[] selectZombies(EnumTerrain terrain) {
		
		int numberOfZombieType = GameManager.getResources().getGameConfig().getConfigInt("numberOfZombieType");
		
		Class[] listOfZombies = new Class[numberOfZombieType];
		
		
		Class[] totalZombies;

		if (GameInfo.isPool(terrain)) {
			
			totalZombies = Stream.concat(Arrays.stream(GameManager.getResources().getZombiesTotalList()), Arrays.stream(GameManager.getResources().getZombiesWaterList()))
                    .toArray(Class[]::new);
			
		} else {
			totalZombies = GameManager.getResources().getZombiesTotalList();
		}


		new UI_Label(new Vector2(8, 1.2f), "Ces Zombies vont vous attaquer", Color.red, 4f);

		// Les zombies simples qui sont toujours présents
		listOfZombies[0] = totalZombies[0];
		listOfZombies[1] = totalZombies[1];
		

		for (int i = 2; i < numberOfZombieType; i++) {
			int rand = (int) (Math.random() * totalZombies.length); // à optimiser 

			listOfZombies[i] = totalZombies[rand];
		}
		
		for (int j = 0; j < listOfZombies.length; j++) {
			new UI_Sprite(new Vector2(10f+ (j % 3) * 1.5f, 3f + 1.6f* (j / 3)), GameManager.getResources().getAnimationByPath("zombies/" + listOfZombies[j].getSimpleName() +".png")[0]);
		}

		return listOfZombies;
	}
	
	// constuit le menu de sélection des plantes
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private static void plant_menu(EnumTerrain terrain) {
		GameManager.getInstance().clearScene();
		new UI_Label(new Vector2(1.5f, 1.2f), "Choisissez vos plantes", Color.green, 4f);
		int i = 0;
	
		ArrayList<UI_Button> listButtons = new ArrayList<UI_Button>();
		
		selectCnt = GameManager.getResources().getGameConfig().getConfigInt("numberOfPlantSelectable");
		
		Class[] listOfPlants = new Class[selectCnt];
		Class[] totalplants = GameManager.getResources().getPlantsTotalList();
		
		Class[] listOfZombies = selectZombies(terrain);
		
		
		
		// On construit et détruit des zombies pour les ranger dans l'eau ou non 
		
		ArrayList<Class<? extends Zombie>> groundZombies = new ArrayList<Class<? extends Zombie>>();
		ArrayList<Class<? extends Zombie>> waterZombies = new ArrayList<Class<? extends Zombie>>();
		
		for (Class zombieClass : listOfZombies) {
			
			Constructor<? extends Zombie> constructor;
			
			try {
				
				constructor = zombieClass.getDeclaredConstructor(new Class[] {Vector2.class});
				Zombie instance = constructor.newInstance(new Object[] {new Vector2(5, 5)});
				if (instance.isWaterZombie()) {
					waterZombies.add(zombieClass);
				} else {
					groundZombies.add(zombieClass);
				}
				instance.forcedDestruction();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		var numberOfPlantSelectable = GameManager.getResources().getGameConfig().getConfigDouble("numberOfPlantSelectable");

		for(i = 0; i < totalplants.length; i++) {
			int b = i; // obligé
			listButtons.add(new UI_Button(new Vector2(1f+ (i%6) *1.5f, 2.5f + 1f* (i/6)), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("cards/" + totalplants[i].getSimpleName() +"_icon.png"), 75), func -> {
					selectButtonList(listButtons, listOfPlants, totalplants, b, fonc -> {GameManager.getResources().startGame(new GameInfo(listOfPlants, groundZombies, waterZombies, terrain));} );}));			
		}
		listButtons.add(new UI_Button(new Vector2(1f+ (i%6) *1.5f, 2.5f + 1f* (i/6)), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("cards/shovel_icon.png"), 75), func -> {selectButtonList(listButtons, listOfPlants, totalplants, -1, fonc -> {});}));			
		
		
		//plant_menu(terrain, listOfZombies);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void selectButtonList(ArrayList<UI_Button> list, Class[] finalList, Class[] totalList,int idSelect, Consumer exitFunction) {
		
		if(idSelect == -1) {
			for(int i = 0; i < list.size(); i++)
				list.get(i).setDisable(false);

			actCnt = 0;
			return;
		}
		
		list.get(idSelect).setDisable(true);
		
		finalList[actCnt] =  totalList[idSelect];
		actCnt++;
		
		if(selectCnt == actCnt) {
			actCnt = 0;
			exitFunction.accept(null);
		
		}
	}
	
	
	
	
}
