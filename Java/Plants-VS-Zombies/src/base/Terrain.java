package base;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import java.util.function.Consumer;

import enums.EnumTerrain;
import enums.RenderMode;
import main.GameInfo;
import ui.UI_Button;

/**
 * 
 */
public class Terrain extends GameObject {
   
	private final Sprite terrainSprite;
	private static int sizeX;
	private static int sizeY;
	private final Square[][] listOfSquares;
	private final EnumTerrain terrainType;
	

	public static Vector2 caseToPosition(Vector2 gridCase) {
		return caseToPosition((int)gridCase.getX(),(int)gridCase.getY());
	}
	
	public static Vector2 caseToPosition(int x, int y) {
		return new Vector2(3.456f + x * 0.94f, 	1.5f+y* (1.15f - ((float)sizeY-5f)/8f));
	}
	
	public static Vector2 positionToCase(Vector2 pos) {
		return new Vector2(Math.round((pos.getX() - 3.456f) / 0.94f), 		Math.round( (pos.getY() - 1.5f) / (1.15f - ((float)sizeY-5f)/8f)) );
	}
	
	
	
	
	public Terrain(Sprite terrainSprite,EnumTerrain terrainType) {
		super(Vector2.zero(), RenderMode.Both);
		this.terrainSprite = terrainSprite;
		this.terrainType = terrainType;
		sizeX = 9;	
	
		if(terrainType == EnumTerrain.pool) {
			sizeY = 6;
		}else
			sizeY = 5;
	
		
		
		this.listOfSquares = new Square[sizeY][9];
		int i, j;
	
		

		for(i = 0; i < sizeY; i++) {
			for(j = 0; j < 9; j++) {
				listOfSquares[i][j] = new Square(j,i, (terrainType == EnumTerrain.pool && i >= 2 && i < 4)? true : false );
			}	
		}

		
	}
	
	
 
	public void generateButtons(ArrayList<UI_Button> lst, Consumer<Integer[]> function, boolean FilledSlotOnly) {
    	int x,y;
  
    	for(y = 0; y < sizeY; y++) {
    	   	for(x = 0; x < sizeX; x++) {
    	   		if( (listOfSquares[y][x].getContain() == null) == FilledSlotOnly)
    	   			continue;
    	   		Integer[] params = new Integer[] {x,y};
    	   		lst.add(new UI_Button( caseToPosition(x,y)  ,1f,Color.orange, Constant.sizeTerrainCase , Constant.sizeTerrainCase , new Vector2(0.5f,0.5f), func -> {function.accept(params ); } ));
    	   		
    	   	}
    	}
	}
	

	
	
	private boolean isInside(int x, int y) {
		  return !(x < 0 || x >  sizeX || y < 0 || y > sizeY);	    		
	}
	
	public boolean isWater(Vector2 pos) {
		  return isInside((int)pos.getX(), (int)pos.getY()) && listOfSquares[(int)pos.getY()][(int)pos.getX()].isInWater();	    		
	}
	
	   public void removeEntity(int x, int y) {
		  	if(!isInside(x,y) || listOfSquares[y][x].getContain() == null) {
	    		System.err.println("Impossible de suprimer l'entité position("+x+" ,"+y+" ) dans la matrice de jeu.");
	    		return;
	    	}
		  	
		  	listOfSquares[y][x].getContain().destroy();
	   }

    
    public Square addEntity(int x, int y, LivingEntity ent) {
    	
    	if(!isInside(x,y) || listOfSquares[y][x].getContain() != null) {
    		System.err.println("Impossible d'ajouter l'entité "+ent.toString()+" dans la matrice de jeu.");
    		ent.destroy();
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
    public String name() { return "Terrain";}

    
      
    
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
    	   		else {
    	   		
    	   			if(listOfSquares[y][x].isInWater())
    	   			graphics.setColor(Color.yellow);
    	   			else
    	   			graphics.setColor(Color.white);
    	   		
    	   		}
    	   		
    	   		graphics.drawRect((int)(realPos.getX() - sizeCase/2f) , (int)(realPos.getY() - sizeCase/2f) , sizeCase, sizeCase );    	    	        		
        	}    		
    	} 
	}


	public Vector2 getTerrainSize() {
	return new Vector2(sizeX, sizeY);
	}
	

}