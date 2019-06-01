package main;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;

import base.Camera;
import base.Constant;
import base.GameObject;
import base.Terrain;
import base.Vector2;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import ui.UI_AnimatedSprite;
import ui.UI_Label;
import zombies.Zombie;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;


	///Cette classe est un singleton. Le jeu démarre après sa creation , et l'appel de la fonction StartManger();
	public final class GameManager implements Serializable {
		
	    /**
		 * 
		 */
		private static final long serialVersionUID = 5825660919828564296L;

		private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() { return SINGLE_INSTANCE; }
	    
	    private GameManager() //Ne pas initialiser de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
	    	objectsInQueue = new ArrayList<GameObject>(); 
	    	objectsToRemoveQueue = new ArrayList<GameObject>(); 
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
	    
	    private  ScreenInfo screenInfo;
	    	/*
	    public ScreenInfo getScreenInfo() {
			return screenInfo;
		}*/
	    
	    private boolean inDebugMode;
	    
	    private float resolutionX;
	    private float resolutionY;    
	    public float getResolutionX() {
			return resolutionX;
		}
	    
	    
	    private final Clock clock;	//Horloge temps reel 
	    private int currentFps;		//Compteur de fps
	    private int savedFps;		//Sauvegarde du nombre de fps de la derniere seconde  
	    private long lastSecTime;	//Sauvegarde du temps de dï¿½marage pour du comptage des fps
	    private long lastFrameTime;	//Sauvegarde du temps de la derniere frame
	    
	    private float deltaTime;	//Temps écoulllé entre la frame actuelle et celle d'avant
	    private float timeScale;	//Vitesse génerale du jeu
	    
	    private int gameWait;
	    private int maxFps;
	  
	    private int utilityObjectCount= 0;
	    
	    // Objets en jeu ou en attente
	    private final ArrayList<GameObject> sceneContent;
	    private final ArrayList<GameObject> objectsInQueue;  
	    private final ArrayList<GameObject> objectsToRemoveQueue;  
	    //private final ArrayList<<Vector2,Vector2>> linesToDraw;  
	    private Camera mainCamera;
	    private LevelManager levelManager;
	    private UI_Label fpsBox;
	    
	    private Point2D.Float clickLocation;    
	    
	    
	    private boolean gameEnded;
	    private boolean gameStarted;
	    
	    PauseMenu pauseMenu;
	    ApplicationContext context;
	    
	    void setGameStarted(boolean val) {
	    	gameStarted = val;
	    	if(val) {
	    	levelManager = new LevelManager();
	    	
	    	
			Consumer<Integer> resume =  (x) -> { pauseMenu.hide(); timeScale = 1f; };
			Consumer<Integer> save =  (x) -> { SaveManager.save(sceneContent);};
			Consumer<Integer> load =  (x) -> { };
			Consumer<Void> exit =  (x) -> { context.exit(0); };

	    	pauseMenu = new PauseMenu(resolutionX, resolutionY, resume, save, load, exit) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 9127473762164938994L;} ;

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
	    	
	    	///////////////////////////////////////// Objets utilitaires du gameManager
	    	
	    	utilityObjects();
	    	
	       	////////////////////////////////////////// Initialisations génerale des resources 
	    	
	    	RESOURCES.loadResources();
	    	maxFps = RESOURCES.getGameConfig().getConfigInt("maxFPS");

	    	//////////////////////////////////////// Démarage de la boucle principale
	    	 
			Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
				screenInfo = context.getScreenInfo();			    
				resolutionX = screenInfo.getWidth();
				resolutionY = screenInfo.getHeight();
				 
				if (this.context == null) {
					this.context = context;
				}

			      
			    System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");

			    MainMenu.start_menu();
			      
			    while (true) {
			    	  
			    if (gameStarted && !gameEnded)
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
	    	utilityObjectCount = objectsInQueue.size();
	    }
	    	    
	    private void updateGameObjects() {			
	    	  	    		    	
	    	  for (GameObject gameObject : objectsInQueue) {
	    		  sceneContent.add(gameObject);
	    	  }
	    	  Collections.sort(sceneContent); // On trie la liste à chaque fois qu'on y rajoute des objets

	    	  for (GameObject gameObject : objectsToRemoveQueue) {
	    		  sceneContent.remove(gameObject);
		      }
	    	  
	    	  objectsInQueue.clear();
	    	  objectsToRemoveQueue.clear();
	    	  
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
	    
	    	gameWait = (int)((((float)savedFps/(float)maxFps)-1f)*(1000f/savedFps));
	    	
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
		  	
	        if (key == KeyboardKey.P) {
	        	if (pauseMenu != null && !pauseMenu.isVisible()) {
					timeScale = 0;
					pauseMenu.show();
					System.out.println("Game Paused");
	        	}
			}
	        
	        
	        if(gameEnded)
	        	return;
	        	
	        if (key == KeyboardKey.LEFT)
	        	mainCamera.pushLeft();
	        	
	        if (key == KeyboardKey.RIGHT)
	        	mainCamera.pushRight();
	        
	
	      
	        if (key == KeyboardKey.D && action == Action.KEY_PRESSED ) {
	        	
	        	if(!inDebugMode) {
	        	System.out.println("Debug mode activated");
	        	timeScale = 5f;	            
	        	} else {	        		
	        		System.out.println("Debug mode stopped");
		        	timeScale = 1f;	            
	        	}
		        inDebugMode = !inDebugMode;		        
	        }
	        if (key == KeyboardKey.O) {
	        	System.out.println("Speed changed to slow");
	        	timeScale = 0.3f;
	        }
	        if (key == KeyboardKey.I) {
	        	System.out.println("Speed changed to normal");
	        	timeScale = 1f;
	        }
	        if (key == KeyboardKey.U) {
	        	System.out.println("Speed changed to fast");
	        	timeScale = 5f;
	        }
	        
	        if (key == KeyboardKey.Y) {
	        	System.out.println("Speed changed to super-fast");
	        	timeScale = 20f;
	       }
	       
	        
	    
	    	
			if (action != Action.POINTER_DOWN) {
				clickLocation = null;
				return;
			}
			RESOURCES.setSelectedUi(null);
			clickLocation = event.getLocation();
		  }
	  
	    
	 
	    
	    public void addGameObjectToScene(GameObject obj) {	
	    	if(!sceneContent.contains(obj) && !objectsInQueue.contains(obj))
	    		objectsInQueue.add(obj);	    	

	    	obj.start();
	    }
	    /*
	    //inverse les 2 dernier objet de la liste des GameObjects a ajouter (utile si l'on veut determiner l'ordre d'affichage de certain objets).
	    public void invertLastGameObjectQueue() {	
	    	//Collections.reverse(ojbectsInQueue);
	    	
	    	if(ojbectsInQueue.size() >= 2) {
	    		GameObject obj = ojbectsInQueue.get(ojbectsInQueue.size()-2);
	    		ojbectsInQueue.set(ojbectsInQueue.size()-2,ojbectsInQueue.get(ojbectsInQueue.size()-1));
	    		ojbectsInQueue.set(ojbectsInQueue.size()-1, obj);
	    	}
	    }*/
	    
	    public void removeGameObjectFromScene(GameObject obj) {
	    	if(obj != null && !objectsToRemoveQueue.contains(obj))
	    		objectsToRemoveQueue.add(obj);
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
		
		public HashSet<Zombie> getZombieArround(GameObject o) {
			return getZombieArround(o, 2f);
		}
		public HashSet<Zombie> getZombieArround(GameObject o, double distance) {
			HashSet<Zombie> listOfZombies = new HashSet<>();
			var hashset = getGameObjectArround(o, distance, new Function<GameObject, Boolean>() {
				@Override
				public Boolean apply(GameObject t) {
					if (t.isZombie()) {
						return Boolean.valueOf(true);
					}
					return Boolean.valueOf(false);
				}
			});
			for (GameObject gameObject : hashset) {
				listOfZombies.add( (Zombie) gameObject);
			}
			return listOfZombies;
		}
		/* Retourne les objets autour de l'objet donnée en paramètre suivant certains critères
		* o l'objet de référence
		* distance la distance autour de cet objet
		* lambda la fonction de sélection de l'objet
		*/
		public HashSet<GameObject> getGameObjectArround(GameObject o, double distance, Function<GameObject, Boolean> lambda) {
			HashSet<GameObject> listOfGameObject = new HashSet<>();
			
			for (GameObject gameObject : sceneContent) {
								
				if (lambda.apply(gameObject) &&
						gameObject.getPosition().getX()    	< o.getPosition().getX() + distance &&
						gameObject.getPosition().getX()	    > o.getPosition().getX() - distance &&
						gameObject.getPosition().getY() 	< o.getPosition().getY() + distance &&
						gameObject.getPosition().getY()  	> o.getPosition().getY() - distance ){
					listOfGameObject.add(gameObject);
				}				
			}
			return listOfGameObject;
		}
		
		public HashSet<Zombie> getAllZombies() {
			HashSet<Zombie> listZombies = new HashSet<Zombie>();
			for (GameObject gameObject : sceneContent) {
				if (gameObject.isZombie()) {
					listZombies.add((Zombie) gameObject);
				}
			}
			return listZombies;
		}
		public boolean isZombieOnRow(int row) {
			//getResources().getTerrain().getTerrainSize().getY();
			
			for (GameObject gameObject : sceneContent) {
				if (gameObject.isZombie() && Terrain.positionToCase(gameObject.getPosition()).isOnSameRow(new Vector2(0, row))) {
					return true;
				}
			}
			return false;
		}
		public GameObject getFirstZombie(GameObject o) {
			GameObject firstEnemy = null;
			for (GameObject gameObject : sceneContent) {
								
				if(gameObject.isZombie() && gameObject.isTargetable() && gameObject.isOnSameRow(o) && gameObject.getPosition().getX() > o.getPosition().getX()) {
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
					
				boolean hypnotisedZombie = false;
				if (gameObject instanceof Zombie) {
					if (((Zombie) gameObject).isHypnotised()) {
						hypnotisedZombie = true;
					}
				}
				if( ( gameObject.isPlant()|| hypnotisedZombie) && !gameObject.isProjectile() && gameObject.isOnSameRow(o) && gameObject.getPosition().getX() < o.getPosition().getX()) {
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

			
			System.out.println("Partie terminé");
			
			clearScene();
			
			
			if(win) 
			new UI_AnimatedSprite(new Vector2((resolutionX/Constant.screenPixelPerUnit)/2f, 0), "particles/end_anim_victory.png", 1.2f, false);		    	
						
			new UI_Label(new Vector2(1f, 1f), (win)?"Partie gagnée !":"Partie perdue !", (win)?Color.green :Color.red, 5f);
			
			if(!win) {
				new UI_AnimatedSprite(new Vector2((resolutionX/Constant.screenPixelPerUnit)/2f, 0), "particles/end_anim_defeat.png", 1.2f, true);		    	
				
			}
			
			gameEnded = true;
			inDebugMode = false;
			
		}
		
		
		
		public boolean isDebugMode() {
			return inDebugMode;
		}
		
	}
	    
	    
	    
	    


	







	 

	

