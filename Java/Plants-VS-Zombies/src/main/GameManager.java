package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import base.Camera;
import base.GameObject;
import base.LivingEntity;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;


	///Cette classe est un singleton. Le jeu démare à sa creation , à l'appel de la fonction StartManger();
	public class GameManager {
		
	    private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() { return SINGLE_INSTANCE;  }
	    private GameManager() //Ne pas initialiser de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
	    	loadedSprites = new HashMap<String, Image>();
	    }
	    
	
	    
	    private Camera mainCamera;
	    
	    private float resolutionX;
	    private float resolutionY; 
	    
	    ArrayList<GameObject> sceneContent;
	    Map<String,Image> loadedSprites;
	       
	    
	    
	    void StartManager() throws IOException {
	    	
	       	//////////////////////////////////////////Initialisations génerale des resources 
	    	
	    	mainCamera = new Camera();
	    	GameObject testAffiche = new LivingEntity(20);
	    	
	    	loadSpriteAtPath("resources/textures/error.png");
	    	
	    	loadSpriteAtPath("resources/textures/plants/plant_idl_0.png");
	    	
	    	
	    	////////////////////////////////////////Démarage de la boucle principale
	    	 
			 Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
			      ScreenInfo screenInfo = context.getScreenInfo();			    
			      resolutionX = screenInfo.getWidth();
			      resolutionY = screenInfo.getHeight();
			     
			      
			      System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");
			      
			      while (true) {
			    	  
			    	  
			      inputCheck(context);
			      render(context);
		
			      }
			 });
	    }   
	    
	    
	
	    
	    private void render(ApplicationContext context) {			
	    	  
		      context.renderFrame(graphics -> {  
		    	  graphics.setColor(Color.WHITE);
		    	  graphics.fill(new  Rectangle2D.Float(0, 0, resolutionX, resolutionY));		
		    	  mainCamera.Render(sceneContent,graphics);		
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
	    
	    
	    
	    public void loadSpriteAtPath(String spriteLink ) throws IOException {
	     	
	    	   File pathToFile = new File(spriteLink);
	    	   Image image = ImageIO.read(pathToFile);    	
	    	   loadedSprites.put(spriteLink, image);
	    	   
	    }
	    
	    public Image getImageByPath(String spriteLink ){
		     	if(loadedSprites.containsKey(spriteLink))
		     		return loadedSprites.get(spriteLink);
		     	return null;
	    }
	    
	    
	    
	    
	}
	    
	    
	    
	    


	







	 

	

