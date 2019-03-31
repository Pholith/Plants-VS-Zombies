package base;

import java.awt.Image;

import main.GameManager;

/*
Attention, cette classe sert essentiellement a stocker des valeures.
*/

final public class Sprite {
		
	
	
	//Texture du sprite
	private final Image baseImg;
	
	//Vecteur d'intervalle qui va definir point d'ancrage du sprite [(0.5,0.5) = centr�]
	private final Vector2 anchor;
	
	//Point definissant le rectangle du sprite sur la texture baseImg
	private final Vector2 bottomLeftCorner;
	private final Vector2 topRightCorner;
	
	private final float width;
	private final float height;
	private final int pixelPerUnit;
	
		
	
	
	
	
	
	public Sprite(Image baseImg, Vector2 bottomLeftCorner, Vector2 topRightCorner, Vector2 anchor, int pixelPerUnit) {
		this.baseImg = baseImg;
		this.bottomLeftCorner = bottomLeftCorner;
		this.topRightCorner = topRightCorner;
		this.anchor = anchor; 
		this.pixelPerUnit = pixelPerUnit;
		width = (int)topRightCorner.getX() - (int)bottomLeftCorner.getX();
		height = (int)topRightCorner.getY() - (int)bottomLeftCorner.getY();
	}
	
	
	public Sprite(Image baseImg, Vector2 bottomLeftCorner, Vector2 topRightCorner, int pixelPerUnit) {
	this(baseImg, bottomLeftCorner, topRightCorner, new Vector2(0.5f,0.5f), pixelPerUnit);
	}
	
	public Sprite(Image baseImg, Vector2 anchor, int pixelPerUnit) {
		this(baseImg, Vector2.zero(), new Vector2(baseImg.getWidth(null),baseImg.getHeight(null)), anchor , pixelPerUnit);
	}
	
	public Sprite(Image baseImg, int pixelPerUnit) {
		this(baseImg, new Vector2(0.5f,0.5f), pixelPerUnit);
	}
	
	
		
	
	@Override
	public String toString() {
		return "Sprite [baseImg=" + baseImg + ", anchor=" + anchor + ", bottomLeftCorner=" + bottomLeftCorner
				+ ", topRightCorner=" + topRightCorner + ", width=" + width + ", height=" + height + "]";
	}
	
	
	public Vector2 getAnchor() {
		return anchor;
	}
	
	public Image getBaseImg() {
		return baseImg;
	}
	
	public Vector2 getBottomLeftCorner() {
		return bottomLeftCorner;
	}
	
	public Vector2 getTopRightCorner() {
		return topRightCorner;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	public int getPixelPerUnit() {
		return pixelPerUnit;
	}



}
