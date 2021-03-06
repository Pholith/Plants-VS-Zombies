package base;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import enums.EnumTerrain;
import enums.RenderMode;
import enums.TerrainSearch;
import main.GameInfo;
import main.GameManager;
import plants.Plant;
import props.Gravestone;
import ui.UI_Button;


public class Terrain extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5789226166886876185L;
	private final Sprite terrainSprite;
	private static int sizeX;
	private static int sizeY;
	private final Square[][] listOfSquares;


	public static Vector2 caseToPosition(Vector2 gridCase) {
		return caseToPosition((int)gridCase.getX(),(int)gridCase.getY());
	}

	public static Vector2 caseToPosition(int x, int y) {
		return new Vector2(3.456f + x * 0.94f, 	1.5f+y* (1.15f - (sizeY-5f)/8f));
	}

	public static Vector2 positionToCase(Vector2 pos) {
		return new Vector2(Math.round((pos.getX() - 3.456f) / 0.94f), Math.round( (pos.getY() - 1.5f) / (1.15f - (sizeY-5f)/8f)) );
	}




	public Terrain(Sprite terrainSprite, EnumTerrain terrainType) {
		super(Vector2.zero(), RenderMode.Both, 0);
		this.terrainSprite = terrainSprite;
		sizeX = 9;	

		if (GameManager.getResources().getGameInfo().isPool()) {
			sizeY = 6;
		} else {
			sizeY = 5;
		}



		this.listOfSquares = new Square[sizeY][9];
		int i, j;


		GameInfo inf = GameManager.getResources().getGameInfo();

		for(i = 0; i < sizeY; i++) {
			for(j = 0; j < 9; j++) {
				listOfSquares[i][j] = new Square(j,i, inf.isPool() && i >= 2 && i < 4? true : false,  inf.isFog() && j >= 4 );
			}	
		}



		if(GameManager.getInstance().IsPlayingASave()) {
			return;
		}



		if(terrainType == EnumTerrain.night_lawn) {

			int graveX, graveY;


			for(i = 0; i < 6 + Math.random()*2; i++) {
				graveX = (int) (6 + Math.random()* (sizeX - 6));
				graveY = (int) (Math.random()* sizeY);



				if(listOfSquares[graveY][graveX].canBePlacedNewGroundPlant()) {

					addEntity(graveX, graveY, new Gravestone(new Vector2(graveX, graveY), listOfSquares[graveY][graveX]));

				} 
			}
		}

	}


	public GameObject getTerrainContent(int x,int y, Class classSearch) {
		if(isInside(x, y) && listOfSquares[y][x].getContain() != null) {
			var lst = listOfSquares[y][x].getContain();

			for(var obj : lst) {
				if(obj.getClass().equals(classSearch) ) {
					return obj;
				}
			}

		}		
		return null;
	}
	public GameObject getTerrainShroom(int x,int y) {
		if(isInside(x, y) && listOfSquares[y][x].getContain() != null) {
			var lst = listOfSquares[y][x].getContain();

			for(var obj : lst) {
				if (obj.isPlant() && ((Plant) obj).isShroom()) {
					return obj;
				}
			}

		}		
		return null;
	}


	boolean checkSquare(Square square, TerrainSearch searchMode) {
		ArrayList<LivingEntity> ent = square.getContain();

		switch (searchMode) {

		case emptySurface:   	   					
			return square.canBePlacedNewGroundPlant();			

		case emptyGround:   	   					
			return ent.size() == 0 && !square.isInWater();			

		case emptyWater:   	   					
			return ent.size() == 0 && square.isInWater();			

		case notEmptyPlant:  
			return ent.size() > 0;

		case possibleTerrain: // pour la citrouille
			return (square.canBePlacedNewGroundPlant() || ent.size() > 0) && !square.hasPumpkin() && !square.hasGravestone();


		case graveStone:
			return square.hasGravestone();


		case directRoof:
			if (!GameManager.getResources().getGameInfo().isRoof()) {
				return false;
			}
			return ent.size() < 1;

		case shroom:
			return square.hasShroom();			

		default:
			break;					
		}

		return false;	
	}


	public void doThisIfGraveStone(Function<Vector2, Boolean> func) {
		for (Square[] squares : listOfSquares) {
			for (Square square : squares) {
				if (square.hasGravestone()) {
					func.apply(Terrain.positionToCase(square.getPos()));
				}
			}
		}
	}

	public void generateButtons(ArrayList<UI_Button> lst, Consumer<Integer[]> function, TerrainSearch searchMode) {
		int x,y;

		for(y = 0; y < sizeY; y++) {
			for(x = 0; x < sizeX; x++) {

				Integer[] params = new Integer[] {x,y};

				if(checkSquare(listOfSquares[y][x], searchMode)) { 	   		




					lst.add(new UI_Button( caseToPosition(x,y)  ,1f,Color.orange, Constant.sizeTerrainCase , Constant.sizeTerrainCase , new Vector2(0.5f,0.5f), func -> {function.accept(params ); } ));
				}
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
			System.err.println("Impossible de suprimer l'entit้ position("+x+" ,"+y+" ) dans la matrice de jeu.");
			return;
		}


		LivingEntity obj = listOfSquares[y][x].getLast();

		listOfSquares[y][x].destroyLast();

		if(obj != null && obj.getLightRange() > 0){
			updateFog();
		}


	}


	public Square addEntity(int x, int y, LivingEntity ent) {

		if(!isInside(x,y)) {
			System.err.println("Impossible d'ajouter l'entit้ "+ent.toString()+" dans la matrice de jeu.");
			ent.destroy();
			return null;
		}

		listOfSquares[y][x].addContain(ent);  


		if(ent.getLightRange() > 0){
			updateFog();
		}



		return listOfSquares[y][x];
	}


	private void updateFog() {
		int i,j,h;
		ArrayList<LivingEntity> caseContain;
		ArrayList<LivingEntity> lightList = new ArrayList<LivingEntity>();

		Vector2 lampPosition;
		float lampRange;




		for(i = 0; i < sizeX; i++) {    		
			for(j = 0; j < sizeY; j++) {
				listOfSquares[j][i].setActiveFog(true, true);
				caseContain = listOfSquares[j][i].getContain();

				if(caseContain == null) {
					continue;
				}

				for(h = 0; h< caseContain.size(); h++) {
					if(caseContain.get(h).getLightRange() > 0) {
						lightList.add(caseContain.get(h));
					}
				}  	  		
			}
		}



		for( i = 0; i < sizeX; i++) {    		
			for( j = 0; j < sizeY; j++) {

				for(h = 0; h< lightList.size(); h++) {
					lampPosition = lightList.get(h).getPosition();
					lampRange = lightList.get(h).getLightRange();

					listOfSquares[j][i].setActiveFog(Vector2.distance(lampPosition, Terrain.caseToPosition(i, j)) > lampRange, false);
				}

			}
		}


	}





	@Override
	public Sprite display() {  
		return terrainSprite;    	
	}
	@Override
	public String name() { return "Terrain";}




	@Override
	public void selfDisplay(Vector2 CamPos, Graphics2D graphics) {

		if(!Constant.debug_spriteRect) {
			return;
		}


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



				if(listOfSquares[y][x].getContain() != null) {
					graphics.setColor(Color.cyan);
				} else {

					if(listOfSquares[y][x].isInWater()) {
						graphics.setColor(Color.yellow);
					} else {
						graphics.setColor(Color.white);
					}

				}

				graphics.drawRect((int)(realPos.getX() - sizeCase/2f) , (int)(realPos.getY() - sizeCase/2f) , sizeCase, sizeCase );    	    	        		
			}    		
		} 
	}

	public Vector2 getTerrainSize() {
		return new Vector2(sizeX, sizeY);
	}


}