package main;

import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;

import org.json.simple.*;
import org.json.simple.parser.*;

public class GameConfig implements Serializable {

	// CONSTANTES DU LEVEL MANAGER

	/**
	 * 
	 */
	private static final long serialVersionUID = -105588413660771305L;

	private HashMap<String, String> configMap;
	private JSONObject jsonObject; // HashMap provenant du JSON 

	@SuppressWarnings("unchecked") // on sait ce qu'on cast donc c'est bon tant que le json contient des strings (normal ?)
	public GameConfig() {

		try {
			jsonObject = (JSONObject) GameConfig.readJsonSimpleDemo("resources/config.json");
			System.out.println("JSON SUCCEFULLY LOADED: \n" + jsonObject);
			configMap = (HashMap<String, String>) jsonObject.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {  
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}
	public Float getConfigFloat(String str) {
		return Float.valueOf((String) jsonObject.get(str));
	}

	public Double getConfigDouble(String str) {
		return Double.valueOf((String) jsonObject.get(str));
	}

	public int getConfigInt(String str) {
		return Integer.valueOf((String) jsonObject.get(str));
	}

	public String getConfigString(String str) {
		return configMap.get(str);
	}

	/*  REGEX pour mettre en forme le format json

	.* ([a-Z]*) = ([0-9]*);.*

	\"$1": "$2",

	 */
}
