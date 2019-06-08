package base;

import java.io.Serializable;

public class Vector2 implements Serializable {

		
	private static final long serialVersionUID = -269571007417092821L;
		private final float x;
		private final float y;
		
		
		public Vector2(float x, float y) {
			super();
			this.x = x;
			this.y = y;
		}	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Float.floatToIntBits(x);
			result = prime * result + Float.floatToIntBits(y);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vector2 other = (Vector2) obj;
			if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
				return false;
			if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
				return false;
			return true;
		}
		public Vector2() {
			this.x = 0;
			this.y = 0;		
		}
		
				
		@Override
		public String toString() {
			return "Vector2 ( " + x + " , " + y+" )";
		}
		
		public static Vector2 zero() {
			return new Vector2();
		}
		public static Vector2 one() {
			return new Vector2(1,1);
		}
		
		
		public float getX() {
			return x;
		}
		public float getY() {
			return y;
		}
		
		/* Les méthodes d'additions */
		public Vector2 add(Vector2 v) {
			return new Vector2(x+v.x , y+v.y );
		}
		public Vector2 add(float x, float y) {
			return new Vector2(this.x+x, this.y+y);
		}
		
	    public double length() {
	        return Math.sqrt(this.x*this.x + this.y*this.y);
	    }

		/* Les méthodes de multiplication */
		public Vector2 multiply(float f) {
			return new Vector2(x*f , y*f );
		}
	    
	    // Possibilité de le changer quand on aura un getRow
	    public boolean isOnSameRow(Vector2 v) { // TO UPDATE
	    	float distanceBen = 0.45f;
	    	return (y < v.y+distanceBen && y > v.y- distanceBen);
	    }
	    public boolean isOnSameCol(Vector2 v) {
	    	return Terrain.positionToCase(new Vector2(x, 0)).getX() == Terrain.positionToCase(new Vector2(v.getX(), 0)).getX();
	    }
	    // renvoie un vecteur random pour la création des zombies
	    public static Vector2 randomStartVector() {
	    	return new Vector2((float) (10 + Math.random()*2), (float) Math.random()*5);
	    }
	    
	    public static Vector2 randomVector() {
	    	float rd = (float) (Math.random()*2f*Math.PI);	    	
	    	return new Vector2((float)Math.cos(rd), (float)Math.sin(rd) );
	    }
	    
	    
	    public static float distance(Vector2 vec1, Vector2 vec2) {
	    	return (float) Math.sqrt(   Math.pow(vec1.getX()- vec2.getX(),2)+ Math.pow( vec1.getY()- vec2.getY(), 2f) );
	    }
	    
	    
	    
	    
	    
}