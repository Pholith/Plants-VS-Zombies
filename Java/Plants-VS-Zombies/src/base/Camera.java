package base;


import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;


import main.GameManager;


public class Camera extends GameObject {

	private float renderSize = 5.0f;//pas encore utilisé

	
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
		
	
	public void render(ArrayList<GameObject> sceneObjs, Graphics2D graphics) {
		
		int screenPixelPerUnit = Constant.screenPixelPerUnit;
		float TexturepixelPerUnit = Constant.texturePixelPerUnit;
		
		
		Sprite spr;
		Vector2 objPos;
		Vector2 finalPos;
		
		Vector2 spriteCoord1;
		Vector2 spriteCoord2;
		
		float posX = getPosition().getX();
		float posY = getPosition().getY();
		
		int spriteWidth, spriteHeight;
		UI_Element uiElem;
		
		float renderSize;
		
		
		for(GameObject obj : sceneObjs) {
		
			
			renderSize = obj.getRenderScale();
			
			//graphics.scale(renderSize, renderSize);
			
			
			if(obj instanceof UI_Element) {				
				uiElem = (UI_Element)obj;				
				uiElem.selfDisplay(getPosition(), graphics);
	
			}else{
				
			
			spr = obj.display();

				
			if(spr == null)
				continue;
			
			//System.out.println(spr);
			
			
			objPos = obj.getPosition();
					
			//mouvement de la camera
			finalPos = new Vector2(
					(objPos.getX() - posX) * screenPixelPerUnit,
					(objPos.getY() - posY) * screenPixelPerUnit);
						
			
			//	if(img == null)//En cas d'erreur de chargement du sprite, on charge un sprite "error"
			//		img = GameManager.getInstance().getImageByPath(Constant.errorTexture);
			
						
			
			spriteWidth = (int)(spr.getWidth()* renderSize);
			spriteHeight = (int)(spr.getHeight()*renderSize);
			
			spriteCoord1 = spr.getBottomLeftCorner();
			spriteCoord2 = spr.getTopRightCorner();
			
    		graphics.drawImage(spr.getBaseImg(),
    				(int)finalPos.getX(),
    				(int)finalPos.getY(),
    				
    				((int)finalPos.getX() + (int)(screenPixelPerUnit * (spriteWidth /TexturepixelPerUnit))),
    				(int)finalPos.getY() + (int)(screenPixelPerUnit * (spriteHeight/TexturepixelPerUnit)),
    				
    				
    				
    				(int)spriteCoord1.getX(),
    				(int)spriteCoord1.getY(),
    				
    				(int)spriteCoord2.getX(),
    				(int)spriteCoord2.getY(), null);			
		
		
    		
		//graphics.setColor(Color.black);		
		//graphics.drawString(  Integer.toString(cnt), 200, 200);
			}
			
			
			
		}
		
	}
}
	

	
	
	

