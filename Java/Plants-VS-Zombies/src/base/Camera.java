package base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.Const;

import main.GameManager;


public class Camera extends GameObject {

	private float renderSize = 5.0f;

	public Camera(Vector2 pos,  float renderSize) {
		super(pos);
		this.renderSize = renderSize;
	}	
	public Camera(Vector2 pos) {
		this(pos, 5.0f);
	}
	public Camera() {
		this(Vector2.zero());
	}
		
	
	public void Render(ArrayList<GameObject> sceneObjs,Graphics2D graphics) {
		
		int screenPixelPerUnit = 128;
		float TexturepixelPerUnit = 32;
		
		
		String link;
		Image img;
		Vector2 objPos;
		Vector2 finalPos;
		float posX = getPosition().getX();
		float posY = getPosition().getX();
		
		int spriteWidth, spriteHeight;
		
		
		for(GameObject obj : sceneObjs) {
			
			
			link = obj.display();			
			img = GameManager.getInstance().getImageByPath(link);
			
			objPos = obj.getPosition();
					
			//mouvement de la camera
			finalPos = new Vector2((objPos.getX()-posX)*screenPixelPerUnit, (objPos.getY()-posY) * screenPixelPerUnit);
			
			
			if(img == null)//En cas d'erreur de chargement du sprite, on charge un sprite "error"
				img = GameManager.getInstance().getImageByPath("resources/textures/error.png");
			
			spriteWidth = (int)img.getWidth(null);
			spriteHeight = (int)img.getHeight(null);
					
    		graphics.drawImage(img, (int)finalPos.getX(), (int)finalPos.getY(),(int)finalPos.getX() + screenPixelPerUnit * (int)(spriteHeight/TexturepixelPerUnit), (int)finalPos.getY() + screenPixelPerUnit * (int)(spriteHeight/TexturepixelPerUnit), 0, 0, spriteWidth, spriteHeight, null);			
		
		
		//graphics.setColor(Color.black);		
		//graphics.drawString(  Integer.toString(cnt), 200, 200);
		
	}
	}
}
	
	
	

