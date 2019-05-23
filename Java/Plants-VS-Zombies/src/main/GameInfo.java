package main;

import enums.EnumTerrain;

public class GameInfo {

	@SuppressWarnings("rawtypes")
	private final Class[] listOfPlants; 
	@SuppressWarnings("rawtypes")
	private final Class[] listOfZombies; 
	private final EnumTerrain selectedTerrain;
	
	
	
	@SuppressWarnings("rawtypes")
	public Class[] getListOfPlants() {
		return listOfPlants;
	}
	
	@SuppressWarnings("rawtypes")
	public Class[] getListOfZombies() {
		return listOfZombies;
	}
	
	public EnumTerrain getSelectedTerrain() {
		return selectedTerrain;
	}
	
	@SuppressWarnings("rawtypes")
	public GameInfo(Class[] listOfPlants, Class[] listOfZombies, EnumTerrain selectedTerrain) {
		super();
		this.listOfPlants = listOfPlants;
		this.listOfZombies = listOfZombies;
		this.selectedTerrain = selectedTerrain;
	}

	public boolean isNight() {
		return selectedTerrain == EnumTerrain.night_lawn || selectedTerrain == EnumTerrain.night_roof || selectedTerrain == EnumTerrain.fog;
	}
	public boolean isPool() {
		return selectedTerrain == EnumTerrain.pool || selectedTerrain == EnumTerrain.fog;
	}
	
	
	
}
