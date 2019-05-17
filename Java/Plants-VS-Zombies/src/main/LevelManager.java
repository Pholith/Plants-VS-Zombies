package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ui.UI_Sun;
import zombies.*;
import base.Constant;
import base.Gravestone;
import base.Terrain;
import base.Vector2;
import plants.day.Peashooter;

public class LevelManager {

	private int levelDifficulty = 1; 
	private float levelAdvancement = 1; // d�termine la puissance des zombies qui vont spawn
	
	private int counterOfLastZombie = 0;
	private int counterOfLastWave = 0;
	private int counterBeforeEnd = 0;
	private int counterOfSun = 10;
	
	private double spawnDelay =  GameManager.getResources().getGameConfig().spawnDelay; // temps de spawn en sec
	private double waveDelay = GameManager.getResources().getGameConfig().waveDelay; // temps entre chaque vague
	private double sunSpawnDelay = GameManager.getResources().getGameConfig().sunSpawnDelay; // temps entre chaque soleil
	private double levelTimeDelay = GameManager.getResources().getGameConfig().levelTimeDelay; // temps d'une partie  
	
	Class[] listOfZombies;
	
	public LevelManager() {		
		super();
		listOfZombies = GameManager.getResources().getGameInfo().getListOfZombies();
		if (GameManager.getResources().getGameInfo().isNight()) {
			// TODO mettre des tombes
		}		
	}

	private long lastTimeStamp = GameManager.getInstance().getClockMillis()/1000;

	
	// liste qui contient les classes de zombie 
	
	/*
	Class[] listOfZombies = new Class[] {
			SimpleZombie.class, ConeheadZombie.class, PoleVaulterZombie.class, BucketHeadZombie.class,
			FootballZombie.class, ScreenDoorZombie.class
	};*/
	
	
	
	
	// Choisi un zombie al�atoire de la liste en prenant une difficult�
	private static Class<? extends Zombie> getRandomZombie(Class[] listOfZombies, int coeffDifficulty) {
		// Choisir un nombre random inclus dans la liste, puis le r�duire un peu pour ne pas avoir trop de difficult�
	    int rnd = new Random().nextInt(coeffDifficulty) % listOfZombies.length;
	    if (new Random().nextBoolean()) {
			rnd /= 2;
		}
	    return listOfZombies[rnd];
	}
	

	// cr�� un zombie al�atoirement en utilisant la liste des classes
	private void createZombie(int coeffDifficulty) {
		try {
		
			// Prend un type de Zombie al�atoire du tableau
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
	
		// cr�� un zombie de vague
	private void createFlagZombie() {
		new FlagZombie(Vector2.randomStartVector());
	}
	
	// g�re les attaques de zombies et les niveaux
	public void levelEvent() {
		double timeMultiplier = 1d/(double)GameManager.getInstance().getTimeScale();

		// compteur de seconde 
		long timeStamp = GameManager.getInstance().getClockMillis();	
		if (timeStamp - lastTimeStamp >= 1000d * timeMultiplier) {
			// incr�mentation de tous les compteurs
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
		// invocation de soleil al�atoire
		if (counterOfSun >= sunSpawnDelay) {
			new UI_Sun(new Vector2((float) Math.random()*5 + 3, (float) Math.random()*4 + 1), func -> { GameManager.getInstance().getResources().getASun(); });
			counterOfSun = 0;
			sunSpawnDelay ++;
		}
		// cr�ation d'une vague d'attaque
		if (counterOfLastWave >= waveDelay) {
			counterOfLastWave = 0;
			System.out.println("Prochaine vague dans: "+Math.round(waveDelay*timeMultiplier)+" secondes");

			createFlagZombie();
			createFlagZombie();
			
			for (int i = 0; i < 10; i++) {
				createZombie((int) levelAdvancement);
			}
		} 
		// cr�ation d'une attaque d'un zombie
		if (counterOfLastZombie >= spawnDelay) {
			counterOfLastZombie = 0;
			levelAdvancement += 0.4;
			//fonction qui baisse au fur et � mesure la valeur  (non lin�aire) 
			spawnDelay = Math.pow(spawnDelay, 2)/15 +2;
			System.out.println("Prochain zombie dans: "+Math.round(spawnDelay*timeMultiplier)+" secondes");
			createZombie((int) levelAdvancement);
		}
	}
}
