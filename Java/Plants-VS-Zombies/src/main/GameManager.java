package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import base.Camera;
import base.Constant;
import base.GameObject;
import base.LevelManager;
import base.LivingEntity;
import base.Terrain;
import base.Vector2;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import projectiles.Peash;
import projectiles.Projectile;
import ui.UI_Label;
import zombies.SimpleZombie;
import zombies.Zombie;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;


	///Cette classe est un singleton. Le jeu dï¿½mare ï¿½ sa creation , ï¿½ l'appel de la fonction StartManger();
	public final class GameManager {
		
	    private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() { return SINGLE_INSTANCE;  }
	    private GameManager() //Ne pas initialiser de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
	    	ojbectsInQueue = new ArrayList<GameObject>(); 
	    	ojbectsToRemoveQueue = new ArrayList<GameObject>(); 
	    	deltaTime = 1f;
	    	timeScale = 1f;
	    	savedFps = 60;
	    	gameWait = 300;
	    	
	    	clock = Clock.systemDefaultZone(); 		
	    	lastSecTime = clock.millis();
	    	lastFrameTime = lastSecTime;
	    }
	    private static final Resources RESOURCES = new Resources();
	    public static Resources getResources() { return RESOURCES;  }

	
	    private boolean inDebugMode;
	    
	    private float resolutionX;
	    private float resolutionY;    
	
	    
	    private final Clock clock;	//Horloge temps reel 
	    private int currentFps;		//Compteur de fps
	    private int savedFps;		//Sauvegarde du nombre de fps de la derniere seconde  
	    private long lastSecTime;	//Sauvegarde du temps de dï¿½marage pour du comptage des fps
	    private long lastFrameTime;	//Sauvegarde du temps de la derniere frame
	    
	    private float deltaTime;	//Temps écoulllé entre la frame actuelle et celle d'avant
	    private float timeScale;	//Vitesse génerale du jeu
	    
	    private int gameWait;
	    private final int maxFps = 90;
	  
	    private int utilityObjectCount= 0;
	    
	    private final ArrayList<GameObject> sceneContent;
	    private final ArrayList<GameObject> ojbectsInQueue;  
	    private final ArrayList<GameObject> ojbectsToRemoveQueue;  
	    //private final ArrayList<<Vector2,Vector2>> linesToDraw;  
	    private Camera mainCamera;
	    private LevelManager levelManager;
	    private UI_Label fpsBox;
	    
	    private Point2D.Float clickLocation;    
	    
	    
	    private boolean endGame;
	    private boolean gameStarted;
	    
	    
	    void setGameStarted(boolean val) {
	    	gameStarted = val;
	    	if(val) {
	    	levelManager = new LevelManager();
	    	clearScene();
	    	}
	    }
	    
	    public boolean getGameStarted() {

	    	return gameStarted;
	    }
	    
	    
	    public Point2D.Float getClickLocation() {
			return clickLocation;
		}
	    
	    
	    void startManager() throws IOException {
	    	
	    	/////////////////////////////////////////Objets utilitaires du gameManager
	    	
	    	utilityObjects();
	    	
	       	//////////////////////////////////////////Initialisations génerale des resources 
	    	
	    	RESOURCES.loadResources();
	    		    	
	    	////////////////////////////////////////Démarage de la boucle principale
	    	 
			 Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
			      ScreenInfo screenInfo = context.getScreenInfo();			    
			      resolutionX = screenInfo.getWidth();
			      resolutionY = screenInfo.getHeight();
			     
			      
			      System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");
			      
			      
			      while (true) {
			    	  
			    	  if(gameStarted && !endGame)
			    	  levelManager.levelEvent();
				      updateGameObjects();
				      RESOURCES.updateResources();
				      inputCheck(context);
				      render(context);
				      fpsCount();
			      }
			 });
	    }   
	    
	    
	    private void utilityObjects() {	    	
	    	mainCamera = new Camera();	    
	    	fpsBox = new UI_Label(new Vector2(0.05f,7f), "FPS..", Color.black, 3f );
	    	utilityObjectCount = ojbectsInQueue.size();
	    }
	    	    
	    private void updateGameObjects() {			
	    	  	    		    	
	    	  for (GameObject gameObject : ojbectsInQueue) {
	    		  sceneContent.add(gameObject);
		    	  }
	    	  for (GameObject gameObject : ojbectsToRemoveQueue) {
	    		  sceneContent.remove(gameObject);
		    	  }
	    	  
	    	  ojbectsInQueue.clear();
	    	  ojbectsToRemoveQueue.clear();
	    	  
	    	//update tout les objets
	    	  for (GameObject gameObject : sceneContent) {
				gameObject.update();
	    	  }	    	
	    }
	    
	    private void render(ApplicationContext context) {			
	    	  
		      context.renderFrame(graphics -> {  
		    	  //clear les graphics
		    	  graphics.setColor(Color.WHITE);
		    	  graphics.fill(new  Rectangle2D.Float(0, 0, resolutionX, resolutionY));		
		    	
		    	  //affichage des sprites
		    	  mainCamera.render(sceneContent, graphics);		    	  
		    	  fpsBox.setText(String.valueOf(savedFps) + " FPS");

		      });
	    }
	    
	    

	    
	    private void inputCheck(ApplicationContext context) {
	    
	    	gameWait = (int)((((float)savedFps/(float)maxFps)-1f)*(1000f/(float)savedFps));
	    	
	    	if(gameWait < 500/maxFps)
	    		gameWait = 500/maxFps;

	    	
		  Event event = context.pollOrWaitEvent(gameWait); 

	        if (event == null) {  // no event
	          return;
	        }
	        
	
	        try {
				Thread.sleep(gameWait);
			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}  
	        
		  Action action = event.getAction();

		  KeyboardKey key =  event.getKey();
		  
		  
	        if (key == KeyboardKey.UNDEFINED) {//action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
	          System.out.println("abort abort !");
	          context.exit(0);
	          return;
	        }
		  	
	        
	        
	        
	        if(endGame)
	        	return;
	        	
	        if (key == KeyboardKey.LEFT)
	        	mainCamera.translation(-0.2f, 0);
	        	
	        if (key == KeyboardKey.RIGHT)
	        	mainCamera.translation(0.2f, 0);
	        
	
	      
	        if (key == KeyboardKey.D && action == Action.KEY_PRESSED ) {
	        	
	        	if(!inDebugMode) {
	        	System.out.println("Debug mode activated");
	        	timeScale = 5f;	            
	        	}else {	        		
	        		System.out.println("Debug mode stopped");
		        	timeScale = 1f;	            
	        	}
		        inDebugMode = !inDebugMode;		        
	        }
	        if (key == KeyboardKey.P) {
	        	System.out.println("Speed changed to slow");
	        	timeScale = 0.3f;
	        }
	        if (key == KeyboardKey.O) {
	        	System.out.println("Speed changed to normal");
	        	timeScale = 1f;
	        }
	        if (key == KeyboardKey.I) {
	        	System.out.println("Speed changed to fast");
	        	timeScale = 5f;
	        	}
	       
	        
	    
	    	
			if (action != Action.POINTER_DOWN) {
				clickLocation = null;
				return;
			}
			RESOURCES.setSelectedUi(null);
			clickLocation = event.getLocation();
		  }
	  
	    
	 
	    
	    public void addGameObjectToScene(GameObject obj) {	
	    	if(!sceneContent.contains(obj) && !ojbectsInQueue.contains(obj))
	    		ojbectsInQueue.add(obj);	    	
	    		obj.start();
	    }
	    
	    //inverse les 2 dernier objet de la liste des GameObjects a ajouter (utile si l'on veut determiner l'ordre d'affichage de certain objets).
	    public void invertLastGameObjectQueue() {	
	    	//Collections.reverse(ojbectsInQueue);
	    	
	    	if(ojbectsInQueue.size() >= 2) {
	    		GameObject obj = ojbectsInQueue.get(ojbectsInQueue.size()-2);
	    		ojbectsInQueue.set(ojbectsInQueue.size()-2,ojbectsInQueue.get(ojbectsInQueue.size()-1));
	    		ojbectsInQueue.set(ojbectsInQueue.size()-1, obj);
	    	}
	    }
	    
	    public void removeGameObjectFromScene(GameObject obj) {
	    	if(obj != null && !ojbectsToRemoveQueue.contains(obj))
	    		ojbectsToRemoveQueue.add(obj);
	    }
	    
	    

	    private void fpsCount() {
	    	
	    	currentFps++;
	    	deltaTime = (clock.millis() - lastFrameTime)/1000f;
	    	lastFrameTime = clock.millis();
	    	if(lastFrameTime-lastSecTime >= 1000) {
	    		
	    		savedFps = currentFps;
	    		currentFps = 0;
	    		lastSecTime = clock.millis();
	    		
	    		
	    	}
	    }
	  
	    
		public long getClockMillis() {
			return clock.millis();
		}
		public float getDeltatime() {
			return deltaTime*timeScale;
		}
	    public float getTimeScale() {
			return timeScale;
		}
	    
		/* 
		 * Renvoie le premier objet ennemi ï¿½ l'objet
		 * Renvoie null sinon
		 */ 
		
		public ArrayList<Zombie> getZombieArround(GameObject o) {
			return getZombieArround(o, 2f);
		}
		public ArrayList<Zombie> getZombieArround(GameObject o, double distance) {
			ArrayList<Zombie> listOfZombies = new ArrayList<>();
			
			for (GameObject gameObject : sceneContent) {
								
				if (gameObject.isEnemy(o) &&
						gameObject.getPosition().getX()    	< o.getPosition().getX() + distance &&
						gameObject.getPosition().getX()	    > o.getPosition().getX() - distance &&
						gameObject.getPosition().getY() 	< o.getPosition().getY() + distance &&
						gameObject.getPosition().getY()  	> o.getPosition().getY() - distance ){
					listOfZombies.add((Zombie) gameObject);
				}				
			}
			
			return listOfZombies;
		}
		
		public GameObject getFirstZombie(GameObject o) {
			GameObject firstEnemy = null;
			for (GameObject gameObject : sceneContent) {
								
				if(gameObject.isEnemy(o) && gameObject.isOnSameRow(o) && gameObject.getPosition().getX() > o.getPosition().getX()) {
					if (firstEnemy == null) firstEnemy = gameObject;

					// si firstEnemy n'est pas null on compare les distances
					else  {
						// si l'objet de la boucle est plus proche de o, on le prend
						if (o.getPosition().getX() - gameObject.getPosition().getX() >
								 o.getPosition().getX() - firstEnemy.getPosition().getX())
							firstEnemy = gameObject;
					}
				}
			}
			return firstEnemy;
		}
		public GameObject getFirstPlant(GameObject o) {
			
			GameObject firstEnemy = null;
			for (GameObject gameObject : sceneContent) {
								
				if(gameObject.isEnemy(o) && !gameObject.isProjectile() &&gameObject.isOnSameRow(o) && gameObject.getPosition().getX() < o.getPosition().getX()) {
					if (firstEnemy == null) firstEnemy = gameObject;

					// si firstEnemy n'est pas null on compare les distances
					else  {
						// si l'objet de la boucle est plus proche de o, on le prend
						if (o.getPosition().getX() - gameObject.getPosition().getX() < 
								 o.getPosition().getX() - firstEnemy.getPosition().getX())
							firstEnemy = gameObject;
					}
				}
			}
			return firstEnemy;
		}
		
		public void clearScene() {
			
			//supresssion de tout les objets de la scene (on épargne les objets utilitaires)	
			
			for(int i = utilityObjectCount; i < sceneContent.size(); i++) {
				sceneContent.get(i).destroy();				
			}
			
		}
		
		public void endGame(boolean win) {

			clearScene();
			
			new UI_Label(new Vector2(1f, 1f), (win)?"Partie gagnée !":"Partie perdue !", (win)?Color.green :Color.red, 5f);
			
			endGame = true;
			inDebugMode = false;
			
		}
		
		
		
		public boolean isDebugMode() {
			return inDebugMode;
		}
		
	}
	    
	    
	    
	    


	







	 

	

