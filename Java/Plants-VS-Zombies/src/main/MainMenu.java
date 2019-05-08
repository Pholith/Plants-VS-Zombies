package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import base.Constant;
import base.GameObject;
import base.Sprite;
import base.Vector2;
import enums.EnumTerrain;
import ui.UI_Button;
import ui.UI_Label;
import ui.UI_PlantButton;
import ui.UI_Sprite;

public final class MainMenu {
	
	 static void start_menu() {
			//new UI_Label(new Vector2(2,1.5f), "Plant VS zombies", Color.blue, 5f);
		 new UI_Sprite(new Vector2( (GameManager.getInstance().getResolutionX()/Constant.screenPixelPerUnit)/2f, 0), GameManager.getResources().getAnimationByPath("titlepage2.jpg")[0]);
			new UI_Button(new Vector2(3f, 3f),1f,Color.white, 200f,60f, new Vector2(0,0.7f), func -> {terrain_menu();});	
			new UI_Label(new Vector2(3f, 3f), "Commencer", Color.black,3f);
		 }
		 
	 
	 private static void terrain_menu() {
			GameManager.getInstance().clearScene();
			new UI_Label(new Vector2(2,1.5f), "Choisissez votre terrain", Color.blue, 5f);
			new UI_Button(new Vector2(2f, 3f), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("lawn.jpg"), 400), func -> { plant_menu(EnumTerrain.lawn);});
			new UI_Button(new Vector2(2f, 4.5f), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("nightTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.night_lawn);});
			new UI_Button(new Vector2(2f, 6f), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("poolTerrain.jpg"), 400), func -> { plant_menu(EnumTerrain.pool);});
		 }
			
	private static int selectCnt = 0;
	private static int actCnt = 0;
	
	private static Class[] selectZombies() {
		Class[] listOfZombies = new Class[6];
		Class[] totalZombies = GameManager.getResources().getZombiesTotalList();

		new UI_Label(new Vector2(8,1.5f), "Ces Zombies vont vous attaquer", Color.red, 4f);

		// Les zombies simples qui sont toujours présents
		listOfZombies[0] = totalZombies[0];
		listOfZombies[1] = totalZombies[1];
		

		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * totalZombies.length); // à optimiser 
			System.out.println("random = " + rand);

			listOfZombies[2 + i] = totalZombies[rand];
		}
		
		for (int j = 0; j < listOfZombies.length; j++) {
			new UI_Sprite(new Vector2(9.5f+ (j % 3) *1f, 4f + 2f* (j / 3)), GameManager.getResources().getAnimationByPath("zombies/" + listOfZombies[j].getSimpleName() +".png")[0]);
		}

		return listOfZombies;
	}
	
	private static void plant_menu(EnumTerrain terrain) {
		GameManager.getInstance().clearScene();
		new UI_Label(new Vector2(1.5f, 1.5f), "Choisissez vos plantes", Color.green, 4f);
		int i = 0;
	
		ArrayList<UI_Button> listButtons = new ArrayList<UI_Button>();
		
		selectCnt = 6;
		
		Class[] listOfPlants = new Class[selectCnt];
		Class[] totalplants = GameManager.getResources().getPlantsTotalList();
		
		Class[] listOfZombies = selectZombies();
		Class[] totalZombies = GameManager.getResources().getZombiesTotalList();
		
		for(i = 0; i < totalplants.length; i++) {
			int b = i;//obligé
			listButtons.add(new UI_Button(new Vector2(1.5f+ (i%5) *1.5f, 2.5f + 1f* (i/5)), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("cards/" + totalplants[i].getSimpleName() +"_icon.png"), 75), func -> {
					selectButtonList(listButtons, listOfPlants, totalplants, b, fonc -> {GameManager.getResources().startGame(new GameInfo(listOfPlants, listOfZombies, terrain));} );}));			
		}
		listButtons.add(new UI_Button(new Vector2(1.5f+ (i%5) *1.5f, 2.5f + 1f* (i/5)), 1f, Color.black, new Sprite(GameManager.getResources().getImageByPath("cards/shovel_icon.png"), 75), func -> {selectButtonList(listButtons, listOfPlants, totalplants, -1, fonc -> {});}));			
		
		
		//plant_menu(terrain, listOfZombies);
	}
	
	
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
