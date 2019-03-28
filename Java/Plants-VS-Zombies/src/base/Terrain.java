package base;


import java.util.*;

/**
 * 
 */
public class Terrain extends GameObject {
   
	private final Sprite terrainSprite;

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
		this(terrainSprite, 6);
	}
	
	
	public Terrain(Sprite terrainSprite, int nbColumns ) {
		super(Vector2.zero());
		this.terrainSprite = terrainSprite;
		this.listOfSquares = new Square[nbColumns][9];
		int i, j;
		
		for(i = 0; i < nbColumns; i++) {
			for(j = 0; j < 9; j++) {
				listOfSquares[i][j] = new Square();
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

    

    
    @Override
    public Sprite display() {  
  return terrainSprite;
    	
    }
    

}