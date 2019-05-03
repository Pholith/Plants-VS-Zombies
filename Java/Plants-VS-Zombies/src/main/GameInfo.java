package main;

import enums.EnumTerrain;

public class GameInfo {

	private final Class[] listOfPlants; 
	private final Class[] listOfZombies; 
	private final EnumTerrain selectedTerrain;
	
	
	
	public Class[] getListOfPlants() {
		return listOfPlants;
	}
	
	public Class[] getListOfZombies() {
		return listOfZombies;
	}
	
	public EnumTerrain getSelectedTerrain() {
		return selectedTerrain;
	}
	
	public GameInfo(Class[] listOfPlants, Class[] listOfZombies, EnumTerrain selectedTerrain) {
		super();
		this.listOfPlants = listOfPlants;
		this.listOfZombies = listOfZombies;
		this.selectedTerrain = selectedTerrain;
	}
	
	
	
	
	
}
