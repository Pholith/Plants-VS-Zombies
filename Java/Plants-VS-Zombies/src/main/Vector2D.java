package main;

public class Vector2D {

		private final int x;
		private final int y;
		
		
		public Vector2D(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		/* Les méthodes d'additions */
		public Vector2D add(Vector2D v) {
			return new Vector2D(x+v.x , y+v.y );
		}
		public Vector2D add(int x, int y) {
			return new Vector2D(this.x+x, this.y+y);
		}
		
	    public double length() {
	        return (double) Math.sqrt(this.x*this.x + this.y*this.y);
	    }
	    
	    
}