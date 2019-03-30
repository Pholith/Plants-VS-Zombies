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
	private double spawnDelay = 10; // temps de spawn en sec
	
	
	public LevelManager() {
		super();
	}

	private long lastTimeStamp = GameManager.getInstance().getClockMillis()/1000;

	// liste qui contient les classes de zombie 
	Class[] listOfZombies = new Class[] {
			SimpleZombie.class, FlagZombie.class, ConeheadZombie.class
	};
	// Choisi un zombie aléatoire de la liste en prenant une difficulté
	private static Class<? extends Zombie> getRandomZombie(Class[] listOfZombies, int coeffDifficulty) {
	    int rnd = new Random().nextInt(coeffDifficulty);
	    return listOfZombies[rnd];
	}

	public void levelEvent() {
		
		long timeStamp = GameManager.getInstance().getClockMillis()/1000;
		// compteur de seconde 
		if (timeStamp > lastTimeStamp) {
			counterOfLastZombie ++;
			lastTimeStamp = timeStamp;
		}
		
		
		if (counterOfLastZombie >= spawnDelay) {
			counterOfLastZombie = 0;
			//fonction qui baisse au fur et à mesure la valeur  (non linéaire) 
			spawnDelay = Math.pow(spawnDelay, 2)/15 +2;
			System.out.println("Prochain zombie dans: "+Math.round(spawnDelay)+" secondes");
			try {
				// Prend un type de Zombie aléatoire du tableau
				Class<? extends Zombie> zombieClass = getRandomZombie(listOfZombies, 2);
				//Class c1 = Class.forName(zombieClass.getName());
				// Cherche le constructeur avec un Vector2 de ce zombie et l'instancie
				Constructor constructor = zombieClass.getDeclaredConstructor(new Class[] {Vector2.class});
				constructor.newInstance(new Object[] {new Vector2(6, 4)}); // TODO rendre random le vecteur
	
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getCause());
			}
		}		
		
		
	}
}
