package main;

import java.io.Serializable;
import java.util.ArrayList;

import base.GameObject;
import ui.UI_Button;
import ui.UI_Label;
import ui.UI_Sprite;

//Classe à serialiser, qui comporte toute les informations du jeux a enregistrer.
public class SaveInstance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4772128883088207847L;

	//Resources
	private final int money;

	//GameManager
	private final ArrayList<GameObject> gameContent;
	private final GameInfo gameInf;

	//Level manager
	private final LevelManager levelManage;

	public LevelManager getLevelManage() {
		return levelManage;
	}

	public SaveInstance(ArrayList<GameObject> gameObjects, int money, GameInfo gameInf, LevelManager levelManage) {
		super();


		gameContent = new ArrayList<GameObject>();

		for(var obj : gameObjects) {

			if( obj instanceof UI_Button || obj instanceof UI_Label || obj instanceof UI_Sprite) {
				continue;
			}

			gameContent.add(obj);
		}	

		this.money = money;
		this.gameInf = gameInf;		
		this.levelManage = levelManage;

	}


	public GameInfo getInfo() {
		return gameInf;
	}

	public ArrayList<GameObject> getGameContent() {
		return gameContent;
	}
	public int getMoney() {
		return money;
	}

}
