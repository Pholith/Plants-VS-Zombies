package base;


public class Vector2 {

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
		
		/* Les m�thodes d'additions */
		public Vector2 add(Vector2 v) {
			return new Vector2(x+v.x , y+v.y );
		}
		public Vector2 add(float x, float y) {
			return new Vector2(this.x+x, this.y+y);
		}
		
	    public double length() {
	        return (double) Math.sqrt(this.x*this.x + this.y*this.y);
	    }
	    
		/* Les m�thodes de multiplication */
		public Vector2 multiply(float f) {
			return new Vector2(x*f , y*f );
		}
	    
	    // Possibilit� de le changer quand on aura un getRow
	    public boolean isOnSameRow(Vector2 v) {
	    	float distanceBen = 0.45f;
	    	return (y < v.y+distanceBen && y > v.y- distanceBen);
	    }
	    // renvoie un vecteur random pour la cr�ation des zombies
	    public static Vector2 randomStartVector() {
	    	return new Vector2((float) (10 + Math.random()*2), (float) Math.random()*5);
	    }
	    
}