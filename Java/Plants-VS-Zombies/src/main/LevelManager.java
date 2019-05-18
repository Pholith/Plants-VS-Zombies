package main;

import java.lang.reflect.Constructor;
import java.util.Random;
import ui.UI_Sun;
import zombies.*;
import base.Vector2;

public class LevelManager {

	private float levelAdvancement = 1; // détermine la puissance des zombies qui vont spawn
	
	private int counterOfLastZombie = 0;
	private int counterOfLastWave = 0;
	private int counterBeforeEnd = 0;
	private int counterOfSun = 10;
	
	private double spawnDelay; // temps de spawn en sec
	private final double waveDelay; // temps entre chaque vague
	private double sunSpawnDelay; // temps entre chaque soleil
	private final double levelTimeDelay; // temps d'une partie  
	
	@SuppressWarnings("rawtypes")
	Class[] listOfZombies;
	
	public LevelManager() {		
		super();
		listOfZombies = GameManager.getResources().getGameInfo().getListOfZombies();
		
		if (GameManager.getResources().getGameInfo().isNight()) {
		
		}

		spawnDelay =  GameManager.getResources().getGameConfig().getConfigDouble("spawnDelay"); 
		waveDelay = GameManager.getResources().getGameConfig().getConfigDouble("waveDelay"); 
		sunSpawnDelay = GameManager.getResources().getGameConfig().getConfigDouble("sunSpawnDelay");
		levelTimeDelay = GameManager.getResources().getGameConfig().getConfigDouble("levelTimeDelay"); 

	}

	private long lastTimeStamp = GameManager.getInstance().getClockMillis()/1000;

	
	// liste qui contient les classes de zombie 
	
	/*
	Class[] listOfZombies = new Class[] {
			SimpleZombie.class, ConeheadZombie.class, PoleVaulterZombie.class, BucketHeadZombie.class,
			FootballZombie.class, ScreenDoorZombie.class
	};*/
	
	
	
	
	// Choisi un zombie aléatoire de la liste en prenant une difficulté
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Class<? extends Zombie> getRandomZombie(Class[] listOfZombies, int coeffDifficulty) {
		// Choisir un nombre random inclus dans la liste, puis le réduire un peu pour ne pas avoir trop de difficulté
	    int rnd = new Random().nextInt(coeffDifficulty) % listOfZombies.length;
	    if (new Random().nextBoolean()) {
			rnd /= 2;
		}
	    return listOfZombies[rnd];
	}
	

	// créé un zombie aléatoirement en utilisant la liste des classes
	private void createZombie(int coeffDifficulty) {
		try {
		
			// Prend un type de Zombie aléatoire du tableau
			Class<? extends Zombie> zombieClass = getRandomZombie(listOfZombies, coeffDifficulty);
			// Class c1 = Class.forName(zombieClass.getName());
			// Cherche le constructeur avec un Vector2 de ce zombie et l'instancie
			Constructor<? extends Zombie> constructor = zombieClass.getDeclaredConstructor(new Class[] {Vector2.class});
			Vector2 vector2 = new Vector2((float) (10 + Math.random()*2), (float) Math.random() * GameManager.getResources().getTerrain().getTerrainSize().getY());

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
	@SuppressWarnings("static-access")
	public void levelEvent() {
		double timeMultiplier = 1d/GameManager.getInstance().getTimeScale();

		// compteur de seconde 
		long timeStamp = GameManager.getInstance().getClockMillis();	
		if (timeStamp - lastTimeStamp >= 1000d * timeMultiplier) {
			// incrémentation de tous les compteurs
			counterOfLastZombie ++;
			counterOfLastWave ++;
			counterBeforeEnd ++;
			if (!GameManager.getResources().getGameInfo().isNight()) {
				counterOfSun ++;
			}
			lastTimeStamp = timeStamp;
		}		
		// fin du niveau
		if (counterBeforeEnd >= levelTimeDelay) {
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
			System.out.println("Prochaine vague dans: "+Math.round(waveDelay*timeMultiplier)+" secondes");

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
			spawnDelay = Math.pow(spawnDelay, 2)/15 +2;
			System.out.println("Prochain zombie dans: "+Math.round(spawnDelay*timeMultiplier)+" secondes");
			createZombie((int) levelAdvancement);
		}
	}
}
