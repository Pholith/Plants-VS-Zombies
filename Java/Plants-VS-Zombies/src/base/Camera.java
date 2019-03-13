package base;

import java.awt.Graphics2D;
import java.util.ArrayList;


public class Camera extends GameObject {

	private float renderSize = 5.0f;

	public Camera(Vector2 pos,  float renderSize) {
		super(pos);
		this.renderSize = renderSize;
	}	
	public Camera(Vector2 pos) {
		this(pos, 5.0f);
	}
	public Camera() {
		this(Vector2.zero());
	}
		
	
	public void Render(ArrayList<GameObject> sceneObjs,Graphics2D graphics) {
		
		for(int i = 0; i < sceneObjs.size(); i++) {
			sceneObjs.get(i).display(getPosition(), graphics);
		}
		
	}
	
	
	
}
