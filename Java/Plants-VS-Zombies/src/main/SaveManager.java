package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import base.GameObject;

public class SaveManager {

	ObjectOutputStream objectOutputStream;

	public static void save(ArrayList<GameObject> gameContent) { // TO CHANGE gamecontent

		;
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream fichier = new FileOutputStream("resources/save.ser");
			oos = new ObjectOutputStream(fichier);
			try {
				for (GameObject gameObject : gameContent) {
					System.out.println("Saving " + gameObject.name()+"...");
					oos.writeObject(gameContent);
				}

			} catch (Exception e) {
				System.out.println("FORCED STOP OF SAVING");
				System.out.println(e);
			}
			oos.flush();
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		
		}
	}
}
