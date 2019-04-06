package base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.GameManager;
import zombies.*;

public class LevelManager {

	private int levelDifficulty = 1; 
	private int levelAdvancement = 0; // avancement dans le niveau
	
	private int counterOfLastZombie = 0; // temps depuis la dernière attaque en sec
	private int counterOfLastWave = 0; // temps depuis la dernière attaque en sec
	private int counterBeforeEnd = 0; // temps depuis la dernière attaque en sec
	
	private double spawnDelay = 10; // temps de spawn en sec
	private double waveDelay = 60; // temps entre chaque vague
	private double levelTimeDelay = 200; // temps d'une partie  
	
	public LevelManager() {
		super();
	}

	private long lastTimeStamp = GameManager.getInstance().getClockMillis()/1000;

	// liste qui contient les classes de zombie 
	Class[] listOfZombies = new Class[] {
			SimpleZombie.class, ConeheadZombie.class
	};
	// Choisi un zombie aléatoire de la liste en prenant une difficulté
	private static Class<? extends Zombie> getRandomZombie(Class[] listOfZombies, int coeffDifficulty) {
	    int rnd = new Random().nextInt(coeffDifficulty);
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

		// compteur de seconde 
		long timeStamp = GameManager.getInstance().getClockMillis()/1000;
		if (timeStamp > lastTimeStamp) {
			// incrémentation de tous les compteurs
			counterOfLastZombie ++;
			counterOfLastWave ++;
			counterBeforeEnd ++;
			lastTimeStamp = timeStamp;
		}
		
		// création d'une vague d'attaque
		if (counterOfLastWave >= waveDelay) {
			counterOfLastWave = 0;
			System.out.println("Prochaine vague dans: "+Math.round(waveDelay)+" secondes");
			
			for (int i = 0; i < 10; i++) {
				createZombie(2);
			}
			createFlagZombie();
			createFlagZombie();
		} 
		// création d'une attaque d'un zombie
		if (counterOfLastZombie >= spawnDelay) {
			counterOfLastZombie = 0;
			//fonction qui baisse au fur et à mesure la valeur  (non linéaire) 
			spawnDelay = Math.pow(spawnDelay, 2)/15 +2;
			System.out.println("Prochain zombie dans: "+Math.round(spawnDelay)+" secondes");
			createZombie(2);
		}
		// fin du niveau
		if (counterBeforeEnd >= levelTimeDelay) {
			// TODO GameManager.getInstance().end
		}
		
		
	}
}
