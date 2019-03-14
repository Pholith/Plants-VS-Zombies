package main;

import java.awt.Color;
import java.awt.Image;
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
import base.LivingEntity;
import base.UI_Label;
import base.Vector2;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;


	///Cette classe est un singleton. Le jeu d�mare � sa creation , � l'appel de la fonction StartManger();
	public class GameManager {
		
	    private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() { return SINGLE_INSTANCE;  }
	    private GameManager() //Ne pas initialiser de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
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
	    
	   	    
	    private final ArrayList<GameObject> sceneContent;
	    private Camera mainCamera;
	  
	 
	    private UI_Label fpsBox;
	    
	    
	    
	    
	    
	    void startManager() throws IOException {
	    	
	       	//////////////////////////////////////////Initialisations g�nerale des resources 
	    	
	   

	    	RESOURCES.startGame();
	    	
	     	mainCamera = new Camera();	    
	    	fpsBox = new UI_Label(new Vector2(0.05f,0.1f), "FPS..", Color.black, 3f );
	    	
	    	
	    	////////////////////////////////////////D�marage de la boucle principale
	    	 
			 Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
			      ScreenInfo screenInfo = context.getScreenInfo();			    
			      resolutionX = screenInfo.getWidth();
			      resolutionY = screenInfo.getHeight();
			     
			      
			      System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");
			      
			      
			      while (true) {		    	  
			    	  
			      inputCheck(context);
			      render(context);
			      fpsCount();
			      
			      }
			 });
	    }   
	    
	    

	    
	    private void render(ApplicationContext context) {			
	    	  
		      context.renderFrame(graphics -> {  
		    	  graphics.setColor(Color.WHITE);
		    	  graphics.fill(new  Rectangle2D.Float(0, 0, resolutionX, resolutionY));		
		    	  mainCamera.render(sceneContent,graphics);		
		    	  
		    	  fpsBox.setText(String.valueOf(savedFps) + " FPS");

		      });
	    }
	    
	    
	    
	    
	    private void inputCheck(ApplicationContext context) {
		  	
		  Event event = context.pollOrWaitEvent(10);
	        if (event == null) {  // no event
	          return;
	        }
		  Action action = event.getAction();
	        if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
	          System.out.println("abort abort !");
	          context.exit(0);
	          return;
	        }
		  }
	  
	    
	    
	    public void addGameObjectToScene(GameObject obj) {	
	    	if(!sceneContent.contains(obj))
	    		sceneContent.add(obj);
	    }
	    

	    private void fpsCount() {
	    	
	    	currentFps++;
	    	
	    	if(clock.millis() - lastSecTime >= 500) {
	    		savedFps = currentFps*2;
	    		currentFps = 0;
	    		lastSecTime = clock.millis();
	    	}
	    }
	  
	    
		public long getClockMillis() {
			return clock.millis();
		}
	    
	    
	    
	}
	    
	    
	    
	    


	







	 

	
