package base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.GameManager;
import plants.Peashooter;
import zombies.*;
import base.Terrain;

public class LevelManager {

	private int levelDifficulty = 1; 
	private float levelAdvancement = 1; // détermine la puissance des zombies qui vont spawn
	
	private int counterOfLastZombie = 0; // temps depuis la dernière attaque en sec
	private int counterOfLastWave = 0; // temps depuis la dernière attaque en sec
	private int counterBeforeEnd = 0; // temps depuis la dernière attaque en sec
	
	private double spawnDelay = 10; // temps de spawn en sec
	private double waveDelay = 80; // temps entre chaque vague
	private double levelTimeDelay = 220; // temps d'une partie  
	

	public LevelManager() {
		super();
	}

	private long lastTimeStamp = GameManager.getInstance().getClockMillis()/1000;

	// liste qui contient les classes de zombie 
	Class[] listOfZombies = new Class[] {
			SimpleZombie.class, ConeheadZombie.class, PoleVaulterZombie.class, BucketHeadZombie.class,
			FootballZombie.class, ScreenDoorZombie.class
	};
	
	
	// Choisi un zombie aléatoire de la liste en prenant une difficulté
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
			//Class c1 = Class.forName(zombieClass.getName());
			// Cherche le constructeur avec un Vector2 de ce zombie et l'instancie
			Constructor constructor = zombieClass.getDeclaredConstructor(new Class[] {Vector2.class});
			constructor.newInstance(new Object[] {Vector2.randomStartVector()}); // TODO rendre random le vecteur

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
	public void levelEvent() {
		double timeMultiplier = 1d/(double)GameManager.getInstance().getTimeScale();

		// compteur de seconde 
		long timeStamp = GameManager.getInstance().getClockMillis();	
		if (timeStamp - lastTimeStamp >= 1000d * timeMultiplier) {
			// incrémentation de tous les compteurs
			counterOfLastZombie ++;
			counterOfLastWave ++;
			counterBeforeEnd ++;
			lastTimeStamp = timeStamp;
		}		
		// fin du niveau
		if (counterBeforeEnd >= levelTimeDelay) {
			  GameManager.getInstance().endGame(true);
			  return;
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
