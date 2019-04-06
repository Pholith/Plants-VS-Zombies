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
	
	private int counterOfLastZombie = 0; // temps depuis la derni�re attaque en sec
	private int counterOfLastWave = 0; // temps depuis la derni�re attaque en sec
	private int counterBeforeEnd = 0; // temps depuis la derni�re attaque en sec
	
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
	// Choisi un zombie al�atoire de la liste en prenant une difficult�
	private static Class<? extends Zombie> getRandomZombie(Class[] listOfZombies, int coeffDifficulty) {
	    int rnd = new Random().nextInt(coeffDifficulty);
	    return listOfZombies[rnd];
	}

	// cr�� un zombie al�atoirement en utilisant la liste des classes
	private void createZombie(int coeffDifficulty) {
		try {
			// Prend un type de Zombie al�atoire du tableau
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
	// cr�� un zombie de vague
	private void createFlagZombie() {
		new FlagZombie(Vector2.randomStartVector());
	}
	
	// g�re les attaques de zombies et les niveaux
	public void levelEvent() {

		// compteur de seconde 
		long timeStamp = GameManager.getInstance().getClockMillis()/1000;
		if (timeStamp > lastTimeStamp) {
			// incr�mentation de tous les compteurs
			counterOfLastZombie ++;
			counterOfLastWave ++;
			counterBeforeEnd ++;
			lastTimeStamp = timeStamp;
		}
		
		// cr�ation d'une vague d'attaque
		if (counterOfLastWave >= waveDelay) {
			counterOfLastWave = 0;
			System.out.println("Prochaine vague dans: "+Math.round(waveDelay)+" secondes");
			
			for (int i = 0; i < 10; i++) {
				createZombie(2);
			}
			createFlagZombie();
			createFlagZombie();
		} 
		// cr�ation d'une attaque d'un zombie
		if (counterOfLastZombie >= spawnDelay) {
			counterOfLastZombie = 0;
			//fonction qui baisse au fur et � mesure la valeur  (non lin�aire) 
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
