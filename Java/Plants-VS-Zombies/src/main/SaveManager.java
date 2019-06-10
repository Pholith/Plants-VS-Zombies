package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import base.Constant;

public class SaveManager {

	ObjectOutputStream objectOutputStream;

	public static boolean save(SaveInstance toSave) { // TO CHANGE gamecontent

		boolean end = true;
		ObjectOutputStream oos = null;
		try {
			final FileOutputStream fichier = new FileOutputStream(Constant.savePath + "save_"+GameManager.getInstance().getClockMillis()+".ser");
			oos = new ObjectOutputStream(fichier);
			try {

				/*
				 //Pour tester les gameObjects serialisables :
				for (var obj : gameContent) {					 
					System.out.println("Saving " + obj.name() + "...");
					if (!(obj instanceof UI_Button))
						oos.writeObject(obj);
					else
						System.out.println("Skipped");
				}	*/

				oos.writeObject(toSave);



			} catch (Exception e) {
				System.out.println("SAVING WAS CANCELED !");
				System.out.println(e);
				end = false;
			}
			oos.flush();

		} catch (final java.io.IOException e) {
			e.printStackTrace();
			end = false;
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
					System.out.println("Saved !");
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

		return end;
	}




	public static SaveInstance load(String saveName) {
		SaveInstance sp = null;

		if(saveName == null) {
			return null;
		}

		Path pt = Paths.get(saveName);

		System.out.println("Loading save at path "+pt);

		try (InputStream str = Files.newInputStream(pt);
				ObjectInputStream in=new ObjectInputStream(str)) {

			sp=(SaveInstance)in.readObject();


		} catch (Exception e) {
			System.out.println("Error : Cannot read this save "+pt);
			e.printStackTrace();
			return null;
		}


		return sp;		
	}

}
