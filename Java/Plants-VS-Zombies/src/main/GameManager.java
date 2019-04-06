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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import base.Camera;
import base.GameObject;
import base.LevelManager;
import base.LivingEntity;
import base.Terrain;
import base.UI_Label;
import base.Vector2;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import projectiles.Peash;
import projectiles.Projectile;
import zombies.SimpleZombie;
import zombies.Zombie;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;


	///Cette classe est un singleton. Le jeu démare à sa creation , à l'appel de la fonction StartManger();
	public final class GameManager {
		
	    private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() { return SINGLE_INSTANCE;  }
	    private GameManager() //Ne pas initialiser de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
	    	ojbectsInQueue = new ArrayList<GameObject>(); 
	    	ojbectsToRemoveQueue = new ArrayList<GameObject>(); 
	    	timeMultiplier = 1f;
	    	savedFps = 60;
	    	clock = Clock.systemDefaultZone(); 		    
	    }
	    private static final Resources RESOURCES = new Resources();
	    public static Resources getResources() { return RESOURCES;  }

	
	    
	    private float resolutionX;
	    private float resolutionY;    
	    
	    
	    private final Clock clock; 
	    private int currentFps;
	    private int savedFps;	    
	    private long lastSecTime;
	    private float timeMultiplier;
	    
	  
	    private final ArrayList<GameObject> sceneContent;
	    private final ArrayList<GameObject> ojbectsInQueue;  
	    private final ArrayList<GameObject> ojbectsToRemoveQueue;  
	    //private final ArrayList<<Vector2,Vector2>> linesToDraw;  
	    private Camera mainCamera;
	    private LevelManager levelManager;
	    private UI_Label fpsBox;
	    
	    private Point2D.Float clickLocation;    
	    
	    
	    public Point2D.Float getClickLocation() {
			return clickLocation;
		}
	    
	    
	    void startManager() throws IOException {
	    	
	       	//////////////////////////////////////////Initialisations génerale des resources 
	    	
	    	RESOURCES.startGame();
	    	levelManager = new LevelManager();
	     	mainCamera = new Camera();	    
	    	fpsBox = new UI_Label(new Vector2(0.05f,0.1f), "FPS..", Color.black, 3f );
	    	
	    	
	    	////////////////////////////////////////Démarage de la boucle principale
	    	 
			 Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
			      ScreenInfo screenInfo = context.getScreenInfo();			    
			      resolutionX = screenInfo.getWidth();
			      resolutionY = screenInfo.getHeight();
			     
			      
			      System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");
			      
			      
			      while (true) {
			    	  levelManager.levelEvent();
				      updateGameObjects();
				      RESOURCES.updateResources();
				      inputCheck(context);
				      render(context);
				      fpsCount();
			      }
			 });
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
	    
	    
	    private int gameWait = 30;
	    private int maxFps = 90;
	    
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        
		  Action action = event.getAction();

		  KeyboardKey key =  event.getKey();
		  
		  
	        if (key == KeyboardKey.UNDEFINED) {//action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
	          System.out.println("abort abort !");
	          context.exit(0);
	          return;
	        }
		  		  
	        
	        if (key == KeyboardKey.LEFT)
	        	mainCamera.translation(-0.2f, 0);
	        	
	        if (key == KeyboardKey.RIGHT)
	        	mainCamera.translation(0.2f, 0);
	        
	        if (key == KeyboardKey.P)
	        	System.out.println("Speed changed to slow");
	        gameWait = 50;
	        if (key == KeyboardKey.O)
	        	System.out.println("Speed changed to normal");
	        gameWait = 20;
	        if (key == KeyboardKey.I)
	        	System.out.println("Speed changed to fast");
	        gameWait = 5;
		 
        		
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
	    
	    public void removeGameObjectFromScene(GameObject obj) {
	    	if(obj != null && !ojbectsToRemoveQueue.contains(obj))
	    		ojbectsToRemoveQueue.add(obj);
	    }
	    
	    

	    private void fpsCount() {
	    	
	    	currentFps++;
	    	
	    	if(clock.millis() - lastSecTime >= 1000) {
	    		
	    		savedFps = currentFps;
	    		currentFps = 0;
	    		lastSecTime = clock.millis();
	    		timeMultiplier = 60f/(float)savedFps;
	    		
	    	}
	    }
	  
	    
		public long getClockMillis() {
			return clock.millis();
		}
		public float getTimeMultiplier() {
			return timeMultiplier;
		}
	    
	    
		/* TODO
		 * Renvoie le premier objet ennemi à l'objet
		 * Renvoie null sinon
		 */ 
		
		public ArrayList<Zombie> getZombieArround(GameObject o) {
			ArrayList<Zombie> listOfZombies = new ArrayList<>();
			
			for (GameObject gameObject : sceneContent) {
				
				double distance = 2f;
				
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
	}
	    
	    
	    
	    


	







	 

	

