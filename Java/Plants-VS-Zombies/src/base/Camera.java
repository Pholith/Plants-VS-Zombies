package base;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Camera extends GameObject implements Serializable {

	private Vector2 speed;
	private static final long serialVersionUID = -6148888095804149524L;



	public Camera(Vector2 pos) {
		super(pos);
		speed = Vector2.zero();
	}

	public Camera() {
		this(Vector2.zero());
	}

	@Override
	public void update() {
		translation(speed);
		slowing();
	}


	private void slowing() {
		float xSpeed = 0;
		if (speed.getX() > 0) {
			xSpeed = speed.getX() - 0.004f;
			if (xSpeed <= 0) {
				xSpeed = 0;
			}
		}
		if (speed.getX() <= 0) {
			xSpeed = speed.getX() + 0.004f;
			if (xSpeed >= 0) {
				xSpeed = 0;
			}
		}
		speed = new Vector2(xSpeed, 0);
	}

	private void checkSpeedMax() {
		float maxSpeed = 0.1f;
		if (speed.getX() > maxSpeed) {
			speed = new Vector2(maxSpeed, 0f);
		} else if (speed.getX() < -maxSpeed) {
			speed = new Vector2(-maxSpeed, 0f);
		}
	}
	public void pushRight() {
		speed = speed.add(0.05f, 0);
		checkSpeedMax();
	}
	public void pushLeft() {
		speed = speed.add(-0.05f, 0);
		checkSpeedMax();
	}

	public void render(ArrayList<GameObject> sceneObjs, Graphics2D graphics) {



		for(GameObject obj : sceneObjs) {


			//	renderSize = obj.getRenderScale();


			switch(obj.getRenderMode()){


			case Sprite:
				DrawSprite(obj, graphics);					
				break;


			case Both:				
				DrawSprite(obj, graphics);							
			case Self:				
				obj.selfDisplay(getPosition(), graphics);				
				break;

				//graphics.setColor(Color.black);		
				//graphics.drawString(  Integer.toString(cnt), 200, 200);
			}
		}		
	}

	void DrawSprite(GameObject obj, Graphics2D graphics) {
		int screenPixelPerUnit = Constant.screenPixelPerUnit;
		float texturePixelPerUnit;




		Sprite spr;

		Vector2 objPos;
		Vector2 finalPos;

		Vector2 spriteCoord1;
		Vector2 spriteCoord2;

		Vector2 offset;

		float posX = getPosition().getX();
		float posY = getPosition().getY();

		float spriteWidth, spriteHeight;



		if(!obj.isRenderActive()) {
			return;
		}


		spr = obj.display();


		if(spr == null) {
			return;
		}				




		texturePixelPerUnit = spr.getPixelPerUnit();				
		objPos = obj.getPosition();	
		offset = spr.getAnchor();

		//mouvement de la camera



		//	if(img == null)//En cas d'erreur de chargement du sprite, on charge un sprite "error"
		//		img = GameManager.getInstance().getImageByPath(Constant.errorTexture);




		spriteWidth = (int) (screenPixelPerUnit * spr.getWidth()/texturePixelPerUnit);
		spriteHeight = (int) (screenPixelPerUnit * spr.getHeight()/texturePixelPerUnit);

		offset = new Vector2( spriteWidth*offset.getX() ,spriteHeight*offset.getY());


		spriteCoord1 = spr.getBottomLeftCorner();
		spriteCoord2 = spr.getTopRightCorner();

		finalPos = new Vector2(
				(objPos.getX() - posX) * screenPixelPerUnit  - offset.getX(),
				(objPos.getY() - posY)  * screenPixelPerUnit  - offset.getY());


		graphics.drawImage(spr.getBaseImg(),
				(int)finalPos.getX() ,
				(int)finalPos.getY() ,

				(int)finalPos.getX() + (int)spriteWidth,
				(int)finalPos.getY() + (int)spriteHeight,



				(int)spriteCoord1.getX(),
				(int)spriteCoord1.getY(),

				(int)spriteCoord2.getX(),
				(int)spriteCoord2.getY(), null);	


		if (Constant.debug_spriteRect) {
			graphics.setColor(Color.red);
			graphics.setStroke(new BasicStroke(2.0f));

			graphics.drawRect((int)finalPos.getX(), (int)finalPos.getY(), (int)spriteWidth, (int)spriteHeight);
			graphics.setColor(Color.blue);
			graphics.drawOval((int)(finalPos.getX()+offset.getX())-10,  (int)(finalPos.getY()+offset.getY())-10, 20, 20);
		}
	}
	@Override
	public String name() {return "Camera";}


}







