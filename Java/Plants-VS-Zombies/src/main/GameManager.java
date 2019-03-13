package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import base.Camera;
import base.GameObject;
import base.LivingEntity;
import fr.umlv.zen5.*;


	///Cette classe est un singleton. Le jeu démare à sa creation , à l'appel de la fonction StartManger();
	public class GameManager {
		
	    private static final GameManager SINGLE_INSTANCE = new GameManager();
	    public static GameManager getInstance() {
		      return SINGLE_INSTANCE;
	    }
	    
	    private Camera mainCamera;
	    
	    private float resolutionX;
	    private float resolutionY; 

	    
	    ArrayList<GameObject> sceneContent;
	    
	    
	    
	    private GameManager() //Ne pas initialise de game object dans cette partie du code  
	    { 
	    	sceneContent = new ArrayList<GameObject>(); 
	    } 
	    	
	    	
	    	
	    void StartManger() {

	    	
	       	//////////////////////////////////////////Initialisations génerale des resources 
	    	
	    	mainCamera = new Camera();
	    	GameObject testAffiche = new LivingEntity(20);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
		   
	    	////////////////////////////////////////Démarage de la boucle principale
	    	 
			 Application.run(Color.WHITE,context -> {			      
			      // get the size of the screen
			      ScreenInfo screenInfo = context.getScreenInfo();			    
			      resolutionX = screenInfo.getWidth();
			      resolutionY = screenInfo.getHeight();
			     
			      
			      System.out.println("size of the screen (" + resolutionX + " x " + resolutionY + ")");
			      
			      while (true) {
			
			      context.renderFrame(graphics -> {
			    	  clearFrame(graphics);
			    	  mainCamera.Render(sceneContent,graphics);
			      });
			      }
			 });
	    }   
	    
	    
	    
	    void clearFrame(Graphics2D graphics) {
	        graphics.setColor(Color.WHITE);
	        graphics.fill(new  Rectangle2D.Float(0, 0, resolutionX, resolutionY));	    
	    }
	    
	   
	  
	    
	    public void addGameObjectToScene(GameObject obj) {	
	    	if(!sceneContent.contains(obj))
	    		sceneContent.add(obj);
	    }
	}
	    
	    
	    
	    


	







	 

	

