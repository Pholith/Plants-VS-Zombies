package main;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;
import java.util.function.Consumer;

import base.Constant;
import base.UI_Element;
import base.Vector2;
import ui.UI_Button;
import ui.UI_Label;

public class PauseMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2501303519968046305L;

	@SuppressWarnings("rawtypes")
	PauseMenu(float screenResolutionX,float screenResolutionY, Consumer resumeFunc, Consumer saveFunc, Consumer loadFunc, Consumer exitFunc) {
		
		elements = new HashSet<UI_Element>();
		visible = false;
		float middlePosX = (screenResolutionX /Constant.screenPixelPerUnit)/2f;
		float middlePosLabX = middlePosX - 0.75f;
		
		float yFirstPos = 1.2f;
		float spaceBetween = 1f;
		float sizeX = 400f;
		float sizeY = 100f;
		
		// RECTANGLE MENU

		// TODO 
		
		// BOUTON REPRENDRE
		elements.add(new UI_Button(new Vector2(middlePosX, yFirstPos + spaceBetween * 1 ), 1f, Color.lightGray, sizeX , sizeY , new Vector2(0.5f, 0.5f), resumeFunc, 95 ));
		elements.add(new UI_Label(new Vector2(middlePosLabX, yFirstPos + spaceBetween * 1 ), "Reprendre", Color.black, 3f, 96));

		// BOUTON SAUVEGARDER
		elements.add(new UI_Button(new Vector2(middlePosX, yFirstPos + spaceBetween * 2 ), 1f, Color.lightGray, sizeX , sizeY , new Vector2(0.5f, 0.5f), saveFunc, 95 ));
		elements.add(new UI_Label(new Vector2(middlePosLabX, yFirstPos + spaceBetween * 2 ), "Sauvegarder", Color.black, 3f, 96));

		// BOUTON CHARGER
		elements.add(new UI_Button(new Vector2(middlePosX, yFirstPos + spaceBetween * 3 ), 1f, Color.lightGray, sizeX , sizeY , new Vector2(0.5f, 0.5f), loadFunc, 95 ));
		elements.add(new UI_Label(new Vector2(middlePosLabX, yFirstPos + spaceBetween * 3 ), "Menu", Color.black, 3f, 96));

		// BOUTON QUITTER
		elements.add(new UI_Button(new Vector2(middlePosX, yFirstPos + spaceBetween * 4 ), 1f, Color.lightGray, sizeX , sizeY , new Vector2(0.5f, 0.5f), exitFunc, 95 ));
		elements.add(new UI_Label(new Vector2(middlePosLabX, yFirstPos + spaceBetween * 4 ), "Quitter", Color.black, 3f, 96));

		for (UI_Element ui_Element : elements) {
			ui_Element.hide();
		}
	}
	
	private Boolean visible; 
	private HashSet<UI_Element> elements;
	
	public boolean isVisible() {
		return visible;
	}
	public void show() {
		visible = true;
		for (UI_Element ui_Element : elements) {
			ui_Element.show();
		}
	}
	
	public void hide() {
		visible = false;
		for (UI_Element ui_Element : elements) {
			ui_Element.hide();
		}
	}
	
	
}
