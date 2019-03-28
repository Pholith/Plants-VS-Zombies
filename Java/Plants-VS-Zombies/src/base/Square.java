package base;


import java.util.*;

import plants.Peashooter;

/**
 * 
 */
public class Square {

	int posX;
	int posY;
	
	float realX;
	float realY;
	
	
    public Square(int x, int y) {
    posX = x;
    posY = y;
    
    realX = 3 + (x) * 0.935f;
    realY = 0.8f+(y)*1.1f;
    
    }


}