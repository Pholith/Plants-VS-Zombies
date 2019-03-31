package base;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import java.util.function.Consumer;

/**
 * 
 */
public class Terrain extends GameObject {
   
	private final Sprite terrainSprite;
	private final int sizeX;
	private final int sizeY;
	private final Square[][] listOfSquares;
	
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
		return new Vector2(3.456f + (x) * 0.94f, 1.5f+(y)*1.15f);
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
	
	
 
	public void generateButtons(ArrayList<UI_Button> lst, Consumer<Integer[]> function) {
    	int x,y;
  
    	for(y = 0; y < sizeY; y++) {
    	   	for(x = 0; x < sizeX; x++) {
    	   		if(listOfSquares[y][x].getContain() != null)
    	   			continue;
    	   		Integer[] params = new Integer[] {x,y};
    	   		lst.add(new UI_Button( caseToPosition(x,y)  ,1f,Color.orange, Constant.sizeTerrainCase , Constant.sizeTerrainCase , new Vector2(0.5f,0.5f), func -> {function.accept(params ); } ));
    	   		
    	   	}
    	}
	}

    

    
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
    public String name() {return "Terrain";}

    
    
    
    
    @Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {
    
    	if(!Constant.debug_spriteRect)
    		return;
    	
    	
    	//debug de la position des cases    	
    	int sizeCase = Constant.sizeTerrainCase;
    	
    	
    	
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
    	   		
    	   		graphics.drawRect((int)(realPos.getX() - sizeCase/2f) , (int)(realPos.getY() - sizeCase/2f) , sizeCase, sizeCase );    	    	        		
        	}    		
    	} 
	}
	

}