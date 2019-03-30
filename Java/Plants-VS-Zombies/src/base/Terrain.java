package base;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

import main.RenderMode;

/**
 * 
 */
public class Terrain extends GameObject {
   
	private final Sprite terrainSprite;
	private final int sizeX;
	private final int sizeY;
	
	/*
	 * 
	 */
	/*
	public Terrain(int numberOfRows, int squaresByRows, String type, ArrayList<Square> listOfSquares) {
		super();
		if (numberOfRows < 2|| squaresByRows < 2) {
			throw new IllegalArgumentException("Impossible de créé une carte plus petite.");
		}
		this.numberOfRows = numberOfRows;
		this.squaresByRows = squaresByRows;
		this.type = Objects.requireNonNull(type);
		this.listOfSquares = listOfSquares;
		if (squaresByRows*numberOfRows != listOfSquares.size()) {
			throw new IllegalArgumentException("Le nombre de cases ne correspond pas avec les dimensions de la carte");
		}
	}*/
	
	public Terrain(Sprite terrainSprite  ) {
		this(terrainSprite, 5);
	}
	
	
	public static Vector2 caseToPosition(Vector2 gridCase) {
		return caseToPosition((int)gridCase.getX(),(int)gridCase.getY());
	}
	public static Vector2 caseToPosition(int x, int y) {
		return new Vector2(3.156f + (x) * 0.935f, 1.35f+(y)*1.1f);
	}
	
	public static Vector2 positionToCase(Vector2 pos) {
		return new Vector2(Math.round((pos.getX() / 0.935f) - 3.156f), Math.round( (pos.getY() /1.1f) - 1.35f));
	}
	
	
	
	
	public Terrain(Sprite terrainSprite, int nbColumns ) {
		super(Vector2.zero(), RenderMode.Both);
		this.terrainSprite = terrainSprite;
		sizeX = 9;
		sizeY = nbColumns;
		
		this.listOfSquares = new Square[nbColumns][9];
		int i, j;
	
		
		for(i = 0; i < nbColumns; i++) {
			for(j = 0; j < 9; j++) {
				listOfSquares[i][j] = new Square(j,i);
			}	
		}
		
	}
	
	
    private final Square[][] listOfSquares;
	/**
     * 
     */
    private int numberOfRows;

    /**
     * 
     */
    private int squaresByRows;

    /**
     * 
     */
    private String type;

    

    
    public Square addEntity(int x, int y, LivingEntity ent) {
    	
    	if(x < 0 || x >  sizeX || y < 0 || y > sizeY) {
    		System.err.println("Impossible d'ajouter l'entité "+ent.toString()+" dans la matrice de jeu.");
    		return null;
    	}
    	
    	listOfSquares[y][x].setContain(ent);  
    	return listOfSquares[y][x];
    }
    
    
    @Override
    public Sprite display() {  
  return terrainSprite;    	
    }
    
    
    
    
    
    @Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
    
    	//debug de la position des cases
    	
    	int sizeCase = 70;//a mettre dans constantes plus tard
    	
    	graphics.setStroke(new BasicStroke(5.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, new float[] {10f}, 0.0f));
    	
    	int x,y;
    	Vector2 realPos;
    	
    	for(y = 0; y < sizeY; y++) {
    	   	for(x = 0; x < sizeX; x++) {
    	   		
    	   		realPos = listOfSquares[y][x].getPos().add(CamPos.multiply(-1)).multiply(Constant.screenPixelPerUnit);
    	   		
    	   		
    	   		
    	   		if(listOfSquares[y][x].getContain() != null)
    	   			graphics.setColor(Color.cyan);
    	   		else
    	   			graphics.setColor(Color.white);
    	   		
    	   		graphics.drawRect((int)(realPos.getX())  , (int)(realPos.getY()) , sizeCase, sizeCase );    	    	        		
        	}    		
    	} 
	}
	

}