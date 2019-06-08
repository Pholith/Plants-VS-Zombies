package main;

import java.io.Serializable;
import java.util.ArrayList;

import enums.EnumTerrain;
import plants.Plant;
import zombies.Zombie;

public class GameInfo implements Serializable{

	@SuppressWarnings("rawtypes")
	private final Class<? extends Plant>[] listOfPlants; 
	private final ArrayList<Class<? extends Zombie>> listOfGroundZombies; 
	private final ArrayList<Class<? extends Zombie>> listOfWaterZombies; 
	private final EnumTerrain selectedTerrain;
	
	
	
	
	@SuppressWarnings("rawtypes")
	public Class[] getListOfPlants() {
		return listOfPlants;
	}
	
	public ArrayList<Class<? extends Zombie>> getListOfWaterZombies() {
		return listOfWaterZombies;
	}
	public ArrayList<Class<? extends Zombie>> getListOfGroundZombies() {
		return listOfGroundZombies;
	}
	
	public EnumTerrain getSelectedTerrain() {
		return selectedTerrain;
	}
	
	@SuppressWarnings("rawtypes")
	public GameInfo(Class[] listOfPlants, ArrayList<Class<? extends Zombie>> listOfGroundZombies, ArrayList<Class<? extends Zombie>> listOfWaterZombies, EnumTerrain selectedTerrain) {
		super();
		this.listOfPlants = listOfPlants;
		this.listOfGroundZombies = listOfGroundZombies;
		this.listOfWaterZombies = listOfWaterZombies;
		this.selectedTerrain = selectedTerrain;
	}

	public boolean isNight() {
		return selectedTerrain == EnumTerrain.night_lawn || selectedTerrain == EnumTerrain.night_roof || selectedTerrain == EnumTerrain.fog;
	}
	public boolean isPool() {
		return selectedTerrain == EnumTerrain.pool || selectedTerrain == EnumTerrain.fog;
	}
	public boolean isFog() {
		return selectedTerrain == EnumTerrain.fog;
	}
	
	
	public static boolean isNight(EnumTerrain sel) {
		return sel == EnumTerrain.night_lawn || sel == EnumTerrain.night_roof || sel == EnumTerrain.fog;
	}
	public static boolean isPool(EnumTerrain sel) {
		return sel == EnumTerrain.pool || sel == EnumTerrain.fog;
	}
	public static boolean isFog(EnumTerrain sel) {
		return sel == EnumTerrain.fog;
	}
	
	
	
	
}
