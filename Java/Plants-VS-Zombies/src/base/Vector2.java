package base;


public class Vector2 {

		private final float x;
		private final float y;
		
		
		public Vector2(float x, float y) {
			super();
			this.x = x;
			this.y = y;
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
	        return (double) Math.sqrt(this.x*this.x + this.y*this.y);
	    }
	    
	    // Possibilité de le changer quand on aura un getRow
	    public boolean isOnSameRow(Vector2 v) {
	    	float distanceBen = 0.3f;
	    	return (y < v.y+distanceBen && y > v.y- distanceBen);
	    }
	    
	    
}