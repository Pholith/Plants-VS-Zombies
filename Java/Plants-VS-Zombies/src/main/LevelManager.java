package main;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import ui.UI_Sun;
import ui.UI_WaveTitle;
import zombies.*;
import zombies.ground.FlagZombie;
import base.GameObject;
import base.Terrain;
import base.Vector2;

public class LevelManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806885033955141482L;

	private float levelAdvancement = 1; // détermine la puissance des zombies qui vont spawn
	
	private float counterOfLastZombie = 0;
	private float counterOfLastWave = 0;
	private float counterBeforeEnd = 0;
	private float counterOfSun = 10;
	
	private double spawnDelay; // temps de spawn en sec
	private final double waveDelay; // temps entre chaque vague
	private double sunSpawnDelay; // temps entre chaque soleil
	private final double levelTimeDelay; // temps d'une partie  
	
	private int waveCnt = 0;//vague Actuelle


	public LevelManager() {		
		super();
	
		new UI_WaveTitle("Les zombies arrivent !");
		
		if (GameManager.getResources().getGameInfo().isNight()) {
		
		}

		spawnDelay =  GameManager.getResources().getGameConfig().getConfigDouble("spawnDelay"); 
		waveDelay = GameManager.getResources().getGameConfig().getConfigDouble("waveDelay"); 
		sunSpawnDelay = GameManager.getResources().getGameConfig().getConfigDouble("sunSpawnDelay");
		levelTimeDelay = GameManager.getResources().getGameConfig().getConfigDouble("levelTimeDelay"); 
		
	
	}

	
	// Choisi un zombie aléatoire de la liste en prenant une difficulté
	private static Class<? extends Zombie> getRandomGroundZombie(int coeffDifficulty) {
		// Choisir un nombre random inclus dans la liste, puis le réduire un peu pour ne pas avoir trop de difficulté
		var listOfZombies = GameManager.getResources().getGameInfo().getListOfGroundZombies();
	    int rnd = new Random().nextInt(coeffDifficulty) % listOfZombies.size();
	    if (new Random().nextBoolean()) {
			rnd /= 2;
		}
	    return listOfZombies.get(rnd);
	}
	
	private static Class<? extends Zombie> getRandomWaterZombie(int coeffDifficulty) {
		// Choisir un nombre random inclus dans la liste, puis le réduire un peu pour ne pas avoir trop de difficulté
		var listOfZombies = GameManager.getResources().getGameInfo().getListOfWaterZombies();

		int rnd = new Random().nextInt(coeffDifficulty);
		if (listOfZombies.size() >= 1) {
			rnd = rnd % listOfZombies.size();

		}
	    return listOfZombies.get(rnd);
	}
	

	// créé un zombie aléatoirement en utilisant la liste des classes
	private void createZombie(int coeffDifficulty) {
		
		// Prend un type de Zombie aléatoire du tableau
		Class<? extends Zombie> zombieClass;
		Vector2 vector2;
		float randomX = (float) (10 + Math.random()*2);
		
		
		// Piscine avec eau au milieu 
		if (GameManager.getResources().getGameInfo().isPool()) {
			
			if (Math.random() < 0.2) { // Zombie dans l'eau 
				zombieClass = getRandomWaterZombie(coeffDifficulty);
				vector2 = new Vector2(randomX, (float) (2f + Math.random() * 2) );

			} else { // Zombie sur le sol
				if (Math.random() < 0.5) {
					vector2 = new Vector2(randomX, (float) (Math.random() * 2) ); // les 2 cases en haut
				} else {
					vector2 = new Vector2(randomX, (float) (4f + Math.random() * 2) ); // les 2 cases en bas
				}
				zombieClass = getRandomGroundZombie(coeffDifficulty);
			}
		}
		// Terrain normal
		else {
			zombieClass = getRandomGroundZombie(coeffDifficulty);
			vector2 = new Vector2(randomX, (float) Math.random() * 5);
		}
		instanceZombie(zombieClass, vector2);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void instanceZombie(Class zombieClass, Vector2 vector2) {
		try {
			// Cherche le constructeur avec un Vector2 de ce zombie et l'instancie
			Constructor<? extends Zombie> constructor = zombieClass.getDeclaredConstructor(new Class[] {Vector2.class});
			constructor.newInstance(new Object[] {vector2});

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
		}
	}
	
	// créé un zombie de vague
	private void createFlagZombie() {
		new FlagZombie(Vector2.randomStartVector());
	}
	
	// gère les attaques de zombies et les niveaux
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void levelEvent() {

		// incrémentation de tous les compteurs
		counterOfLastZombie 	+= GameManager.getInstance().getDeltatime();
		counterOfLastWave 		+= GameManager.getInstance().getDeltatime();
		counterBeforeEnd		+= GameManager.getInstance().getDeltatime();

		if (!GameManager.getResources().getGameInfo().isNight()) {
			counterOfSun += GameManager.getInstance().getDeltatime();
		}
		// fin du niveau
		if (counterBeforeEnd >=  levelTimeDelay) {
			  GameManager.getInstance().endGame(true);
			  return;
		}
		// invocation de soleil aléatoire
		if (counterOfSun >= sunSpawnDelay) {
			new UI_Sun(new Vector2((float) Math.random()*5 + 3, (float) Math.random()*4 + 1), func -> { GameManager.getInstance();
				GameManager.getResources().getASun(); });
			counterOfSun = 0;
			sunSpawnDelay ++;
		}
		// création d'une vague d'attaque
		if (counterOfLastWave >= waveDelay) {
			counterOfLastWave = 0;
			
			if (waveCnt%2 == 0) { // Toutes les vagues pairs
				
				Function f = new Function<Vector2, Boolean>() {
					@Override
					public Boolean apply(Vector2 v) {
						System.out.println(v);
						instanceZombie(getRandomGroundZombie(3), v);
						return true;
					}
				};
				GameManager.getResources().getTerrain().doThisIfGraveStone(f);

			}
			System.out.println("Prochaine vague dans: " + Math.round(waveDelay)+" secondes");
			
			waveCnt++;
			new UI_WaveTitle("Vague "+waveCnt);
			
			createFlagZombie();
			createFlagZombie();
			
			for (int i = 0; i < 10; i++) {
				createZombie((int) levelAdvancement);
			}
		} 
		// création d'une attaque d'un zombie
		if (counterOfLastZombie >= spawnDelay) {
			counterOfLastZombie = 0;
			levelAdvancement += 0.4;
			
			//fonction qui baisse au fur et à mesure la valeur  (non linéaire)
			spawnDelay -= spawnDelay / 20;
			if (spawnDelay <= 2.5) {
				spawnDelay = 2.5;
			}
			System.out.println("Prochain zombie dans: "+Math.round(spawnDelay)+" secondes");
			createZombie((int) levelAdvancement);
		}
	}
   
    
    
}
