package base;

import java.awt.Graphics2D;

/*
 * Texte temporaire qui servira de particule de dégats ou autre (plutôt pour debug)
 */
public class TemporaryText extends GameObject {
	
	
	private final int lifeDuration;
	public TemporaryText(Vector2 pos, int lifeDuration, String content) {
		super(pos);
		this.lifeDuration = lifeDuration;
		this.content = content;
	}


	private int currentDuration = 0;
	private final String content;

	
	@Override
	public void update() {
		currentDuration ++;
		if (currentDuration > lifeDuration) {
			destroy();
		}
	}
	
	/*public Sprite display() {
		return "";
	}*/

}
