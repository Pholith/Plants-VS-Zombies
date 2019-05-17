package main;

import java.io.FileReader;

import org.json.simple.*;
import org.json.simple.parser.*;

public class GameConfig {

	// CONSTANTES DU LEVEL MANAGER
	
	public final double spawnDelay; // temps de spawn en sec
	public final double waveDelay = 80; // temps entre chaque vague
	public final double sunSpawnDelay = 18; // temps entre chaque soleil
	public final double levelTimeDelay = 300; // temps d'une partie  

	public GameConfig() {
		spawnDelay = 16d;
		
		try {
			JSONObject jsonObject = (JSONObject) GameConfig.readJsonSimpleDemo("/Plants-VS-Zombies/src/main/config.json");
			System.out.println(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {  
	    FileReader reader = new FileReader(filename);
	    JSONParser jsonParser = new JSONParser();
	    return jsonParser.parse(reader);
	}
}
